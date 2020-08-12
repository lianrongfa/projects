package cn.lianrf.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * @version: v1.0
 * @date: 2020/7/27
 * @author: lianrf
 */
public class ResultUtil {

    public static String build(String head,Result result){
        String re="%s(%s)";
        String s = JSONObject.toJSONString(result);
        return String.format(re,head,s);
    }
}
