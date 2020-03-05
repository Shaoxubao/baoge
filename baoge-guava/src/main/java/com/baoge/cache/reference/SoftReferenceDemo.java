package com.baoge.cache.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/3/5 9:42
 *
 * 我从JDK1.8的API中看到:
 * 软参考对象，由垃圾收集器根据内存需求自行清除。 软引用通常用于实现内存敏感缓存。
 * 假设垃圾收集器在某个时间点确定对象是可软可达的。此时，它可以选择原子性地清除对该对象的所有软引用以及对任何其他软可到达对象的所有软引用，从这些软可达对象可以通过一系列强引用访问。
 * 同时或稍后，它将把那些新清除的、注册到引用队列中的软引用排队。在虚拟机抛出OutOfMemoryError之前，所有软引用对象可以保证被清除。
 * 否则，在清除软引用的时间或者对一组对不同对象的引用将被清除的顺序没有约束。 但是，鼓励虚拟机实现偏离清除最近创建或最近使用的软参考。
 * 此类的直接实例可用于实现简单的缓存; 此类或派生子类也可用于较大的数据结构以实现更复杂的高速缓存。 只要一个软引用的引用是强可及的，
 * 也就是说，在实际使用中，软引用就不会被清除。因此，例如，一个复杂的缓存可以通过保持对这些条目的强引用来防止最近使用的条目被丢弃，剩下的条目将由垃圾收集器自行丢弃。
 *
 * 我们可以得出当发生GC时，虚拟机可能会回收SoftReference对象所指向的软引用，为什么用"可能"两个字呢，
 * 从第二段的描述中"鼓励虚拟机实现偏离清除最近创建或最近使用的软参考"意味着近期创建的软引用或近期使用的软引用不会被GC回收，
 * 除非一种OOM情况的发生(从"在虚拟机抛出OutOfMemoryError之前，所有软引用对象可以保证被清除"可以得出)。
 * 从第三段的"只要一个软引用的引用是强可及的，也就是说，在实际使用中，软引用就不会被清除"中，我们可以了解到，
 * 若发生FULL GC的时候或者OOM时，该软引用对象由一个强引用指向，那么这个软引用就不会被垃圾回收器所回收 {@link SoftReferenceDemo2}
 */
public class SoftReferenceDemo {

    public static SoftReference<Student> studentSoftRef = new SoftReference<>(new Student(1, "张三"));

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (studentSoftRef.get() == null) {
                        System.out.println("===" + studentSoftRef.get() + "===");
                        break;
                    } else {
                        System.out.println("===" + studentSoftRef.get() + "===");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                int index = 1;
                while (true) {
                    list.add((index++) + "");
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
