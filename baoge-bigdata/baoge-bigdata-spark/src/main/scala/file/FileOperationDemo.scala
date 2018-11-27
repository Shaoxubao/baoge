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

        val title = "首付52万起！江宁地铁盘要来了，洋房首开！"
        val content = "理想的建筑 著名建筑大师贝聿铭曾说过： 一座理想的建筑， 满足居住需求只是基本使命， 能够承载居住者对美好生活的想象和期待， 才是让人安放心灵的家。 何谓理想的建筑？绿地理想城，给你一个答案！ 绿地理想城，首付52万起，让你在南京安置一个家！ 绿地理想城效果图 低总价+核心地段，绿地理想城吸引了无数刚需买房人的目光！项目11月17日样板间公开后，已经吸引了各地数千组买房人前往售楼处观看，项目近期首开推出18层洋房住宅，必将掀起一股购房热潮！ 1 首付52万起，就能在江宁买到三房 首付52万起，就能在江宁买套新房，还是三房！还是地铁盘！这家楼盘就是位于江宁正方新城的绿地理想城。随着江宁区域的迅速发展，江宁的房价破3万/㎡的比比皆是，此次绿地理想城首开价格还是比较优惠的！ 推荐理由 1、位于江宁正方新城发展区域，距离禄口机场和南京南站都非常近； 2、楼盘旁边就是地铁S1号线正方中路站，是真正的地铁盘！ 3、首付仅52万起，价格适宜； 4、配套完善。周边商业体、医院、学校等配套完善，一应俱全。 目前想要在南京买房，购房门槛越来越高，绿地理想城位于江宁区域发展价值高低，首付仅52万起步，轻松入住。 2 地铁盘，户型实用 对于想在南京置业买房的人来说，这样的地铁热盘，真的不失为一个很好的选择。 绿地理想城位于江宁正方新城，正方新城规划范围东至机场高速、南至银杏湖大道、西至牛首大道南段及银杏湖西段、北至南京绕越高速。该板块面积达14.21平方公里，包括东大山片区、软件园吉山基地和软件园紫金片区，未来预计将容纳居住人口19万人，就业人口30万人。 目前正方新城板块内除了绿地理想城，在售楼盘还有景瑞春风十里、远洋山水、荣盛隽峰、银城蓝溪郡等项目，板块内住宅项目越来越扎堆，居住环境越来越完善。 总价低，楼盘紧靠地铁口，毗邻禄口机场和南京南站，出行便利，生活方便，随着江宁新区的不断发展，未来周边的生活配套必将不断完善，居住舒适度也会越来越高。 ▼A户型：建筑面积约102㎡ 三室两厅一卫 【户型点评】：主次卧大床飘窗设计，三开间朝南带大阳台 ▼B户型：建筑面积89㎡三室两厅一卫 【户型点评】：三开间朝南的设计，89平做成了三方的设计，玄关、双厅、阳台一气呵成，卫浴干湿分离，空间舒适度高。 ▼89㎡样板间实拍 3 江宁正方新城，区域优势明显 项目以南京的新硅谷为定位，以江苏软件园为产业依托，以高素质人口和高科技产业为基础，尤其以紫金（江宁）科技创业特别社区和华谊电影小镇的落户，未来就业人流和旅游人流双层叠加，将为正方新城带来超高人气。 项目属于南部新城缓冲地带，自住、投资门槛比南站区域更具性价比，可以预见的是，未来将借势南部新城大环境，未来拥有的双地铁资源，也是相当大的一个优势。 绿地理想城是绿地中高端品牌系新里系作品，将打造18栋住宅、5栋商业、2栋办公和1栋18层酒店式公寓，此外配建一个社区中心和幼儿园。 项目此次首开推出洋房住宅，近期开盘，更多项目信息和团购详情，请添加小编微信了解。 快来加入各片区买房交流群 板块 城东 zhouxiaoyu_njlsgc 城北 vx20160923 城南 河西 zxYYZ012 江宁 jiangxb_njlsgc 江北 都市圈 （句容、滁州等） njlsgc007 njlsgc008 置业类型 公寓、商铺 njlsgc008 度假、海外 zxYYZ012 二手房、学区房 njlsgc007 综合咨询 njlsgc007 贴心提示：看不到朋友圈的不加哦！ 你可能还想看 有人说出南京房价里的套路！为什么我们不愿意承认？ 总价117万起！江宁地铁住宅今天登记，扬子江改造传来新消息！ 幼儿园也要划片入学？“入园”两难问题将解决！ 地价破2万，房价1.65万/㎡起！首付不到10万就能买！ 南京到连云港仅需2小时！这条高铁16站点曝光，有经过你家的吗？"
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
