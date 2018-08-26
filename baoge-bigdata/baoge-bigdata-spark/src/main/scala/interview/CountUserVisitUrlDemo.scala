/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/6/22
  */

package interview

import rdd.BaseSparkSession

/**
  * http://cwiki.apachecn.org/pages/viewpage.action?pageId=2886351
  * 举个例子,比如要统计用户的总访问次数和去除访问同一个URL之后的总访问次数,随便造了几条样例数据(四个字段:id,name,vtm,url,vtm字段本例没用,不用管)如下:
  * id1,user1,2,http://www.hupu.com
    *id1,user1,2,http://www.hupu.com
    *id1,user1,3,http://www.hupu.com
    *id1,user1,100,http://www.hupu.com
    *id2,user2,2,http://www.hupu.com
    *id2,user2,1,http://www.hupu.com
    *id2,user2,50,http://www.hupu.com
    *id2,user2,2,http://touzhu.hupu.com
 **
  * 根据这个数据集,我们可以写hql 实现:
    *select id,name, count(0) as ct,count(distinct url) as urlcount
    *from table
    *group by id,name;
 **
  * 得出结果应该是:
 **
  * id1,user1,4,1
    *id2,user2,4,2
 **
  * 下面用Spark实现这个聚合功能
    *简单说说MR的解析过程：
 **
  * map阶段： id和name组合为key， url为value
    *reduce阶段： len(urls) 出现次数, len(set(urls)) 出现用户数
  */
object CountUserVisitUrlDemo extends BaseSparkSession {
    def main(args: Array[String]): Unit = {

        val custs = Seq(
            (1, "user1", 4, "http://www.hupu.com"),
            (1, "user1", 1, "http://www.hupu.com"),
            (1, "user1", 5, "http://www.hupu.com"),
            (1, "user1", 4, "http://www.hupu.com"),
            (5, "user2", 1, "http://www.hupu.com"),
            (5, "user2", 9, "http://www.hupu.com"),
            (5, "user2", 8, "http://www.hupu.com"),
            (5, "user2", 2, "http://touzhu.hupu.com")
        )

        val custRows = sparkSession.sparkContext.parallelize(custs, 3)

//        import sparkSession.implicits._
        // convert RDD of tuples to DataFrame by supplying column names
//        val customerDF = custRows.toDF("id", "name", "vtm", "url")

//        val mapRDD = customerDF.map(r => ((r("id"), r("name")), r))

    }
}
