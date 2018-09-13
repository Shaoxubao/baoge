package rdd

import org.apache.spark.sql.SparkSession

class BaseSparkSession {
    val sparkSession = SparkSession.builder()
            .appName("ReduceTest")
            .master("local[2]")
            .getOrCreate()
}
