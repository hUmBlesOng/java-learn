package juc.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author 🦑bys
 * @date 2021/7/23 11:20
 */
public class AtomicMarkableReferenceDemo {
    public static void main(String[] args) {
        AtomicMarkableReference<Integer> amr = new AtomicMarkableReference<>(10, false);

        Integer oldRef = amr.getReference();
        boolean oldMarked = amr.isMarked();

        //尝试以CAS方式更新引用对象，并将版本号+1
        System.out.println(amr.compareAndSet(oldRef, 20, oldMarked, !oldMarked));
        System.out.println(amr.getReference());
        System.out.println(amr.isMarked());
    }
}
