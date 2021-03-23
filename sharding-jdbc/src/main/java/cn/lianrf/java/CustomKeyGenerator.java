package cn.lianrf.java;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 自定义sharding-jdbc主键生成器
 *
 * 新建META-INF/services文件夹及文件org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator
 * 结构如下：
 * resources
 *   |MATA-INF
 *     |services
 *       |org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator
 * 文件内容如下：
 * cn.lianrf.java.CustomKeyGenerator
 *
 * @version: v1.0
 * @date: 2021/3/23
 * @author: lianrf
 */
public class CustomKeyGenerator implements ShardingKeyGenerator {

    private final AtomicInteger act=new AtomicInteger();

    private Properties properties=new Properties();

    @Override
    public Comparable<?> generateKey() {
        return act.incrementAndGet();
    }

    @Override
    public String getType() {
        return "customKey";
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties=properties;
    }
}
