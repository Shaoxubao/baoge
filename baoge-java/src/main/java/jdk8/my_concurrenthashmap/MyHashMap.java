package jdk8.my_concurrenthashmap;

/**
 * @Author shaoxubao
 * @Date 2020/7/22 10:48
 */
public class MyHashMap<K, V> {

    /**
     * 存放node的数组
     */
    transient Node<K, V>[] table;

    static class Node<K, V> {
        final int hash;
        final K key;
        volatile V val;
        volatile Node<K, V> next;

        Node(int hash, K key, V val, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }


    private final void initTable() {
        table = (Node<K, V>[]) new Node<?, ?>[8];
    }

    public void put(K key, V value) {
        if (table == null) {
            initTable();
        }

        int hash = key.hashCode();
        int n = table.length;
        int i = hash % n;

        if (table[i] == null) {
            Node<K, V> newNode = new Node<>(hash, key, value, null);
            table[i] = newNode;
        } else {
            Node<K, V> list = table[i];
            for (Node<K, V> p = list; p != null; p = p.next) {
                if (p.next == null) {
                    p.next = new Node<>(hash, key, value, null);
                    break;
                }
            }
        }
    }

    public V get(Object key) {
        int hash = key.hashCode();
        int n = table.length;
        int i = hash % n;
        if (table[i] != null) {
            if (table[i].key == key) {
                return table[i].val;
            }
            Node<K, V> pred = table[i];
            Node<K, V> e = pred.next;
            while (e != null) {
                if (e.key == key) {
                    return e.val;
                }
                e = e.next;
            }
        }

        return null;
    }

}
