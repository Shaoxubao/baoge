package scala

import org.jsoup.Jsoup

/**
  * 将一篇文章按照标题拆解成变量
  */
object BaikeVariationJob {

    private val num = "123456789一二三四五六七八九"

    def main(args: Array[String]): Unit = {

        val title = "定金可以退还吗？定金什么情况下可以退？"
        val content = "<p>我们大家在买房子的时候，有时候开发商会要求我们是需要缴纳一部分的定金的，这种情况下，一般的购房者们都会在冲动之下交了钱，但有的人过后又会后悔，不想要买这套房子了，或者开发商不想把房子卖给购房者了。那么，定金可以退还吗？定金什么情况下可以退？</p><p><img src=\"https://static-tp.com/xfwf/Fo0J-5t7CEydWA51ZAK1Cry8O5kJ.jpg\"></p><p><strong>定金可以退还吗？</strong></p><p>1、退定金是有一定条件的，即因合同条款达不成一致意见可以退，另外，必须是在认购书约定的期限内来签合同，要想退定金必须要证明这一点。购房人可以通过合同条件谈判时双方修改的记录或者双方谈话的录音来证明。</p><p>2、到目前为止，涉及到定金的法律有以下一些：《合同法》、《担保法》、《担保法司法解释》、《商品房买卖合同纠纷司法解释》等法律。</p><p>3、但是所有的法律关于定金这的规定都是一样的：给付定金的一方如果没有按照合同的约定履行相关的义务或者是债务的，无权要求返还定金；收受定金的一方不履行约定的债务的，应当双倍返还定金。</p><p>4、具体到房屋买卖上来说：出卖人通过认购、订购、预订等方式向买受人收受定金作为订立商品房买卖合同担保的，如果因当事人一方原因未能订立商品房买卖合同，应当按照法律关于定金的规定处理；因不可归责于当事人双方的事由，导致商品房买卖合同未能订立的，出卖人应当将定金返还买受人。</p><p><img src=\"https://static.com/xfwf/FkQwuEDv3YRJZ7RZwQe3hD6FXpqm.jpg\"></p><p><strong>定金什么情况下可以退？</strong></p><p>1、开发商无销售许可证或者产权证，不具备商品房销售资格，导致购房合同无法达成。</p><p>2、开发商存在欺诈行为，如已将认购房屋转售他人。</p><p>3、因合同条款达不成一致意见。如卖家对房屋信息表述模糊，合同主要条款无法确定，或者卖家对认购书中的条件，如房屋面积、价格等进行修改等。</p><p>4、买卖双方均无过错，但商品房买卖合同依旧未能订立。如因第三人的原因或自然灾害，致使该商品房项目未能建设或未能按原约定建设等。</p><p>以上即是有关于定金可以退还吗以及定金什么情况下可以退的详细解读了，希望能够对大家有所帮助。通过文章我们不难看出，即便是定金可以退，当然也是要满足一定的条件才可以，不是随随便便就能够把定金退掉的。当然，退定金的过程也非常麻烦，大家在交定金前最好考虑清楚。</p>"

        val titleArr = title.split("？")
        val title_a = titleArr(0)
        val title_b = titleArr(1)
        if (titleArr.length == 2 && content.contains(title_a + "？")
        && content.contains(title_b + "？")) {
            var contentVar_a = ""
            var contentVar_b = ""
            val reg1 = "<strong>" + title_a + "？"
            val reg2 = "<strong>" + title_b + "？"
            var contentSegment = content.split(reg1)
            contentSegment = contentSegment(1).split(reg2)
            if (contentSegment != null && contentSegment.length == 2) {
                contentVar_a = contentSegment(0)
                contentVar_b = contentSegment(1)

                contentVar_a = contentVar_a.replaceAll(title_a + "？", "")
                        .replaceAll("</strong></p>", "")

                contentVar_b = contentVar_b.replaceAll(title_b + "？", "")
                        .replaceAll("</strong></p>", "")

                val contentVar_b_doc = Jsoup.parse(contentVar_b)

                val contentVar_b_eles = contentVar_b_doc.body.getElementsByTag("p")
                // 有的需要去掉最后一段
                if (contentVar_b_eles != null) {
                    contentVar_b = ""
                    var size = contentVar_b_eles.size()
                    val segCom = contentVar_b_eles.get(contentVar_b_eles.size() -2).text()
                    val com = segCom.charAt(0)
                    if (!segCom.contains("<strong>") && (num.contains(com))) {
                        size = size -2
                    }
                    for (i <- 0 to size) {
                        contentVar_b = contentVar_b + contentVar_b_eles.get(i).toString
                    }
                }

                println("contentVar_a:" + contentVar_a + "\n")
                println("contentVar_b:" + contentVar_b + "\n")
            }

        }


    }

}
