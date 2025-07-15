package az.cybernet.internship.dictionary.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.UUID;

// We map Java type UUID to the database JDBC type OTHER (since UUID is often handled as a specific type or OTHER in JDBC drivers, especially for Postgres).
@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(UUID.class)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        // We set the parameter as an object and specify the JDBC type as OTHER.
        ps.setObject(i, parameter, Types.OTHER);
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // Retrieve the UUID from the database by column name.
        Object object = rs.getObject(columnName);
        if (object instanceof UUID) {
            return (UUID) object;
        } else if (object != null) {
            return UUID.fromString(object.toString());
        }
        return null;
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // Retrieve the UUID from the database by column index.
        Object object = rs.getObject(columnIndex);
        if (object instanceof UUID) {
            return (UUID) object;
        } else if (object != null) {
            return UUID.fromString(object.toString());
        }
        return null;
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // Retrieve the UUID from a CallableStatement (used for stored procedures).
        Object object = cs.getObject(columnIndex);
        if (object instanceof UUID) {
            return (UUID) object;
        } else if (object != null) {
            return UUID.fromString(object.toString());
        }
        return null;
    }
}