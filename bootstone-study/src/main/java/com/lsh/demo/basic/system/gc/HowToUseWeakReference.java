package com.lsh.demo.basic.system.gc;

import java.lang.ref.WeakReference;

/**
 * Created by lsh on 2019-05-13.
 * <p>
 * java.lang.ref.WeakReference 怎么用呢
 * 弱引用， 当一个对象仅仅被weak reference（弱引用）指向, 而没有任何其他strong reference（强引用）指向的时候,
 * 如果这时GC运行, 那么这个对象就会被回收，不论当前的内存空间是否足够，这个对象都会被回收。
 * <p>
 * <p>
 * Entity 的key为什么用弱引用，因为如果强引用，ThreadLocal gc回收了，但是Entity 的value得不到回收，导致内存泄露
 */
public class HowToUseWeakReference {

    private String name = "1234";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        HowToUseWeakReference how1 = new HowToUseWeakReference();

        WeakReference<HowToUseWeakReference> reference = new WeakReference<>(how1);

        System.out.println(reference.get().name);
        System.gc();

        try {
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == reference.get()) {
            System.out.println("already gc");
        } else {
            System.out.println("no gc");
        }

    }
}
