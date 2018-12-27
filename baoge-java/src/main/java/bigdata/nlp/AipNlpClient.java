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

//        System.out.println(nlpResult);

        String content = "大多数的床头台灯都为工艺台灯，是由灯座和灯罩二部分组成。灯座由陶瓷、石料、景泰蓝、竹编、大理石等材料制作。灯罩常用玻璃、金属、塑料、织物竹藤做成，灯座和灯罩一经巧妙地，便使台灯成为美丽的艺术品。 1、光谱成分中没有紫外光和红外光。 2、光的色温应贴近自然 3、灯光为无频闪光 在选购台灯时，可随身带一个小陀螺，在灯光下旋转陀螺，如果没有产生倒转的视错觉，就说明灯具没有频闪。";
        jsonObject = client.newsSummary(content, 200, null);
        System.out.println(jsonObject);

    }

}
