package cn.lianrf.mock;

import cn.lianrf.IUserService;

/**
 * Created by lianrongfa on 2018/6/22.
 */
public class TestMock implements IUserService{
    public String insert(String msg) {
        return "mock insert:"+msg;
    }
}
