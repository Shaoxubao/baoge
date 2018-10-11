package scala.rdd

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import rdd.BaseSparkSession

object VectorAssemblerTest extends BaseSparkSession {
    def main(args: Array[String]): Unit = {
        val dataSetDF = sparkSession.createDataFrame(
            Seq((0, 18, 1.0, Vectors.dense(0.0, 10.0, 0.5), 1.0))
        ).toDF("id", "hour", "mobile", "userFeatures", "clicked")
        val assembler = new VectorAssembler()
                .setInputCols(Array("hour", "mobile", "userFeatures"))
                .setOutputCol("features")

        val output = assembler.transform(dataSetDF)
        println(output.select("features", "clicked").first())
    }
}
