package juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference基本使用
 * @author 🦑bys
 * @date 2021/7/22 16:15
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<A> atomicReference1 = new AtomicReference<>();
        atomicReference1.set(new A("a1"));
        System.out.println(atomicReference1.get());

        System.out.println("-------------");

        AtomicReference<A> atomicReference2 = new AtomicReference<>(new A("a2"));
        System.out.println(atomicReference2.get());

        System.out.println("-------------");

        A a3 = new A("a3");
        AtomicReference<A> atomicReference3 = new AtomicReference<>(a3);
        // compareAndSet() 第一个参数和atomicReference3的值做比较(==比较), 如果相同则替换atomicReference3对象的值为第二个参数
        // jdk原描述: 如果当前值 ==为预期值，则将值设置为给定的更新值
        System.out.println(atomicReference3.compareAndSet(a3, new A("a31")));
        System.out.println(atomicReference3.get());

        System.out.println("-------------");

        AtomicReference<A> atomicReference4 = new AtomicReference<>(new A("a4"));
        // 原子方式设置为给定值，并返回旧值，先获取当前对象，在设置新的对象
        System.out.println(atomicReference4.getAndSet(new A("a41")));
        System.out.println(atomicReference4.get());
    }
}

