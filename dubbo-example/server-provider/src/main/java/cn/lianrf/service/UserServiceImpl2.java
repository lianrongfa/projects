package cn.lianrf.service;

import cn.lianrf.IUserService;

/**
 * Created by lianrongfa on 2018/6/19.
 *
 * 多版本
 */
public class UserServiceImpl2 implements IUserService {
    public String insert(String msg) {
        return "version2 insert success:"+msg;
    }
}
