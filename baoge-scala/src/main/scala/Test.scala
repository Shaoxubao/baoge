/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/7/31
  */


object Test {
    def main(args: Array[String]): Unit = {
        var recPanName = "我、是、中、国、人"
        val recPanNameArr = recPanName.split("、").take(3)

        println(recPanNameArr.foreach(println))
    }
}
