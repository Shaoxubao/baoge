package scala.file

import java.io._

import scala.io.Source

object FileClean {

    def main(args: Array[String]): Unit = {

        val loupanFilePath = "/dict/loupan.dict"

        val loupanDict = Source.fromInputStream(this.getClass().getResourceAsStream(loupanFilePath))
                .getLines().filter(r => r.length > 6)

        val stringBuilder = new StringBuilder
        loupanDict.foreach(r => {
            stringBuilder.append(r).append("\n")
        })

        val file = new File("loupan_new.dict".trim())
        try {
            file.createNewFile()
            val p = new PrintWriter(new FileOutputStream(file.getAbsolutePath()));
            p.write(stringBuilder.toString())
            p.close()
        } catch {
            case ex: IOException => {
                ex.printStackTrace()
                throw ex
            }
        }
    }

}
