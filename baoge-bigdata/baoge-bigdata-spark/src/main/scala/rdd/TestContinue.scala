package scala.rdd

import rdd.BaseSparkSession

import util.control.Breaks._

object TestContinue extends BaseSparkSession{

    def main(args: Array[String]): Unit = {

        import sparkSession.implicits._
        val df = Seq(
            (1, "First Value"),
            (2, "Second Value")).toDF("id", "name")


        val df2 = Seq(
            (1, "First Value", "25"),
            (2, "Second Value", "32"),
            (3, "Third Value", "31")).toDF("id", "name", "age")

        val dfArr = df.rdd.collect()
        dfArr.foreach(r => {
            val resultDF = df2.map(row => {
                val id1 = r.getAs[Int]("id")

                val id2 = row.getAs[Int]("id")
                breakable {  // 此法不能用于df.map中
                    if (id2 == 2) {
//                        println("break id1:" + id1 + "id2:" + id2)
                        break()
                    }
                }
                println("id1:" + id1 + ",id2:" + id2)
                (id1, id2)
            }).toDF("id1", "id2")

            resultDF.show(false)
        })
    }

}
