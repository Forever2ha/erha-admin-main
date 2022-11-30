package fun.yizhierha.common.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBooleanTypeHandler implements TypeHandler<Boolean> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        if (aBoolean){
            preparedStatement.setInt(i,0);
        }else {
            preparedStatement.setInt(i,1);
        }
    }

    @Override
    public Boolean getResult(ResultSet resultSet, String s) throws SQLException {
        String res = resultSet.getString(s);
        return !"YES".equals(res);
    }

    @Override
    public Boolean getResult(ResultSet resultSet, int i) throws SQLException {
        String res = resultSet.getNString(i);
        return !"YES".equals(res);
    }

    @Override
    public Boolean getResult(CallableStatement callableStatement, int i) throws SQLException {
        String res = callableStatement.getNString(i);
        return !"YES".equals(res);
    }
}
