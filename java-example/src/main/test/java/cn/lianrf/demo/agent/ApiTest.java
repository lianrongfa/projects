package cn.lianrf.demo.agent;

/**
 * @version: v1.0
 * @date: 2020/11/25
 * @author: lianrf
 */
public class ApiTest {

    public static void main(String[] args) throws InterruptedException {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    private void echoHi() throws InterruptedException {
        System.out.println("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }
}
