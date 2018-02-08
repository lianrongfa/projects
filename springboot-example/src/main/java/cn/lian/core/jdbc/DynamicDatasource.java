package cn.lian.core.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by lianrongfa on 2018/2/5.
 */
public class DynamicDatasource extends AbstractRoutingDataSource{

    private static final ThreadLocal<String> DATA_SOURCE_KEY =new ThreadLocal<String>();

    public static void setDataSource(String dataSourceKey){
        DATA_SOURCE_KEY.set(dataSourceKey);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceKey();
    }

    public static String getDataSourceKey(){
        String name = DATA_SOURCE_KEY.get();
            if(name==null||name=="")
                setDataSource(DataSourceEnum.getDefault());
        name = DATA_SOURCE_KEY.get();
        return name;
    }
    public static void clearDataSourceKey(){
        DATA_SOURCE_KEY.remove();
    }
}
