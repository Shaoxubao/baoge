
package rdd

object ReduceByKeyAndGroupByKeyDemo extends BaseSparkSession {

    def main(args: Array[String]): Unit = {

        val words = Array("one", "two", "two", "three", "three", "three")

        val wordPairsRDD = sparkSession.sparkContext.parallelize(words).map(word => (word, 1))

        val wordCountsWithReduce = wordPairsRDD.reduceByKey(_ + _)

        val wordCountsWithGroup = wordPairsRDD.groupByKey().map(t => (t._1, t._2.sum))



    }

}
