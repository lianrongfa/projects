package cn.lianrf.var;

/**
 * @version: v1.0
 * @date: 2020/11/27
 * @author: lianrf
 */
public class AppTest {


    /**
     * var 关键词
     * 用于声明一个变量，声明式必须初始化，只能用于局部变量中，不能用于lambda表达式中使用
     * @param args
     */
    public static void main(String[] args) {
        var a="String";

        var b=new Runnable(){
            @Override
            public void run() {

            }
        };
    }
}
