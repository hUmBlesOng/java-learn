package jvm;

/**
 * @author 🦑bys
 * @date 2021/7/22 10:43
 */
public class Test {
    public static void main(String[] args) {
        ChildClass cc = new ChildClass();
        cc.printX();
    }
}

class ParentClass {
    private int parentX;
    public ParentClass() {
        setX(100);
    }
    public void setX(int x) {
        parentX = x;
    }
}

class ChildClass extends ParentClass{
    private int childX = 1;
    public ChildClass() {}
    @Override
    public void setX(int x) {
        super.setX(x);
        childX = x;
        System.out.println("ChildX 被赋值为 " + x);
    }
    public void printX() {
        System.out.println("ChildX = " + childX);
    }

}