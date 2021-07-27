package juc.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 🦑bys
 * @date 2021/7/22 18:03
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        // 创建AtomicStampedReference对象，持有对象的引用，初始为10，版本为0
        AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(10, 0);

        Integer oldRef = asr.getReference();
        int oldStamp = asr.getStamp();

        //尝试以CAS方式更新引用对象，并将版本号+1
        System.out.println(asr.compareAndSet(oldRef, 20, oldStamp, oldStamp + 1));
        System.out.println(asr.getReference());
        System.out.println(asr.getStamp());

    }
}
