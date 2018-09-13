import org.apache.spark.sql.SparkSession

object OpGroupRDDTest {
    def main(args: Array[String]): Unit = {
        val spark =
            SparkSession.builder()
                    .appName("DataFrame-DropDuplicates")
                    .master("local[4]")
                    .getOrCreate()

        // create an RDD of tuples with some data
        val custs = Seq(
            (1, "Widget Co", 120000.00,    0.93, "AZ"),
            (2, "Acme Widgets", 410500.00, 0.19, "CA"),
            (3, "Widgetry", 410500.00,     0.90, "CA"),
            (4, "Widgets R Us", 410500.00, 0.12, "CA"),
            (3, "Widgetry", 410500.00,     0.21, "CA"),
            (5, "Ye Olde Widgete", 500.00, 0.22, "MA"),
            (6, "Widget Co", 12000.00,     0.16, "AZ")
        )
        val customerRows = spark.sparkContext.parallelize(custs, 4)

        val groupRDD = customerRows.groupBy(row => row._1)
        val groupDF = customerRows.groupBy(row => row._1).map(row => {
            val arr = row._2
        })
        groupRDD.foreach(r => println(r))

        groupRDD.foreach(row => println(row._2.iterator.foreach(f => println(f.toString()))))

//        val disRDD = groupRDD.takeOrdered(1)(Ordering[Double].reverse.on(x => x._2.iterator))

//        val sortRDD = customerRows.sortBy(x => x._4, false)
//        sortRDD.foreach(r => println(r))


    }
}
