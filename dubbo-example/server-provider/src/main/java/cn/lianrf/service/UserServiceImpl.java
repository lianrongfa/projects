package cn.lianrf.service;

import cn.lianrf.IUserService;

/**
 * Created by lianrongfa on 2018/6/19.
 */
public class UserServiceImpl implements IUserService {
    public String insert(String msg) {
        return "insert success:"+msg;
    }
}
