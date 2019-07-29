package algorithm;

import algorithm.top_n.TopN;
import org.junit.Test;

/**
 * @Author shaoxubao
 * @Date 2019/7/16 18:17
 */
public class TestTopN {

    @Test
    public void testTopN() {
        TopN topN = new TopN();

        int[] array = {3, 6, 1, 10, 23, 66, 90, 100, 98, 99};
        topN.print(array);
        topN.findTopN(5, array);
        topN.print(array);
    }

}
