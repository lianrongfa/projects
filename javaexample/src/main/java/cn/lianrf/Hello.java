package cn.lianrf;

/**
 * Created by lianrongfa on 2018/1/2.
 */
public class Hello {

    private int a;
    private String s;

    public void test(){
        a=10;
        s="haha";
    }

    public String test2(String s){
        return s+"haha";
    }

    public static void main(String args[]){

        try {
            test2();
        } catch (Exception e) {
           e.printStackTrace();
        }

        System.out.println("hello");
    }

    public static void test2(){

        throw new RuntimeException("哈哈");

    }

}

