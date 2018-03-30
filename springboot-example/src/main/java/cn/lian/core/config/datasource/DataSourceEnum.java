package cn.lian.core.config.datasource;

/**
 * Created by lianrongfa on 2018/2/5.
 */
public enum DataSourceEnum {
    MASTER("masterDataSource",true),
    SLAVE("slaveDataSource",false);

    //数据源名称
    private String name;
    //是否为默认数据库
    private boolean master;

    DataSourceEnum(String name, boolean master) {
        this.name = name;
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public static String getDefault(){
        String nameKey="";
        for (DataSourceEnum item:DataSourceEnum.values()) {
            if(item.isMaster()){
                nameKey=item.getName();
                break;
            }
        }
        return nameKey;
    }

}
