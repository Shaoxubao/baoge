package rdd

object DropDuplicatesDemo extends BaseSparkSession {

    def main(args: Array[String]): Unit = {

        val custs = Seq(
            (1, "小明", 24, 85, "B"),
            (2, "小华", 21, 82, "C"),
            (3, "小六", 25, 86, "A"),
            (1, "小明", 24, 88, "A"),
            (5, "小鹏", 21, 85, "B")
        )

        val custRows = sparkSession.sparkContext.parallelize(custs, 3)

        import sparkSession.implicits._
        // convert RDD of tuples to DataFrame by supplying column names
        val customerDF = custRows.toDF("id", "name", "age", "score", "degree")

        // drop fully identical rows
        val withoutDuplicates = customerDF.dropDuplicates("name") // 不指定名称默认按id去除相同id
        withoutDuplicates.show(false)

        // drop fully identical rows
        val resultDropDuplicates = customerDF.dropDuplicates("name").dropDuplicates("age") // 不指定名称默认按id去除相同id
        resultDropDuplicates.show(false)
    }
}
