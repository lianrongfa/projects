package cn.lianrf.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lianrongfa on 2018/3/24.
 */
public class TestTypeHandler extends BaseTypeHandler<String> {


    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,s+"hahahah");
    }

    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
