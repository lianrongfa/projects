package cn.lian.core.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import sun.misc.ProxyGenerator;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lianrongfa on 2018/2/8.
 */
@Configuration
public class DataSourceConfig {

    /**
     * masterDataSource
     * @Primary 注解用于标识默认使用的 DataSource Bean，因为有2个 DataSource Bean，该注解可用于 master
     * 或 slave DataSource Bean, 但不能用于 dynamicDataSource Bean, 否则会产生循环调用
     * @ConfigurationProperties 注解用于从 application.properties 文件中读取配置，为 Bean 设置属性
     * @return data source
     */
    @Bean("masterDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.masterDataSource")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix="spring.datasource.slaveDataSource")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DynamicDatasource dynamicDataSource(){

        //ProxyGenerator.generateProxyClass();

        System.out.println("className:"+this);
        DynamicDatasource dynamicDatasource = new DynamicDatasource();

        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceEnum.MASTER.getName(),masterDataSource());
        dataSourceMap.put(DataSourceEnum.SLAVE.getName(),slaveDataSource());

        dynamicDatasource.setTargetDataSources(dataSourceMap);

        return dynamicDatasource;
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 将路由的数据源设为SqlSessionFactoryBean的数据源
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }


    //配置事物
    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
