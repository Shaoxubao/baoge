package scala.file

import scala.io.Source

object FileOperationDemo {

    def main(args: Array[String]): Unit = {
        val loupanFilePath = "/dict/loupan.dict"

        val loupanDict = Source.fromInputStream(this.getClass().getResourceAsStream(loupanFilePath))
                .getLines().map(line => {
            val values = line.split(",")
            (values(0).trim, values(1).trim)
        }).toArray.groupBy(_._1).map(e => (e._1, e._2.map(_._2)))

        val loupanDictMap = Source.fromInputStream(this.getClass().getResourceAsStream(loupanFilePath))
                .getLines().map(line => {
            val values = line.split(",")
            (values(1).trim, values(0).trim)
        }).toMap

        loupanDictMap.foreach(r => println(r))

    }

}
