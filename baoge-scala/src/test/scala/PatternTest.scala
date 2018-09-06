/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/9/6
  */

package scala

/**
  * 正则
  */
object PatternTest {
    def main(args: Array[String]): Unit = {
        val regex = """([0-9]+)|([a-zA-Z]+)""".r//"""原生表达
        val numPattern="[0-9]+".r
        val numberPattern="""\s+[0-9]+\s+""".r

        // findAllIn()方法返回遍历所有匹配项的迭代器
        for(matchString <- regex.findAllIn("99345Scala,22298 中国人民学习Spark")) {
            println(matchString)
        }

    }
}
