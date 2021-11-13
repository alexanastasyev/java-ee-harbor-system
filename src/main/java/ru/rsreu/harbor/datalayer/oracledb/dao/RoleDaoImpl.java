package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.Role;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RoleDaoImpl implements RoleDao {
    private static final String ROLE_BY_ID_SQL = Resourcer.getString("dao.role.id.sql");

    private final JdbcClient jdbcClient;

    public RoleDaoImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Role findById(Long id) {
        Role role = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(ROLE_BY_ID_SQL, id.toString());
            if (!queryResult.isEmpty()) {
                role = this.extractRole(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    private Role extractRole(Map<String, Object> row) {
        return new Role(((BigDecimal) row.get(Resourcer.getString("dao.role.column.id"))).longValue(),
                row.get(Resourcer.getString("dao.role.column.title")).toString());
    }
}
