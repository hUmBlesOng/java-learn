package fanshe;

/**
 * @author 🐥bys
 * @date 2020/10/23 11:11
 */
public class Demo {

    public static void main(String[] args) {
        // 普通new方式
        newWay();

        // 反射方式
        fansheWay1();
        fansheWay2();
        fansheWay3();


        Animal cat = FactoryDemo.newInstance("cat");
        System.out.println(cat);
        Animal dog = FactoryDemo.newInstance("dog");
        System.out.println(dog);

        Animal cat1 = FactoryDemo.newInstance2(Cat.class);
        System.out.println(cat1);
        Animal dog1 = FactoryDemo.newInstance2(Dog.class);
        System.out.println(dog1);
    }

    /**
     * 第一种方式(forName方式，参数为类路径)
     */
    private static void fansheWay1() {
        try {
            Class<?> i1 = Class.forName("fanshe.ITestImpl1");
            ITestImpl1 o = (ITestImpl1) i1.newInstance();
            o.start();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    /**
     * 第二种方式(new之后调用getClass()方法)
     */
    private static void fansheWay2() {

        ITestImpl1 i2 = new ITestImpl1();
        // 其中获取Class类如果用第一种方式，下面newInstance()方法需要强转(见上面)，用第二种方式不需要强转
//        Class<?> impl1Class = iTestImpl1.getClass();
        Class<? extends ITestImpl1> clz = i2.getClass();
        try {
            ITestImpl1 o = clz.newInstance();
            o.start();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void fansheWay3() {
        // 第三种方式(根据类名获取对应的Class类)
        Class<? extends ITestImpl1> clz = ITestImpl1.class;
        ITestImpl1 o = null;
        try {
            o = clz.newInstance();
            o.start();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    /**
     * 来源：https://juejin.im/post/6844903663966617607
     * 普通new方式
     */
    private static void newWay() {
        //普通写法，使用New 关键字
        ITest iTest = createITest("ITestImpl1");
        iTest.start();
        ITest iTest2 = createITest("ITestImpl2");
        iTest2.start();
    }

    private static ITest createITest(String name) {

        if ("ITestImpl1".equals(name)) {
            return new ITestImpl1();
        } else if ("ITestImpl2".equals(name)) {
            return new ITestImpl2();
        }
        return null;
    }
}

interface ITest {
    void start();
}

class ITestImpl1 implements ITest {

    @Override
    public void start() {
        System.out.println("i am test1");
    }
}

class ITestImpl2 implements ITest {

    @Override
    public void start() {
        System.out.println("i am test2");
    }
}