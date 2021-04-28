package cn.lianrf.java;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


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
        //设置主键 自增列值生成器类型，可自定义或选择内置类型：SNOWFLAKE/UUID
        orderTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("customKey","id"));

        // 配置OrderItem绑定表规则
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration("order_item","db${0..1}.order_item");
        orderItemTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id","db${order_id%2}"));


        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().addAll(Arrays.asList(orderTableRuleConfig,orderItemTableRuleConfig));

        //设置绑定，防止出现笛卡尔积
        shardingRuleConfig.getBindingTableGroups().add("order,order_item");


        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

        insertOrder(dataSource);
//        insertOrderItem(dataSource);
//        selectOrderALL(dataSource);
//        selectOrderItem(dataSource);

    }

    private static void selectOrderItem(DataSource dataSource) throws SQLException {
        long l = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select oi.id,oi.order_id from `order_item` oi inner join `order` o on o.id=oi.order_id where oi.order_id=1");

        while (resultSet.next()){
            System.out.print("id = " + resultSet.getObject(1));
            System.out.print(", order_id = " + resultSet.getObject(2));
            System.out.println();
        }

        System.out.println(System.currentTimeMillis()-l);
        connection.close();


    }

    private static void insertOrderItem(DataSource dataSource) throws SQLException {

        Connection connection = dataSource.getConnection();

        connection.setAutoCommit(false);


        String sql="INSERT INTO `order_item` (id,order_id) values (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);


        for (int i = 1; i <= 2000; i++) {
            statement.setLong(1,i);
            int m = new Random().nextInt(10)+1;
            statement.setInt(2,m);
            statement.execute();
        }
        connection.commit();
        connection.close();

    }

    private static void selectOrderALL(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select id from `order` order by id limit 20 ");

        while (resultSet.next()){
            System.out.print("id = " + resultSet.getObject(1));
            System.out.println();
        }
        connection.close();

    }

    private static void insertOrder(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();

        connection.setAutoCommit(false);


        String sql="INSERT INTO `order` (order_num) values (?)";

        PreparedStatement statement = connection.prepareStatement(sql);


        for (int i = 1; i <= 10; i++) {
//            statement.setLong(1,i);
            statement.setString(1,String.valueOf(i));
            statement.execute();
        }
        connection.commit();
        connection.close();
    }
}
