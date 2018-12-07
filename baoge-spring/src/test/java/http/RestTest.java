package http;

import com.google.common.collect.Maps;
import utils.HttpClientUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestTest {

    public static void main(String[] args) throws IOException {
        String url = "http://10.0.44.238:8888/article_tag";
        Map<String, String> params = Maps.newHashMap();
        params.put("title", "媒体看房之—— 一河两岸下的府园大境");
        params.put("content", "如果说沈阳有哪个项目没有明显的短板");

        String title = "媒体看房之—— 一河两岸下的府园大境";
        String content = "如果说沈阳有哪个项目没有明显的短板";
        String dataJson = "{\"title\":" + title + ", \"content\":" +  content + "}";


        url = "https://api.mgzf.com/room-find-web/find/list";
        Map<String, String> param = new HashMap<>();
        param.put("cityId", "340");

        String result = HttpClientUtils.doPost(url, param);

        System.out.println(result);
    }


}
