package reference;

import reference.phantom.PhantomRefDemo2;

import java.lang.ref.WeakReference;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/18
 */
public class WeakRefDemo2 {

    public static void main(String[] args) {
        WeakReference<PhantomRefDemo2.M> weakReference = new WeakReference<>(new PhantomRefDemo2.M());

        System.out.println(weakReference.get());

        System.gc();

        System.out.println(weakReference.get());

        ThreadLocal<PhantomRefDemo2.M> threadLocal = new ThreadLocal<>();
        threadLocal.set(new PhantomRefDemo2.M());
        threadLocal.remove();

    }

}
