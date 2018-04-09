package cn.lian.service;

import cn.lian.entity.User;

/**
 * Created by lianrongfa on 2018/1/19.
 */
public interface HelloSerivce {
    void insert();
    User selectByPrimaryKey(String id);
    void checkN_1();
}
