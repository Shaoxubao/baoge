package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    public static void main(String[] args) {

        List<String> regexList = new ArrayList<String>();
        regexList.add("<p.*?><span>来源：([\\s\\S]*?)</span></p>");
        regexList.add("<p.*?><strong>来源丨</strong>([\\s\\S]*?)</p>");


        String content = "<p><span>点击上方</span><span><strong>蓝字</strong></span><span>，关注21君~</span></p> <p><span>走进经济生活里的一切</span></p> <span>看着今天的盘面，小编不由地想起了最近很火的一句口号——<br></span> <p><span>“活下去！”</span></p> <p><strong>来源丨</strong>21世纪经济报道（ID：jjbd21）综合自证券时报网、华尔街见闻、中国基金报、券商研报、公开信息</p> <p>10月10日，美股暴跌，遭遇近几月来最惨淡一天。</p> <p>这也许是可载入美股史册的“黑色星期三”！</p> <p><img src=\"https://static-tp.fangdd.com/xfwf/FksgfxsjrmbwFrZJpfb5r3Ez_ufH.jpg\"></p>";

        String content2 = "<p><span>来源：水皮More</span><br></p> <p><strong><span>十年间，居民存款骤降</span></strong></p> <br> <p><span>近日，央行发布的最新数据显示，今</span><strong><span>年8月中国金融机构各项人民币存款余额175.24万亿元，同比增长8.3%</span></strong><span>，而此前39年里，我国金融机构各项存款余额同比增速从未跌破过9%。</span></p>";

        String cleanContent = content.replaceAll("<br>", "");

        for (String regex : regexList) {
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(cleanContent);
            while (match.find()) {
                String result = match.group(1);
                System.out.println(result);
            }
        }

    }

}
