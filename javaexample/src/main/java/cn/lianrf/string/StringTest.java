package cn.lianrf.string;

import java.math.BigDecimal;

/**
 * Created by lianrongfa on 2018/1/17.
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = new String("haha");
        String s2 ="haha";
        System.out.println(s1.intern()==s2);
    }

    public static boolean test(){
        Integer s=15;

        try {
            return false;
        } finally {
            return true;
        }
    }
}
enum Aa{
    ff{public int get(){
        return 0;
    }},ff1;

    private int i;
    public static final String SEQUENCE_NAME="HD_SQ_N_CY";
}