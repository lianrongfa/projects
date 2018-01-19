package cn.lian.service.impl;

import cn.lian.entity.User;
import cn.lian.mapper.UserMapper;
import cn.lian.service.HelloSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by lianrongfa on 2018/1/19.
 */
@Service
public class HelloServiceImpl implements HelloSerivce{
    @Autowired
    private UserMapper mapper;

    @Transactional
    @Override
    public void insert() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-",""));
        mapper.insert(user);
    }
}
