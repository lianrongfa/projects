package cn.lian.service.impl;

import cn.lian.core.config.datasource.DataSourceEnum;
import cn.lian.core.config.datasource.annotation.DataSource;
import cn.lian.entity.Test;
import cn.lian.entity.User;
import cn.lian.mapper.UserMapper;
import cn.lian.service.HelloSerivce;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    /**
     * 验证mybatis一级缓存。
     * spring环境中一级缓存无效，因为其使用SqlSessionTemplate重新生成了session
     * @param id
     * @return
     */
    @DataSource(DataSourceEnum.MASTER)
    public User selectByPrimaryKey(String id){
        User user = mapper.selectByPrimaryKey(id);
        User user2 = mapper.selectByPrimaryKey(id);
        return null;
    }

    @DataSource(DataSourceEnum.MASTER)
    public User selectByPrimaryKey1(String id){
        User user = mapper.selectByPrimaryKey(id);
        return user;
    }

    @DataSource(DataSourceEnum.SLAVE)
    public User selectByPrimaryKey2(String id){
        User user = mapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 验证1+N问题
     */
    @Lazy
    public void checkN_1(){
        User user = mapper.selectByPrimaryKey("1");
        System.out.println(user);
    }

}
