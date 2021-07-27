package executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 🦑bys
 * @date 2021/7/1 23:33
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(futureTask, "aa");
        thread.start();

        System.out.println(Thread.currentThread().getName() + "++++++++++++");

        System.out.println(futureTask.get());


    }

    static class MyThread implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "---------------");
            Thread.sleep(1000);
            return 100;
        }
    }
}
