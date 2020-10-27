package fanshe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 🐥bys
 * @date 2020/10/23 16:16
 */
public class FieldDemo {


    /**
     * getField()方法 可以获取类的public构造方法
     * getDeclaredField()方法 可以获取public、private、product构造方法，需要搭配setAccessible(true)使用，来取消封装
     */
    public static void main(String[] args) {
        try {
            Class<?> catClass = Class.forName("fanshe.Cat");

            // 实例化
            Object o = catClass.getDeclaredConstructor(String.class, String.class, String.class).newInstance("xy", "18", "ss");

            // 获取dad参数
            Field dadField = catClass.getField("dad");
            dadField.set(o, "ss");
            // 参数值
            System.out.println(dadField.get(o));
            // 参数名
            System.out.println(dadField.getName());
            // 参数类型
            System.out.println(dadField.getType());

            System.out.println(o);

            // 获取所有参数，包括public,private,protected
            Field[] declaredFields = catClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                // 设置是否取消封装，取消封装后就可以调用私有属性了，包括（构造，方法，参数），
                // 这个是取消java 的权限控制，而不是将其访问权限改为public
                declaredField.setAccessible(true);
                declaredField.set(o, "123");
            }

            System.out.println(o);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
