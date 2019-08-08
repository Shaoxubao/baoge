package algorithm.LRU;

import org.junit.Test;

import java.util.Scanner;

/**
 * @Author shaoxubao
 * @Date 2019/8/7 16:53
 *
 * LRU 测试
 */
public class LRUTest {

    /**
     * LRU 单链表
     */
    @Test
    public void testLRUByLinkList() {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            list.add(sc.nextInt());
//            list.printAll();
//        }


        // 初始化
        list.add(1);
        list.add(2);
        list.add(3);

        list.printAll();

        // 查找元素，并将其移向表头部
        int r = (int) list.findNode( 2);
        System.out.println(r);

        list.printAll();

        // 插入大于最大容量会删除尾元素
        list.add(4);
        list.printAll();
    }

    /**
     * LRU 双链表+map
     */
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
