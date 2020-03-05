package com.baoge.cache.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/3/5 9:59
 * <p>
 * 总结一下:
 * 1.当发生GC时，虚拟机可能会回收SoftReference对象所指向的软引用，是否被回收取决于该软引用是否是新创建或近期使用过。
 * 2.在虚拟机抛出OutOfMemoryError之前，所有软引用对象都会被回收。
 * 3.只要一个软引用对象由一个强引用指向，那么即使是OutOfMemoryError时，也不会被回收。
 */
public class SoftReferenceDemo2 {

    public static SoftReference<Student> studentSoftRef = new SoftReference<>(new Student(1, "张三"));

    static Student student = null;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);                // ----------------标注1-------------
                    synchronized (lock) {
                        System.out.println("===" + studentSoftRef.get() + "===");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                int index = 1;
                synchronized (lock) {
                    while (true) {
                        student = studentSoftRef.get(); // ----------标注2--------------
                        list.add((index++) + "");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static class Student {
        int id;
        String name;

        public Student(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[id=" + id + ",name=" + name + "]";
        }
    }
}
