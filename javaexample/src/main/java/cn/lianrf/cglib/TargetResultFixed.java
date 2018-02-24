package cn.lianrf.cglib;

import net.sf.cglib.proxy.FixedValue;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class TargetResultFixed implements FixedValue{
    @Override
    public Object loadObject() throws Exception {
        return "FixedValue";
    }
}
