/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/5/25
  */

package rdd

object ReduceByKeyDemo extends BaseSparkSession {

    def main(args: Array[String]): Unit = {

        val outputFile = "e:/tmp/spark/data1.txt"

        // 累加器
        val accum = sparkSession.sparkContext.longAccumulator("my accounter")

        val dataRDD= sparkSession.sparkContext.textFile("e:/tmp/spark/data.txt")
        dataRDD.foreach(r => println(r))

        // Split up into words.
        val wordRDD = dataRDD.flatMap(line => line.split(" "))
        // Transform into word and count.
        val countRDD = wordRDD.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
        // Save the word count back out to a text file, causing evaluation.
        // countRDD.saveAsTextFile(outputFile)

        // 不能用countRDD.map(r => accum.add(r._2))，因为没有 actions（动作）来让 map 操作被计算。
        countRDD.foreach(r => accum.add(r._2))
        println(accum.value)

        countRDD.foreach(r => println(r))
    }

}
