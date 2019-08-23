package toword;

import org.junit.Test;
import utils.toword.WordGenerator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2019/8/23 14:18
 */
public class WordGeneratorTest {

    @Test
    public void testWordGenerator() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "啦啦啦");
        map.put("content", "明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.");
        map.put("text", "明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.");
        map.put("reason", "明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.明月松间照，清泉石上流.");

        File file = WordGenerator.createDoc(map, "test");

    }


}
