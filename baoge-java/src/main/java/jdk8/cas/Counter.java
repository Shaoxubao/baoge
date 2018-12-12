package jdk8.cas;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对不同的key进行计数
 */
public class Counter {

    private static final Map<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

    public static synchronized void increment(String key) {
        AtomicInteger count = counterMap.get(key);
        if (count == null) {
            count = new AtomicInteger(0);
            counterMap.put(key, count);
        }
        count.incrementAndGet();
    }

    public static void dump() {
        String logStr = "Counter:===";
        System.out.println(logStr);
        for (Map.Entry<String, AtomicInteger> entry : counterMap.entrySet()) {
            String item = logStr + " key : " + entry.getKey() + ", value : " + entry.getValue();
            System.out.println(item);
        }
    }

    public static void dumpInterval(final int miss) {
        Thread dumpThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        dump();
                        Thread.sleep(miss);
                    } catch (Exception e) {

                    }
                }
            }
        };
        dumpThread.start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Counter.increment("a");
        }

        for (int i = 0; i < 20; i++) {
            Counter.increment("b");
        }

        Counter.dump();

    }


}
