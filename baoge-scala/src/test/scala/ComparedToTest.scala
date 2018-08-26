/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/6/19
  */


object ComparedToTest {

    def main(args: Array[String]): Unit = {
        val strA = "117592394551764c444"
        val strB = "1179b476f72b56e70e5"
        val strC = "117592394551764c444"

        println(strA.compareTo(strB)) // -4
        println(strA.compareTo(strC)) // 0
        println(strB.compareTo(strA)) // 4
    }

}
