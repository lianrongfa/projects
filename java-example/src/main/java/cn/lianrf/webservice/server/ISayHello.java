package cn.lianrf.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.Map;

/**
 * Created by lianrongfa on 2018/5/15.
 */
@WebService
public interface ISayHello {

    @WebMethod
    String say1(String msg);

    @WebMethod
    List<Object> say2(String msg);

    @WebMethod
    Map<String,String> say3(String msg);
}
