package algorithm.LRU;

import org.junit.Test;

/**
 * @Author shaoxubao
 * @Date 2019/8/7 16:53
 *
 * LRU 测试
 */
public class LRUTest {

    @Test
    public void testLRU() {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        System.out.println(cache.get(2));
        cache.printCache(cache);

        cache.put(4, 4);
        System.out.println(cache.get(4));
        cache.printCache(cache);

    }

}
