package cn.lianrf.framework.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lianrongfa on 2018/1/18.
 */
public class SNumUtils {
    public static Lock _LOCK=new ReentrantLock();

    /**
     * @param session hibernate session
     * @param sequenceName 数据库中序列名
     * @param length 长度-1
     * @param preString 前缀
     * @return
     */
    /*public static String getSequenceByName(final Session session,final String sequenceName, final String length,final String preString) {
        if (StringUtils.isBlank(sequenceName)) {
            return "";
        } else {
            try {
                _LOCK.lock();
                Query query = session.createSQLQuery("SELECT " + sequenceName + ".nextval from dual");
                BigDecimal seq = (BigDecimal) query.uniqueResult();
                if (seq != null) {
                    String s = String.valueOf(seq.longValue());
                    if (s.contains("4")) {
                        s = s.replace("4", "5");
                        final String sql = "create sequence " + sequenceName + " minvalue 0 maxvalue 9999999999 start with " + (Long.parseLong(s) + 1) + " increment by 1 cache 20";
                        final String dropsqlString = "drop sequence " + sequenceName;
                        session.doWork(new Work() {
                            @Override
                            public void execute(Connection connection) throws SQLException {
                                Statement statement = connection.createStatement();
                                statement.executeUpdate(dropsqlString);
                                statement.executeUpdate(sql);
                            }
                        });
                    }
                    return String.format(preString + "%0" + length + "d", Long.parseLong(s));
                } else {
                    return String.format(preString + "%0" + length + "d", 0);
                }
            } finally {
                _LOCK.unlock();
            }
        }
    }*/
}
