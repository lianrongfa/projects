package com.lianrf.tierexp.examples;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * NumberTest
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/14 11:38 上午
 */
public class NumberTest {
    public static void main(String[] args) throws ParseException {

        System.out.println(13/2+1);

//        NumberFormat instance = DecimalFormat.getInstance();
        NumberFormat instance = NumberFormat.getPercentInstance();


        Number parse = instance.parse("1.234%");

int a[][]=null;
        int[] ints = a[0];

        System.out.println(27%4);
    }
}
