package fanshe;

/**
 * 动物制造工厂
 * @author 🐥bys
 * @date 2020/10/23 15:10
 */
public class FactoryDemo {

    private FactoryDemo() {
    }

    /**
     * 普通new方法
     * @param className
     * @return
     */
    public static Animal newInstance(String className) {
        if ("cat".equals(className)){
            return new Cat();
        } else if ("dog".equals(className)) {
            return new Dog();
        }
        return null;
    }

    /**
     * 反射方法
     * @param clazz
     * @return
     */
    public static Animal newInstance2(Class clazz) {
        Animal a = null;
        try {
            a = (Animal) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return a;
    }
}
