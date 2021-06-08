package designPatterns.single;

/**
 * 饿汉式
 * @author 🦑bys
 * @date 2021/6/7 11:13
 */
public class HungryModel {

    public static void main(String[] args) {

    }
}

class Bean {

    private static Bean bean = null;

    static {
        bean = new Bean();
    }

    private Bean(){}

    public static Bean getInstance() {
        return bean;
    }
}