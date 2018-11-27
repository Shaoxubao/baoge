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

        val title = "这一次，融创让你“上下为难”"
        val content = "江浙沪一手房汇总您有1668位好友已关注 置顶我，获取「独家」江浙沪楼市资讯！ 一般买叠加，更多人会选下叠。而这一次，融创在徐汇最新设计了一个叠加产品，会让很多买家犹豫：究竟选择上叠，还是下叠？ “品”字形的设计，总共才3套叠加 整个三、四层为一套 一、二层，则东西各一套 从俯视图可以看到 上叠，设计多个大面积露台 而且上叠同样有花园，有地下室 通过电梯进入上叠 马上就可以看到如此大的露台 使室内空间向户外延伸 让人感觉眼前一片开阔 上叠还是下叠？ 头一次那么难选择 位于徐汇的叠加新品，融创打造 近期刚刚对外发布 融创领馆壹号院 世界语境 上下院墅 融创领馆壹号院位于拥有百年历史的徐汇，融汇上海百年人文精神，以精细严谨的极简主义现代建筑美学为设计灵感，打造现代东方院墅产品。 承袭东方文化，融创领馆壹号院复刻“礼/禅/趣”三进院落空间，重塑“门、馆、景、户”四重归家礼遇，让生活出则繁华，入则宁静。 露而不透的静谧大门是归家心情的起点，从城市到社区，从公共界面到私家空间的过渡，展现尊贵感、私密感。 由大门进入一进庭院，映入眼帘的是一个工整、精致的院落空间。列植的银杏、精修的灌木、沉静的水面，营造出雅致、礼序、宁静的空间氛围。 二进庭院，通过水、石、树、岛的组合形成一幅生命画卷，将现代精神与东方情节交织在一起。随着时间的延展，乌桕这类色叶树种的变幻，让人能在这里看见四季、看见自然。 走在巷道间感受空间的张弛，虚实变化，光影交错，在归家动线上营造出许多变换灵动的细节。 去风格式留白设计，将建筑转化为几何线条和空阔的灰白，缔造亲地而居的内心平静。 空间会与人的灵魂和思想联合，让人享受其中。建筑面积约2000㎡会所，借助山水等东方意境，将自然艺术化，更注重细节和内涵，让空间生活回归简朴自然。 约10米宽景露台、通透的玻璃和精致的窗套线条，表达了纯粹和简洁的现代美感，模糊了室内外空间界线，使建筑、室内与景观完美融合。 7.4米横厅与大面积落地玻璃窗，将光线引入室内，营造内外无界的空间。 融创壹号公馆 徐汇西 纯粹法式谧境 融创壹号公馆，融创法式经典超越之作，联手国际顶尖设计巨匠，以前沿法式审美与品位，历经900多天精心准备、高达9次易稿打磨，复兴一席纯粹的法式秘境，匠造精睿生活范本。 恢弘对称的皇家礼制、露天的镜面泳池，敬呈法式生活的静谧内心所在。 约2280㎡美学会所，重塑塔尖人物的私属领地。 定制9大主题空间，集萃全年恒温泳池、健身房、瑜伽室、儿童游戏等功能，安放美好的无限可能。 依循以人为本的空间设计理念，精琢每一寸舒适空间，以一生舒居致献城市精睿。 11月，融创壹号公馆法式谧境盛景已绽放，为上海再现高雅尊崇之美。 “每一个国家、每一座城市都有自己的建筑时代。” 在上海，融创正以一座座“壹号”封面之作，勾勒这座卓越城市的生活精彩，以匠心与善意，为居者创造美好的生活时代。 如果你对这个叠加感兴趣，想了解更多 找“小蜜”加入购房群☟ 更多楼盘信息 欢迎扫码加入江浙沪购房群 与上千名购房者一起交流~ 获取最新认筹开盘信息 更多精彩内容 点击图片"
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
