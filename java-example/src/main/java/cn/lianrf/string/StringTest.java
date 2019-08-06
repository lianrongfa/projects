package cn.lianrf.string;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by lianrongfa on 2018/1/17.
 */
public class StringTest {
    public static void main(String[] args) {
        int a=1;
        if(a==2){}
        else if(a==1){
            System.out.println("11");
        }else if(a<2){
            System.out.println("1");
        }

    }

    private static void test1() {
        String s1 = new StringBuffer("haha").append("1").toString();

        System.out.println(s1.hashCode());
        System.out.println(s1.intern().hashCode());

        System.out.println(s1.intern()==s1);
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
class Hi{
    private int s;

    public Hi(int s) {
        this.s = s;
    }
}
