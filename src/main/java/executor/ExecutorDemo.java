package executor;

import java.util.concurrent.*;

/**
 * 基本使用
 * @author bys
 * @date 2020/9/1 9:43
 */
public class ExecutorDemo {

    /**
     * corePoolSize 线程池的核心大小
     * maximumPoolSize 最大线程池大小，队列满后线程能容忍的最大并发数
     * keepAliveTime 空闲线程等待回收的时间限制
     * unit keepAliveTime时间单位
     * workQueue 阻塞的队列类型
     * threadFactory 创建线程的工厂，一般默认
     * handler 超出工作队列和线程池时，任务会默认抛出异常
     */
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 10, 5000, TimeUnit.SECONDS,
            new SynchronousQueue<>(), Executors.defaultThreadFactory(), new ExeHandler());


    /**
     * 线程池中线程数小于corePoolSize时，新任务将创建一个新线程执行任务，不论此时线程池中存在空闲线程；
     * 线程池中线程数达到corePoolSize时，新任务将被放入workQueue中，等待线程池中任务调度执行；
     * 当workQueue已满，且maximumPoolSize>corePoolSize时，新任务会创建新线程执行任务；
     * 当workQueue已满，且提交任务数超过maximumPoolSize，任务由RejectedExecutionHandler处理；
     * 当线程池中线程数超过corePoolSize，且超过这部分的空闲时间达到keepAliveTime时，回收该线程；
     * 如果设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize范围内的线程空闲时间达到keepAliveTime也将回收；
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            poolExecutor.execute(new PoolTask(i));
        }
    }
}

// 定义线程池任务
class PoolTask implements Runnable {

    private int numParam;

    public PoolTask (int numParam) {
        this.numParam = numParam;
    }
    @Override
    public void run() {
        try {
            System.out.println("PoolTask "+ numParam+" begin...");
//            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getNumParam() {
        return numParam;
    }
    public void setNumParam(int numParam) {
        this.numParam = numParam;
    }
}

// 定义异常处理
class ExeHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        System.out.println("自定义异常 "+executor.getCorePoolSize());
        executor.shutdown();
    }
}