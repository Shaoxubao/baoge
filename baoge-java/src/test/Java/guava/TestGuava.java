package guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/6/11 17:21
 */
public class TestGuava {

    @Test
    public void testSkipNulls() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        String result = Joiner.on("_").skipNulls().join(list);

        System.out.println(result);
    }

}
