package fanshe;

/**
 * @author 🐥bys
 * @date 2020/10/23 16:04
 */
class Cat implements Animal {

    public String dad;

    private String name;
    private String age;
    private String lover;

    public Cat() {
    }

    public Cat(String name, String age, String lover) {
        this.name = name;
        this.age = age;
        this.lover = lover;
    }

    private Cat(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void eat() {
        System.out.println("cat eat rourou");
    }

    @Override
    public void drink() {
        System.out.println("cat drink naicha");
    }

    private void voice(String str) {
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "dad='" + dad + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", lover='" + lover + '\'' +
                '}';
    }
}