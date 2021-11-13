package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Role;

import java.math.BigDecimal;

public class RoleDaoImpl implements RoleDao {
    private static final String ROLE_BY_ID_SQL = Resourcer.getString("dao.role.id.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public RoleDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Role findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(this.roleRowMapper, ROLE_BY_ID_SQL, id.toString()).get(0);
    }

    private final RowMapper<Role> roleRowMapper = (row) -> new Role(
            ((BigDecimal) row.get(Resourcer.getString("dao.role.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.role.column.title")).toString()
    );
}
