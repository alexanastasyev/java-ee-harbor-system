package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static final String USER_BY_LOGIN_SQL = Resourcer.getString("dao.user.login.sql");
    private static final String USER_BY_ID_SQL = Resourcer.getString("dao.user.id.sql");

    private final JdbcClient jdbcClient;

    private final RoleDao roleDao;

    private final StatusDao statusDao;

    public UserDaoImpl(JdbcClient client, RoleDao roleDao, StatusDao statusDao) {
        this.jdbcClient = client;
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(USER_BY_ID_SQL, id.toString());
            if (!queryResult.isEmpty()) {
                user = extractUser(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(USER_BY_LOGIN_SQL, login);
            if (!queryResult.isEmpty()) {
                user = extractUser(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User extractUser(Map<String, Object> row) {
        return new User(((BigDecimal) row.get(Resourcer.getString("dao.user.column.id"))).longValue(),
                row.get(Resourcer.getString("dao.user.column.login")).toString(),
                row.get(Resourcer.getString("dao.user.column.password")).toString(),
                this.roleDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.roleId"))).longValue()),
                this.statusDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.statusId"))).longValue()));

    }
}
