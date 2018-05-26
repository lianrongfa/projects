package cn.lianrf.webservice.server;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lianrongfa on 2018/5/15.
 */
@WebService
public class SayHelloImpl implements ISayHello {
    @Override
    public String say1(String msg) {
        return "Hi :"+"msg";
    }

    @Override
    public List<Object> say2(String msg) {

        List strings = new ArrayList();
        strings.add(msg);
        return strings;
    }

    @Override
    public Map<String,String> say3(String msg) {
        HashMap<String, String> map = new HashMap<>();
        map.put(msg,msg);
        return map;
    }


}
