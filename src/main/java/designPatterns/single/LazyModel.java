package designPatterns.single;

import java.lang.reflect.Constructor;
import java.util.concurrent.*;

/**
 * @author 🦑bys
 * @date 2021/6/7 11:24
 */
public class LazyModel {
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 50, 5000, TimeUnit.SECONDS,
            new SynchronousQueue<>(), Executors.defaultThreadFactory());


    public static void main(String[] args) {
        // 测试多线程情况下，Bean1基本写法的线程不安全
//        for (int i = 0; i < 100; i++) {
//            poolExecutor.execute(() -> {
//                Bean1 bean1 = Bean1.getInstance();
//                System.out.println(bean1);
//            });
//        }

        // 测试静态内部类反射创建多实例
        try {
            Constructor<Bean3> declaredConstructor = Bean3.class.getDeclaredConstructor(null);
            // 强行访问私有方法
            declaredConstructor.setAccessible(true);
            Bean3 obj1 = declaredConstructor.newInstance();
            Bean3 obj2 = Bean3.getInstance();
            System.out.println(obj1);
            System.out.println(obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 基本写法，线程不安全
 */
class Bean1 {
    private static Bean1 bean = null;

    private Bean1() {
    }

    public static Bean1 getInstance() {
        if (bean == null) {
            bean = new Bean1();
        }
        return bean;
    }
}

/**
 * 双重检查锁
 */
class Bean2 {
    private volatile static Bean2 bean = null;

    private Bean2() {
    }

    public static Bean2 getInstance() {
        if (bean == null) {
            synchronized (Bean2.class) {
                if (bean == null) {
                    bean = new Bean2();
                }
            }
        }
        return bean;
    }
}

/**
 * 静态内部类
 */
class Bean3 {
    private Bean3 (){}
    public static Bean3 getInstance() {
        return bbb.bean3;
    }

    private static class bbb {
        private static Bean3 bean3 = new Bean3();
    }
}

enum Bean4 {
    INSTANCE;

    public static Bean4 getInstance(){
        return INSTANCE;
    }
}