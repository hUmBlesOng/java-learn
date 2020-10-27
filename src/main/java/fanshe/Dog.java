package fanshe;

/**
 * @author 🐥bys
 * @date 2020/10/23 16:52
 */
public class Dog implements Animal {


    @Override
    public void eat() {
        System.out.println("dog eat baba");
    }

    @Override
    public void drink() {
        System.out.println("dog drink water");
    }
}
