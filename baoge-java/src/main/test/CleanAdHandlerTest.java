import clean_html.AdCleanser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CleanAdHandlerTest extends SpringTestBase {

    @Autowired
    private AdCleanser adCleanser;

    @Test
    public void testCleanAd() {
        String content = "<p>不请自来15818238900，为什么方太这么难下款0914-8839781，主要分为自己问题，开发商问题，银行问题。第一个自己的问题有以下几点，第一点，收入不够，第二点，没有流水，第三点，征信不好点，第四个银行电话回访的时候你没有接好电话。第二个开发商的问题，第一点，开发商信用不好，银行不好贷款给他，第二点，开发商合作银行不多，第三点，开发商证件不齐全。第三个，银行问题，第一个，银行没有额度了，第二个，银行今年的指标完成了，这几个问题都可能导致你的房贷难下款，如果想了解更多的问题，可以关系我的今日头条，或者关注我的微信公众号，我里面有很多买房注意事项，购房技巧，希望能帮助大家，谢谢</p>";

        String[] items = content.split(",|，|。");

        String result = adCleanser.cleanContent(content);

        System.out.println(result);

    }

}
