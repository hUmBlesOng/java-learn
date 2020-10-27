package fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 🐥bys
 * @date 2020/10/23 15:53
 */
public class ConstructorDemo {
    public static void main(String[] args) {
        // 构造器
        Constructor1();
    }

    /**
     * getConstructor()方法 可以获取类的public构造方法
     * getDeclaredConstructor()方法 可以获取public、private、product构造方法，需要搭配setAccessible(true)使用，来取消封装
     */
    private static void Constructor1() {
        try {
            Class<?> catClass = Class.forName("fanshe.Cat");

            // 无参构造
            Constructor<?> constructor1 = catClass.getConstructor();
            Object cat1 = constructor1.newInstance();
            System.out.println(cat1);

            // 有参构造
            Constructor<?> constructor2 = catClass.getConstructor(String.class, String.class, String.class);
            Object cat2 = constructor2.newInstance("NO1", "10", "fishman");
            System.out.println(cat2);

            // 私有有参构造，需要调用
            Constructor<?> constructor3 = catClass.getDeclaredConstructor(String.class, String.class);
            // 设置是否取消封装，取消封装后就可以调用私有属性了，包括（构造，方法，参数），
            // 这个是取消java 的权限控制，而不是将其访问权限改为public
            constructor3.setAccessible(true);
            Object cat3 = constructor3.newInstance("NO2", "11");
            System.out.println(cat3);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
