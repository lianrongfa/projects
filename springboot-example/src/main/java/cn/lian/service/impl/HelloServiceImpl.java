package cn.lian.service.impl;

import cn.lian.entity.User;
import cn.lian.mapper.UserMapper;
import cn.lian.service.HelloSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    @Value("${my.values}")//自动注入yml文件中的值
    private String aa;
    @Transactional
    @Override
    public void insert() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setName("123123");
        mapper.insert(user);
        int i = 1 / 0;
    }

    public void testTransaction(){
        this.insert();
    }

}
