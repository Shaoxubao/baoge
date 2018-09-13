
object Test {
    def main(args: Array[String]): Unit = {
        var recPanName = "我、是、中、国、人"
        val recPanNameArr = recPanName.split("、").take(3)

        println(recPanNameArr.foreach(println))
    }
}
