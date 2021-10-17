package ru.rsreu.harbor.datalayer.oracledb;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoOracleDbImpl implements UserDao {
    private static final String USER_BY_LOGIN_SQL = Resourcer.getString("dao.user.login.sql");

    private final JdbcClient client;

    public UserDaoOracleDbImpl(JdbcClient client) {
        this.client = client;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try {
            List<Map<String, Object>> queryResult = this.client.executeQuery(USER_BY_LOGIN_SQL, login);
            if (!queryResult.isEmpty()) {
                user = extractUser(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User extractUser(Map<String, Object> row) {
        return new User(row.get(Resourcer.getString("dao.user.column.login")).toString(),
                row.get(Resourcer.getString("dao.user.column.password")).toString(),
                ((BigDecimal) row.get(Resourcer.getString("dao.user.column.roleId"))).intValueExact());
    }
}
