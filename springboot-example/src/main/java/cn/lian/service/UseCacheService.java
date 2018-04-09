package cn.lian.service;

import cn.lian.entity.Test;

/**
 * Created by lianrongfa on 2018/4/9.
 */
public interface UseCacheService {
    Test selectByPrimaryKey(String id);
}
