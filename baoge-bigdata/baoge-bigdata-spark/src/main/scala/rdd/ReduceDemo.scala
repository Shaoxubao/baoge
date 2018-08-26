/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/5/24
  */

package rdd

import org.apache.spark.sql.SparkSession

object ReduceDemo {
    def main(args: Array[String]): Unit = {

        val sparkSession = SparkSession.builder()
                .appName("ReduceTest")
                .master("local[2]")
                .getOrCreate()

        val linesRDD= sparkSession.sparkContext.textFile("e:/tmp/spark/data.txt")
        linesRDD.foreach(r => println(r))

        // 求每一行的长度
        val linesLength = linesRDD.map(r => r.length)
        // 总长度大小
        val totalLength = linesLength.reduce((a, b) => a + b)

        println(totalLength)

    }
}
