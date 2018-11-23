package scala.file

import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import rdd.BaseSparkSession

import scala.collection.mutable
import util.control.Breaks._
import scala.io.Source

object FileOperationDemo extends BaseSparkSession {

    def main(args: Array[String]): Unit = {
        val loupanFilePath = "/dict/loupan.dict"

        val loupanDict = Source.fromInputStream(this.getClass().getResourceAsStream(loupanFilePath))
                .getLines().map(line => {
            val values = line.split(",")
            (values(0).trim, values(1).trim)
        }).toArray.groupBy(_._1).map(e => (e._1, e._2.map(_._2)))

        val loupanDictMap = Source.fromInputStream(this.getClass().getResourceAsStream(loupanFilePath))
                .getLines().map(line => {
            val values = line.split(",")
            (values(1).trim, values(0).trim)
        }).toArray.groupBy(_._1).map(e => (e._1, e._2.map(_._2)))

//        loupanDictMap.foreach(r => println(r))


        val cityPath = "/dict/dim-city.dict"
        val city = Source.fromInputStream(this.getClass().getResourceAsStream(cityPath)).getLines()
                .map(x => x.trim.split(","))
                .map { case Array(id, name) => Seq(id.toInt, name) }
                .map(x => Row(x: _*)).toSeq
        val schema = StructType(List(
            StructField("city_id", IntegerType, true),
            StructField("city_name", StringType, true)))
        val sc = sparkSession.sparkContext
        val cityDF = sparkSession.createDataFrame(sc.parallelize(city), schema)

        val cityArr = cityDF.select("city_name").collect()

        cityArr.foreach(r => println(r.getAs[String]("city_name")))

        val title = "中海戈雅园入市倒计时！看到这样的大三房 我立马交出了“房票”！"
        val content = ""
        val cityNameExtract = extractCityNameByTitleOrContent(title, content, cityDF, loupanDictMap)

        println("cityNameExtract result:" + cityNameExtract)

    }

    private def extractCityNameByTitleOrContent(title: String, content: String, cityDF: DataFrame,
                                                loupanDictMap: Map[String, Array[String]]): String = {
        val cityArr = cityDF.select("city_name").collect()

        var cityNameResult = "全国"

        // 1、标题中是否含有城市名
        var titleCityCount = 0
        breakable {
            for (i <- 0 to cityArr.length - 1) {
                val originCityName = cityArr(i).getAs[String]("city_name")
                if (title.contains(originCityName)) {
                    cityNameResult = originCityName
                    titleCityCount += 1
                }
                if (titleCityCount > 1) {
                    break()
                }
            }
        }
        if (titleCityCount == 1) {
            return cityNameResult
        }
        if (titleCityCount > 1) {
            cityNameResult = "全国"
            return cityNameResult
        }
        // 2、标题中城市为0，标题中看是否有楼盘小区
        val loupanCityAllSet = mutable.HashSet[String]()         // 楼盘所有城市set
        val loupanCityMap = mutable.HashMap[String, mutable.HashSet[String]]() // 楼盘城市map
        var maxLengthLoupan = ""     // 楼盘名
        breakable {
            for ((loupanKey, loupanCityArr) <- loupanDictMap) {
                if (title.contains(loupanKey)) {
                    if (loupanKey.length > maxLengthLoupan.length) {
                        maxLengthLoupan = loupanKey
                    }
                    loupanCityArr.foreach(r => loupanCityAllSet.add(r))
                    val loupanCitySet = mutable.HashSet[String]()         // 楼盘城市set
                    loupanCityArr.foreach(r => loupanCitySet.add(r))
                    loupanCityMap.put(loupanKey, loupanCitySet)
                }
            }
        }

        if (loupanCityAllSet.size == 1) {
            cityNameResult = loupanCityAllSet.iterator.next()
            return cityNameResult
        }
        if (loupanCityAllSet.size > 1) {
            val loupanCitySet = loupanCityMap(maxLengthLoupan)
            if (loupanCitySet.size == 1) {
                cityNameResult = loupanCitySet.iterator.next()
            } else {
                cityNameResult = "全国"
            }
            return cityNameResult
        }

        // 文章content中识别城市=====================
        // 3、content中是否含有城市名
        var contentCityCount = 0
        breakable {
            for (i <- 0 to cityArr.length - 1) {
                val originCityName = cityArr(i).getAs[String]("city_name")
                if (content.contains(originCityName)) {
                    cityNameResult = originCityName
                    contentCityCount += 1
                }
                if (contentCityCount > 1) {
                    break()
                }
            }
        }
        if (contentCityCount == 1) {
            return cityNameResult
        }
        if (titleCityCount > 1) {
            cityNameResult = "全国"
            return cityNameResult
        }

        // 4、content中城市为0，content中看是否有楼盘小区
        breakable {
            for ((loupanKey, loupanCityArr) <- loupanDictMap) {
                if (content.contains(loupanKey)) {
                    loupanCityArr.foreach(r => loupanCityAllSet.add(r))
                }
                if (loupanCityAllSet.size > 1) {
                    break()
                }
            }
        }

        if (loupanCityAllSet.size == 1) {
            cityNameResult = loupanCityAllSet.iterator.next()
            return cityNameResult
        }
        if (loupanCityAllSet.size > 1) {
            cityNameResult = "全国"
            return cityNameResult
        }

        cityNameResult
    }

}
