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

        val title = "捂盘8年！涨价1万+！首付可能5成！鼓楼神秘楼盘马上开盘！"
        val content = "鼓楼老盘长江峰景，捂盘8年，最近曝出最新消息。 该楼盘正在申领销许，推162套江景房，户型面积93-140㎡，预计精装4万/㎡以上，学区是复兴小学（力小分校）。 据365淘房，该楼盘预计首付5成，还需要验资。 周边的中介告诉我，长江峰景这个楼盘还是比较神秘的，里面住的非富即贵。 该楼盘上一次开盘，是在2011年1月，当时精装2.8万/㎡，据今已经长达8年了，很多人都以为这个楼盘早已卖完。这次开盘预计卖精装4万+/㎡，相较之前涨了1万多/ ㎡。 就在最近，该楼盘官微更新内容，透露即将加推二期两栋楼，162套房，户型93 、95、120 、130、140㎡。近期将公开售楼处。 我查询365淘房，目前小区二手房均价47891元/㎡。 但实际成交的二手房并不多，据中介反应，小区房源少，业主基本都是买来自住，挂出来的就不多，而且业主心态高，有人直接把价格挂到近5万/㎡。 户型图： 92㎡：两室两厅一卫 95㎡：两室两厅一卫 119㎡：三室两厅一卫 130㎡：三室两厅两卫 140㎡：三室两厅两卫"
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
                val newContent = content.replace( originCityName + "报道", "")
                if (newContent.contains(originCityName)) {
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
        if (contentCityCount > 1) {
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
