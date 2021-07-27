package juc.atomic;

/**
 * @author 🦑bys
 * @date 2021/7/22 17:18
 */
public class A {
    /**
     *
     */
    private String a;

    public A(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}
