package cn.lianrf.mybatis.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * Created by lianrongfa on 2018/3/24.
 */
public class TestInterceptor implements Interceptor{
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    public Object plugin(Object o) {
        return null;
    }

    public void setProperties(Properties properties) {

    }
}
