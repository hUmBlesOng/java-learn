package executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 🐥bys
 * @date 2020/11/9 15:41
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) {
        //开启3个线程
        ExecutorService exs = Executors.newFixedThreadPool(50);
        try {
            int taskCount = 100;
            // 结果集
            List<Integer> list = new ArrayList<Integer>();
            List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();

            // 1.定义CompletionService
            CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exs);

            long start = System.currentTimeMillis();

            // 2.添加任务
            for (int i = 0; i < taskCount; i++) {
                Future<Integer> future = completionService.submit(new Task(i + 1));
                futureList.add(future);
            }

            // 3.获取结果
            for (int i = 0; i < taskCount; i++) {
                Integer result = completionService.take().get();
                System.out.println("任务i==" + result + "完成!" + new Date());
                list.add(result);
            }

            long end = System.currentTimeMillis();

            System.out.println(end - start + "毫秒");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            exs.shutdown();
        }

    }

    static class Task implements Callable<Integer> {
        Integer i;

        public Task(Integer i) {
            super();
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
//            if(i==5) {
//                Thread.sleep(5000);
//            }else{
            Thread.sleep(1000);
//            }
            System.out.println("线程：" + Thread.currentThread().getName() + "任务i=" + i + ",执行完成！");
            return i;
        }

    }
}
