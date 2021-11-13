package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.User;

import java.math.BigDecimal;

public class UserDaoImpl implements UserDao {
    private static final String USER_BY_ID_SQL = Resourcer.getString("dao.user.id.sql");
    private static final String USER_BY_LOGIN_SQL = Resourcer.getString("dao.user.login.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    private RoleDao roleDao;

    private StatusDao statusDao;

    public UserDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, RoleDao roleDao, StatusDao statusDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public User findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_BY_ID_SQL, id.toString()).get(0);
    }

    @Override
    public User findByLogin(String login) {
        return this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_BY_LOGIN_SQL, login).get(0);
    }

    private final RowMapper<User> userRowMapper = (row) -> new User(
            ((BigDecimal) row.get(Resourcer.getString("dao.user.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.user.column.login")).toString(),
            row.get(Resourcer.getString("dao.user.column.password")).toString(),
            roleDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.roleId"))).longValue()),
            statusDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.statusId"))).longValue()));
}
