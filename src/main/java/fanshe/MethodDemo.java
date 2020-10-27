package fanshe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 🐥bys
 * @date 2020/10/23 16:23
 */
public class MethodDemo {


    /**
     * getMethod()方法 可以获取类的public构造方法
     * getDeclaredMethod()方法 可以获取public、private、product构造方法，需要搭配setAccessible(true)使用，来取消封装
     */
    public static void main(String[] args) {
        try {
            Class<?> catClass = Class.forName("fanshe.Cat");

            // 实例化
            Object o = catClass.getDeclaredConstructor(String.class, String.class, String.class).newInstance("xy", "18", "ss");

            // 获取eat方法，invoke()方法来执行
            Method eatMethod = catClass.getMethod("eat");
            eatMethod.invoke(o);

            // 获取voice方法，invoke()方法来执行，getDeclaredMethod()可以访问私有方法
            Method voiceMethod = catClass.getDeclaredMethod("voice", String.class);
            // 设置是否取消封装，取消封装后就可以调用私有属性了，包括（构造，方法，参数），
            // 这个是取消java 的权限控制，而不是将其访问权限改为public
            voiceMethod.setAccessible(true);
            voiceMethod.invoke(o, "voice is cuteeee");

            // 获取所有方法数组
            Method[] declaredMethods = catClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if ("drink".equals(declaredMethod.getName())) {
                    declaredMethod.invoke(o);
                }
            }

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
