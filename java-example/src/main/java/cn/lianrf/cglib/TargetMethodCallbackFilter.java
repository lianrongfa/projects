package cn.lianrf.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 回调方法过滤
 * Created by lianrongfa on 2018/2/24.
 */
public class TargetMethodCallbackFilter implements CallbackFilter{
    /**
     * 过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
     */
    @Override
    public int accept(Method method) {
        String name = method.getName();
        if("show1".equals(name)){
            return 0;
        }else if("show2".equals(name)){
            return 1;
        }
        return 2;
    }
}
