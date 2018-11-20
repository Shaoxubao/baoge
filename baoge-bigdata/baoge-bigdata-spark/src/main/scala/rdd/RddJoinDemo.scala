/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/11/13
  */

package scala.rdd

import rdd.BaseSparkSession

object RddJoinDemo extends BaseSparkSession {

    def main(args: Array[String]): Unit = {
        val custs = Seq(
            (1, "小明", 24, 85, "B"),
            (2, "小华", 21, 82, "C"),
            (3, "小六", 25, 86, "A"),
            (4, "小呆", 24, 88, "A"),
            (5, "小鹏", 21, 85, "B")
        )

        val cust = Seq(
            (1, "小明", 24),
            (2, "小华", 21),
            (3, "小六", 25)
        )

        val custRows = sparkSession.sparkContext.parallelize(custs, 3)

        import sparkSession.implicits._
        // convert RDD of tuples to DataFrame by supplying column names
        val customersDF = custRows.toDF("id", "name", "age", "score", "degree")

        val custRow = sparkSession.sparkContext.parallelize(cust, 3)
        import sparkSession.implicits._
        // convert RDD of tuples to DataFrame by supplying column names
        val customerDF = custRow.toDF("id", "nackname", "size")

        val joinDF = customersDF.join(customerDF, Seq("id"), "left_outer")

        joinDF.show(false)
    }

}
