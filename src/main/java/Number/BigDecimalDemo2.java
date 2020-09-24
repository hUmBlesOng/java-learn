package Number;

import cn.hutool.core.convert.Convert;

import java.math.BigDecimal;

/**
 * @author bys
 * @date 2020/9/24 10:58
 */
public class BigDecimalDemo2 {

    public static void main(String[] args) {

        /*
        double类型的数据在小数大于等于15位之后会出现精度丢失的情况，莫慌
         */

        double b = 12.111111111111113;
        BigDecimal payAmount = BigDecimal.valueOf(12.111111111111113d);
        payAmount = payAmount.multiply(BigDecimal.valueOf(100d));
        double v = payAmount.doubleValue();

        System.out.println(Convert.toStr(b));
        System.out.println(Convert.toStr(payAmount));
        System.out.println(Convert.toStr(v));

        System.out.println();

        double b2 = 12.1111111111111131;
        double b3 = 12.1111111111111135;
        double b4 = 12.1111111111111139;
        double b5 = 12.111111111111113;
        double b6 = 12.111111111111117;
        System.out.println(Convert.toStr(b2));
        System.out.println(Convert.toStr(b3));
        System.out.println(Convert.toStr(b4));
        System.out.println(Convert.toStr(b5));
        System.out.println(Convert.toStr(b6));


        System.out.println();

    }
}
