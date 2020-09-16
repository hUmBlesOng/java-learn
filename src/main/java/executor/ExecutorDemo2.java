package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 批量账户和密码的校验任务，在实际的业务中算比较常见的，通过初始化线程池，把任务提交执行，最后拿到处理结果，这就是线程池使用的核心思想：节省资源提升效率。
 * @author bys
 * @date 2020/9/1 10:28
 */
public class ExecutorDemo2 {

    public static void main(String[] args) {
        // 初始化校验任务
        List<CheckTask> checkTaskList = new ArrayList<>() ;
        initList(checkTaskList);
        // 定义线程池
        ExecutorService executorService ;
        if (checkTaskList.size() < 10){
            executorService = Executors.newFixedThreadPool(checkTaskList.size());
        }else{
            executorService = Executors.newFixedThreadPool(10);
        }
        // 批量处理
        List<Future<Boolean>> results = new ArrayList<>() ;
        try {
            results = executorService.invokeAll(checkTaskList);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 查看结果
        for (Future<Boolean> result : results){
            try {
                System.out.println(result.get());
                // System.out.println(result.get(10000,TimeUnit.SECONDS));
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
        // 关闭线程池
        executorService.shutdownNow();
    }

    private static void initList (List<CheckTask> checkTaskList){
        checkTaskList.add(new CheckTask("root","123")) ;
        checkTaskList.add(new CheckTask("root1","1234")) ;
        checkTaskList.add(new CheckTask("root2","1235")) ;
    }
}

class CheckTask implements Callable<Boolean> {
    private String userName ;
    private String passWord ;
    public CheckTask(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    @Override
    public Boolean call() throws Exception {
        // 校验账户+密码
        if ("root".equals(userName) && "123".equals(passWord)){
            return Boolean.TRUE ;
        }
        return Boolean.FALSE ;
    }
}