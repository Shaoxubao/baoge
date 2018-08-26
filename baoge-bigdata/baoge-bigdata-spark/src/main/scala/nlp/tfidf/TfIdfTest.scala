/**
  * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
  * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
  * Date:   2018/7/25
  */

package nlp.tfidf

import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.{SparkConf, SparkContext}
import rdd.BaseSparkSession

object TfIdfTest extends BaseSparkSession {
    def main(args: Array[String]): Unit = {

        val sentenceData = sparkSession.createDataFrame(Seq(
            (0, "中国 美国 伊拉克 中国"),
            (0, "中国 是 基建狂魔"),
            (1, "Logistic regression models")
        )).toDF("label", "sentence")

        val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
        val wordsData = tokenizer.transform(sentenceData)

        val hashingTF = new HashingTF()
                .setInputCol("words").setOutputCol("rawFeatures")
                .setNumFeatures(20000)  // 设置哈希桶的数量
        val featurizedData = hashingTF.transform(wordsData)
        // [中国, 美国, 伊拉克, 中国]，(20000,[中国, 美国, 伊拉克, 中国],[2.0,1.0,1.0])
        // 其中20000为桶数，[中国, 美国, 伊拉克, 中国]为对应词的哈希值，[2.0,1.0,1.0]，代表词频。
        // IDF是一个机器学习模型，这里承担估计器的角色。
        val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
        val idfModel = idf.fit(featurizedData)
        val rescaledData = idfModel.transform(featurizedData)
        rescaledData.show(false)
        // (20000,[7282,9637,18665],[0.5753641449035617,0.6931471805599453,0.6931471805599453])
        // 将tf结果转化为了TF-IDF值，可以看到，“美国”“伊拉克”只出现了一次，值相同，中国在所有文件中出现了两次，根据idf公式其值更小一些，如果中国在第三个文件中也出现，那么其值会变为0。这就是过滤掉常用词语保留重要词语的的意义所在
    }

}
