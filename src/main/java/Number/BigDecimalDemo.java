package Number;

import java.math.BigDecimal;

/**
 * @author bys
 * @date 2020/8/14 15:59
 */
public class BigDecimalDemo {

    public static void main(String[] args) {
        BigDecimal aaa = new BigDecimal(0.1f);
        System.out.println(aaa.toString());
        int a = 1;
        int b = 15;
        double k = (double)a/b;
        System.out.println(k);
//        double k = (double)a*100/b;
        double i = new BigDecimal(k).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        double i3 = new BigDecimal(k).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        double i2 = i*100;
        System.out.println(i);
        System.out.println(i*100);
        System.out.println(i2);
        System.out.println(i3);

        System.out.println(1.2 - 1);
    }
}
