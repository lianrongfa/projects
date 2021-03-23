package cn.lianrf.java;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * for java
 * @version: v1.0
 * @date: 2021/3/22
 * @author: lianrf
 */
public class JavaAppTest {
    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/db0?useSSL=false");
        dataSource1.setUsername("shard");
        dataSource1.setPassword("123");
        dataSourceMap.put("db0", dataSource1);

        // 配置第 2 个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://127.0.0.1:3306/db1?useSSL=false");
        dataSource2.setUsername("shard");
        dataSource2.setPassword("123");
        dataSourceMap.put("db1", dataSource2);


        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("order","db${0..1}.order");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "db${id % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_num", "order"));



        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 省略配置order_item表规则...
        // ...

        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

        //insert(dataSource);
        selectALL(dataSource);


    }

    private static void selectALL(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select id from `order` order by id limit 20 ");

        while (resultSet.next()){
            System.out.print("id = " + resultSet.getObject(1));
            System.out.println();
        }
        connection.close();

    }

    private static void insert(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();

        connection.setAutoCommit(false);


        String sql="INSERT INTO `order` (id,order_num) values (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);


        for (int i = 1; i <= 10; i++) {
            statement.setLong(1,i);
            statement.setString(2,String.valueOf(i));
            statement.execute();
        }
        connection.commit();
        connection.close();
    }
}
