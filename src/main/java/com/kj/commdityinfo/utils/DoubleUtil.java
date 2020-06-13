package com.kj.commdityinfo.utils;

import java.math.BigDecimal;

/**
 * @author kj
 * @Date 2020/6/12 20:47
 * @Version 1.0
 */
public class DoubleUtil {

    public static Double plus(Double d1, Double d2){
        BigDecimal bigDecimal = new BigDecimal(d1.toString());
        BigDecimal bigDecimal2 = new BigDecimal(d2.toString());

        BigDecimal add = bigDecimal.add(bigDecimal2);
        return add.doubleValue();
    }

    public static Double reduce(Double d1, Double d2){
        BigDecimal bigDecimal = new BigDecimal(d1.toString());
        BigDecimal bigDecimal2 = new BigDecimal(d2.toString());

        BigDecimal subtract = bigDecimal.subtract(bigDecimal2);
        return subtract.doubleValue();
    }

}
