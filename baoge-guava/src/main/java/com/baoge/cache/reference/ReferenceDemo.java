package com.baoge.cache.reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/2/29
 *
 * Java 几种引用
 *
 * vm参数：-Xmx128M -Xms64M -XX:+PrintGCDetails
 */
public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        // ===== Strong Reference
        /**
        int counter = 1;
        List<Ref> container = new ArrayList<>();

        for (; ; ) {
            int cur = counter++;
            container.add(new Ref(counter));
            System.out.println("The " + cur + "Ref will be insert into container");
            TimeUnit.MILLISECONDS.sleep(500);
        }*/

        // ===== Soft Reference:发现JVM内存快不够用的时候，引发 Soft GC
        /**
        SoftReference<Ref> softReference = new SoftReference<>(new Ref(0));

        int counter = 1;
        List<SoftReference<Ref>> container = new ArrayList<>();

        for (; ; ) {
            int cur = counter++;
            container.add(new SoftReference<>(new Ref(counter)));
            System.out.println("The " + cur + "Ref will be insert into container");
            TimeUnit.SECONDS.sleep(1); // 时间睡眠设置大点，运行不会发生OOM
        }*/

        // ===== Weak Reference:当发生GC(Major GC和Full GC)都会进行回收WeakReference
        /**
        int counter = 1;
        List<WeakReference<Ref>> container = new ArrayList<>();

        for (; ; ) {
            int cur = counter++;
            container.add(new WeakReference<>(new Ref(counter)));
            System.out.println("The " + cur + "Ref will be insert into container");
            TimeUnit.SECONDS.sleep(1); // 时间睡眠设置大点，运行不会发生OOM
        } */

        /**
        Ref ref = new Ref(10);
        SoftReference<Ref> softReference = new SoftReference<>(ref);
        ref = null;

        System.gc();

        // Active Object
        TimeUnit.SECONDS.sleep(1);
        System.out.println(ref);
        System.out.println(softReference.get().index); // 结果不会回收
    */

        /**
        Ref ref = new Ref(10);
        WeakReference<Ref> weakReference = new WeakReference<>(ref);
        ref = null;

        System.out.println(weakReference.get().index);

        System.gc();

        // Active Object
        TimeUnit.SECONDS.sleep(1);
        System.out.println(ref);
        System.out.println(weakReference.get().index); // 结果会回收
        */

        /**
        Ref ref = new Ref(10);
        ReferenceQueue<Ref> queue = new ReferenceQueue<>(); // GC完之后会记录被GC的对象PhantomReference<Ref>
        PhantomReference<Ref> phantomReference = new PhantomReference<>(ref, queue);
        ref = null;

        System.out.println(phantomReference.get()); // 输出null

        System.gc();
        Reference<? extends Ref> object = queue.remove(); // 运行程序后，用jconsole工具执行GC
        System.out.println(object);
         */

        Ref ref = new Ref(10);
        ReferenceQueue queue = new ReferenceQueue<>(); // GC完之后会记录被GC的对象PhantomReference<Ref>，可参考FileCleaningTracker类
        MyPhantomReference phantomReference = new MyPhantomReference(ref, queue, 10);
        ref = null;

        System.out.println(phantomReference.get()); // 输出null

        System.gc();
        Reference object = queue.remove();
        ((MyPhantomReference) object).doAction();

    }

    private static class MyPhantomReference extends PhantomReference<Object> {

        private int index;

        public MyPhantomReference(Object referent, ReferenceQueue<? super Object> q, int index) {
            super(referent, q);
            this.index = index;
        }

        public void doAction() {
            System.out.println("The object " + index + "is GC.");
        }
    }

    private static class Ref {
        private byte[] data = new byte[1024 * 1024]; // 1M(1024Byte(字节)=1KB, 1024KB=1MB)

        private final int index;

        public Ref(int index) {
            this.index = index;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("The index [" + index + "] will be GC.");
        }
    }

}
