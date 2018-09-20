package scala.rdd

import rdd.BaseSparkSession

object ExceptDataFrameDemo extends BaseSparkSession {
    def main(args: Array[String]): Unit = {
        val custs = Seq(
            (1, "小明", 24, 85, "B"),
            (2, "小华", 21, 82, "C"),
            (3, "小六", 25, 86, "A"),
            (5, "小鹏", 21, 85, "B")
        )

        val custs2 = Seq(
            (1, "小明", 24, 85, "B"),
            (1, "小明", 24, 88, "A"),
            (5, "小鹏", 21, 85, "B")
        )

        val custRows = sparkSession.sparkContext.parallelize(custs, 3)
        val custRows2 = sparkSession.sparkContext.parallelize(custs2, 3)

        import sparkSession.implicits._
        // convert RDD of tuples to DataFrame by supplying column names
        val customerDF = custRows.toDF("id", "name", "age", "score", "degree")
        val customerDF2 = custRows2.toDF("id", "name", "age", "score", "degree")

//        val resultDF = customerDF.except(customerDF2)

        // 选出a和b中都有的
        val resultDF = customerDF.as("a")
                .join(customerDF2.as("b"), customerDF("id") === customerDF2("id"))
                .select("a.*")
                .distinct()

        resultDF.show(false)
    }
}
