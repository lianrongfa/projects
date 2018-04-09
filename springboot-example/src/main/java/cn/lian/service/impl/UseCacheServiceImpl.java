package cn.lian.service.impl;

import cn.lian.entity.Test;
import cn.lian.mapper.TestMapper;
import cn.lian.service.UseCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by lianrongfa on 2018/4/9.
 */
@Service
public class UseCacheServiceImpl implements UseCacheService {

    @Autowired
    private TestMapper mapper;


    @Override
    @Cacheable(value="testCache",key="#id")
    public Test selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }
}
