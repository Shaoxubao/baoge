package scala

import org.jsoup.Jsoup

/**
  * 将一篇文章按照标题拆解成变量
  */
object BaikeVariationJob {

    private val num = "123456789一二三四五六七八九"

    def main(args: Array[String]): Unit = {

        val title = "父母买房如何获得子女公积金？获得公积金需要什么证明？"
        val content = "<p>现在很多人购房都会使用公积金贷款，因为公积金贷款比较稳定，是国家给予职工的福利，那么父母买房如何获得子女公积金？获得公积金需要什么证明？其实父母不可以使用子女的公积金，但是具体原因还是需要依据一定的法律条件，住房资金管理规定，在职职工个人贷款的共同申请人，定是借款人的配偶，那么今天小编就来谈一谈这个问题。</p><p><img src=\"https://static-tp.fangdd.com/xfwf/FtkfixLDNcEaWocAVntbQFcwJHqL.jpg\"></p><p><strong>父母买房如何获得子女公积金？</strong></p><p>1、那虽然无法获得子女的公积金，但是公积金的具体获得方式如下：我们根据目前城市公积金退出的政策，符合购买本市以外自有住房条件的职工为本人及其配偶获得公积金。符合条件的员工是指户籍证明或在所购房屋所在城市工作。证明。购买外国房屋时，如果是全额购买房屋，需提供自己的配偶身份证，公积金卡，购房合同，房产证，户口本和户籍证明或工作证明。</p><p>2、我们参加住房公积金制度者要申请住房公积金个人购房贷款还必须符合以下条件：即申请贷款前连续缴存住房公积金的时间不少于六个月。因为，如果职工缴存住房公积金的行为不正常，时断时续，说明其收入不稳定，发放贷款后容易产生风险。</p><p>3、其配偶一方申请了住房公积金贷款，在其未还清贷款本息之前，配偶双方均不能再获得住房公积金贷款。因为，住房公积金贷款是满足职工家庭住房基本需求时提供的金融支持，是一种＂住房保障型＂的金融支持。</p><p><img src=\"https://static-tp.fangdd.com/xfwf/FghgGquEWrD4rRplqNNbBzNc_tSD.jpg\"></p><p><strong>获得公积金需要什么证明？</strong></p><p>那虽然无法获得子女的公积金，但是公积金的具体需要的证明如下：那如果员工是贷款买方，除了上述信息外，他还必须提供贷款合同和还款证明。购买房屋的工人可以一次性提取第1笔付款，或者可以按月偿还贷款。工人拿起第1笔付款后，他／她再次偿还每月付款。取款只需要提供公积金卡，身份证和还款证。</p><p>以上就是小编介绍的父母买房如何获得子女公积金？获得公积金需要什么证明？这些问题的回答，如果你觉得过于难以理解不妨再看几遍。公积金的具体使用方法还是需要具备一定条件的，自己多了解这些内容也好过吃了不了解的亏。具体的使用方法还需要依据当地的政策来定，也很感谢你的支持与阅读，如果你有什么疑问我们也将竭诚为您解答。</p>"

        val titleArr = title.split("？|\\?")
        val title_a = titleArr(0)
        val title_b = titleArr(1)
        if (titleArr.length == 2 && content.contains(title_a + "？")
                && content.contains(title_b + "？")) {
            var contentVar_a = ""
            var contentVar_b = ""
            val reg1 = "<p><strong>" + title_a + "？</strong></p>"
            val reg2 = "<p><strong>" + title_b + "？</strong></p>"
            var contentSegment = content.split(reg1)
            if (contentSegment != null && contentSegment.length == 2) {
                contentSegment = contentSegment(1).split(reg2)
                if (contentSegment != null && contentSegment.length == 2) {
                    contentVar_a = contentSegment(0)
                    contentVar_b = contentSegment(1)

                    val contentVar_b_doc = Jsoup.parse(contentVar_b)

                    val contentVar_b_eles = contentVar_b_doc.body.getElementsByTag("p")
                    // 有的需要去掉最后一段
                    if (contentVar_b_eles != null) {
                        contentVar_b = ""
                        var resultSize = 0
                        val size = contentVar_b_eles.size()
                        val segCom = contentVar_b_eles.get(contentVar_b_eles.size() - 2)
                        val com = segCom.text().substring(0, 1)
                        if (segCom.toString.contains("<strong>") && num.contains(com)) resultSize = size - 1 else resultSize = size - 2

                        for (i <- 0 to resultSize) {
                            contentVar_b = contentVar_b + contentVar_b_eles.get(i).toString
                        }
                    }

                    println("contentVar_a:" + contentVar_a + "\n")
                    println("contentVar_b:" + contentVar_b + "\n")
                }
            }

        }


    }

}
