package cn.lianrf.webservice.server;

import javax.xml.ws.Endpoint;

/**
 * Created by lianrongfa on 2018/5/15.
 */
public class Bootstrap {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/say",new SayHelloImpl());
        System.out.println("success");
    }
}
