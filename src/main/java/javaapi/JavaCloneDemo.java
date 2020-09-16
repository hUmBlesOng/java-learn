package javaapi;

import java.io.*;

/**
 * java克隆实例
 * @author bys
 * @date 2020/9/1 22:53
 */
public class JavaCloneDemo {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Pig gxy = new Pig();
        gxy.out();

        B b = new B(gxy);
        A a = new A(100, b);

        A a1 = (A) a.clone();
        System.out.println("---浅克隆前");
        System.out.println(a);
        System.out.println(a.getB());
        System.out.println(a.getNumber());
        System.out.println(a.getB().getPig());
        System.out.println("---浅克隆后");
        System.out.println(a1);
        System.out.println(a1.getB());
        System.out.println(a1.getNumber());
        System.out.println(a1.getB().getPig());

        System.out.println();
        System.out.println("---深克隆前");
        AA aa = new AA(new BB(1));
        AA aa1 = (AA) aa.clone();
        System.out.println(aa);
        System.out.println(aa.getBb());
        System.out.println(aa.getBb().getI());
        System.out.println("---深克隆后");
        System.out.println(aa1);
        System.out.println(aa1.getBb());
        System.out.println(aa1.getBb().getI());

        System.out.println();
        Pig1 gxysz = new Pig1();
        gxysz.out();
        System.out.println("---序列化深克隆前");
        Animal animal = new Animal(gxysz);
        Zoo zoo = new Zoo(animal);
        Zoo zoo1 = (Zoo) zoo.deepClone();
        System.out.println(zoo);
        System.out.println(zoo.getAnimal());
        System.out.println(zoo.getAnimal().getPig1());

        System.out.println("---序列化深克隆后");
        System.out.println(zoo1);
        System.out.println(zoo1.getAnimal());
        System.out.println(zoo1.getAnimal().getPig1());
    }
}

/**
 * 克隆的对象必须实现Cloneable这个接口，而且需要重写clone方法
 */
class A implements Cloneable {
    private int number;
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public A(int number, B b) {
        this.number = number;
        this.b = b;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class B {
    private Pig pig;

    public Pig getPig() {
        return pig;
    }

    public void setPig(Pig pig) {
        this.pig = pig;
    }

    public B(Pig pig) {
        this.pig = pig;
    }
}

class Pig {
    void out() {
        System.out.println("哼哼~~");
    }
}

class AA implements Cloneable {
    public AA(BB bb) {
        this.bb = bb;
    }

    private BB bb;

    public BB getBb() {
        return bb;
    }

    public void setBb(BB bb) {
        this.bb = bb;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AA o = (AA) super.clone();
        o.bb = (BB) bb.clone();
        return o;
    }
}

class BB implements Cloneable {
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public BB(int i) {
        this.i = i;
    }

    private int i;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class DeepClone implements Serializable {
    private static final long serialVersionUID = -2658204965442453698L;

    protected Object deepClone() throws ClassNotFoundException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oss = new ObjectOutputStream(bos);

        oss.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}

class Zoo extends DeepClone {
    private static final long serialVersionUID = -293932665050190715L;

    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Zoo(Animal animal) {
        this.animal = animal;
    }
}

class Animal extends DeepClone {
    private static final long serialVersionUID = -293932665050190715L;
    private Pig1 pig1;

    public Pig1 getPig1() {
        return pig1;
    }

    public void setPig1(Pig1 pig1) {
        this.pig1 = pig1;
    }

    public Animal(Pig1 pig1) {
        this.pig1 = pig1;
    }
}

class Pig1 extends DeepClone {
    public void out() {
        System.out.println("哼哼哼~~");
    }
}