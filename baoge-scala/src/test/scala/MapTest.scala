/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/11/27
  */

package scala

import scala.collection.mutable

object MapTest {

    def main(args: Array[String]): Unit = {

        val tagMap = mutable.HashMap[String, Long]()

        val keyArr = "a、b、c、c".split("、")
        keyArr.foreach(key => {
            var tagCount = tagMap.get(key)
            if (tagCount.equals(None)) {
                tagMap.put(key, 1L)
            } else {
                val tagCountR = tagCount.get + 1
                tagMap.put(key, tagCountR)
            }
        })

        val r = tagMap.toSeq.sortWith(_._2 > _._2)  // 降序排序 value

        r.foreach(r => println(r._1 + "：" + r._2))

    }

}
