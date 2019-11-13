package algorithm.LRU;

import java.util.HashMap;

/**
 * @Author shaoxubao
 * @Date 2019/8/7 13:48
 *
 * LRU 最近较少使用算法
 *
 *  Double Linked List
 *   * 用了一个特别的双向的ListNode，有了head和tail，这样就大大加快了速度。
 *  主要加快的就是那个‘更新排位’的过程，找到item hashmap O(1), 做减法换位也都是O(1)
 *  Overall O(1)
 * ##### 巧妙点
 *  1. head和tail特别巧妙：除掉头和尾，和加上头和尾，就都特别快。
 *  2. 用双向的pointer: pre和next, 当需要除掉任何一个node的时候，只要知道要除掉哪一个，
 *  直接把node.pre和node.next耐心连起来就好了，node就自然而然的断开不要了。
 *  一旦知道怎么解决了，就不是很特别，并不是难写的算法:
 *  moveToHead()
 *  insertHead()
 *  remove()
 */
public class LRUCache {

    class DoubleLinkedListNode {
        private int key, val;
        DoubleLinkedListNode next, prev;

        public DoubleLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
    }

    private int capacity;                                 // 容量
    private HashMap<Integer, DoubleLinkedListNode> map;   // map O(1)
    private DoubleLinkedListNode head, tail;              // 双向链表的头尾指针

    /**
     * 构造函数初始化
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new DoubleLinkedListNode(-1, -1);
        this.tail = new DoubleLinkedListNode(-1, -1);
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DoubleLinkedListNode node = map.get(key);
        moveToHead(node);             // 将节点移向头部
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            moveToHead(map.get(key)); // 将节点移向头部
        } else {
            DoubleLinkedListNode node = new DoubleLinkedListNode(key, value);
            if (map.size() >= this.capacity) {
                DoubleLinkedListNode rm = tail.prev;
                remove(rm);
                map.remove(rm.key);
            }
            insertHead(node);
            map.put(key, node);
        }
    }

    private void moveToHead(DoubleLinkedListNode node) {
        remove(node);
        insertHead(node);
    }

    /*
        Put node to front, where the latest item is at.
     */
    public void insertHead(DoubleLinkedListNode node) {
        DoubleLinkedListNode next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    /*
        Find front and end, link them.
     */
    public void remove(DoubleLinkedListNode node) {
        DoubleLinkedListNode front = node.prev;
        DoubleLinkedListNode end = node.next;
        front.next = end;
        end.prev = front;
    }

    /**
     * 打印缓存
     * @param cache
     */
    public void printCache(LRUCache cache) {

        DoubleLinkedListNode head = cache.head;
        DoubleLinkedListNode tail = cache.tail;

        DoubleLinkedListNode next = head.next;
        while (next != tail) {
            System.out.println(next.key + "---" + next.val);

            next = next.next;
        }
    }

}
