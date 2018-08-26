package rdd

/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/5/25
  */

import org.apache.spark.sql.SparkSession

class BaseSparkSession {
    val sparkSession = SparkSession.builder()
            .appName("ReduceTest")
            .master("local[2]")
            .getOrCreate()
}
