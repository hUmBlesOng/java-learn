package java8stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author bys
 * @date 2020/8/21 10:53
 */
public class PredicateDemo {

    public static List<Flower> filterFlower(List<Flower> flowers, Predicate<Flower> p) {
        List<Flower> resList = new ArrayList<>();
        for (Flower flower : flowers) {
            if (p.test(flower)) {
                resList.add(flower);
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        List<Flower> flowerList = Arrays.asList(new Flower("red", 6), new Flower("yellow", 7), new Flower("pink", 8));


        // 普通方式
        List<Flower> flowerList1 = filterFlower(flowerList, Flower::isRed);
        flowerList1.forEach(System.out::print);
        System.out.println();

        List<Flower> flowerList2 = filterFlower(flowerList, Flower::isLowPrice);
        flowerList2.forEach(System.out::print);
        System.out.println();


        // lambda方式
        List<Flower> flowerList3 = filterFlower(flowerList, f -> "red".equals(f.getColor()));
        flowerList3.forEach(System.out::print);
        System.out.println();

        List<Flower> flowerList4 = filterFlower(flowerList, f -> f.getPrice() < 8);
        flowerList4.forEach(System.out::print);
        System.out.println();
    }

}

class Flower {
    private String color;

    private Integer price;

    public Flower(String color, Integer price) {
        this.color = color;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public static boolean isRed(Flower flower) {
        return "red".equals(flower.getColor());
    }

    public static boolean isLowPrice(Flower flower) {
        return flower.getPrice() < 8;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
