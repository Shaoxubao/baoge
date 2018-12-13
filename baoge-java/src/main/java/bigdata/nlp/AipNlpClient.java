package bigdata.nlp;

import com.baidu.aip.nlp.AipNlp;
import net.sf.json.JSONObject;

public class AipNlpClient {

    private static final String APP_ID = "11657544";
    private static final String API_KEY = "sZKpyhfObZGIM2jSio2lhzhl";
    private static final String SECRET_KEY = "GAHiNnywhQxQQ2MM2izgfAjX3KBytS1W";

    private static AipNlp client = null;

    static {
        client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
    }

    public static void main(String[] args) {
        String text1 = "采光 房价 购房 婚前房产";
        String text2 = "购房 婚前房产 婚前财产";
        String text3 = "购房 交房 开发商 契税 收房 物业 业主 物业管理 维修基金 保证金";
        String text4 = "房价 公积金 购房 户型";

        org.json.JSONObject jsonObject = client.simnet(text1, text2, null);

        JSONObject obj = JSONObject.fromObject(jsonObject.toString());
        NlpResult nlpResult = (NlpResult) JSONObject.toBean(obj, NlpResult.class);

        System.out.println(nlpResult);
    }

}
