package juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证
 * @author 🦑bys
 * @date 2021/7/22 17:11
 */
public class AtomicReferenceDemo2 {
    public static void main(String[] args) throws Exception {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(1000);

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new Task(atomicReference), "Thread" + i);
            list.add(t);
            t.start();
        }
        for (Thread thread : list) {
            // 等待线程死亡
            thread.join();
        }
        System.out.println(atomicReference.get());
    }
}

class Task implements Runnable {
    private AtomicReference<Integer> ref;

    Task(AtomicReference<Integer> ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        while (true) {
            // 利用CAS自旋
            Integer old = ref.get();
            if (ref.compareAndSet(old, old + 1)){
                break;
            }
        }
    }
}
