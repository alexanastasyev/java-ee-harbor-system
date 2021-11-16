package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.ObjectMapper;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.User;

import java.math.BigDecimal;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String USER_BY_ID_SQL = Resourcer.getString("dao.user.id.sql");
    private static final String USER_BY_LOGIN_SQL = Resourcer.getString("dao.user.login.sql");
    private static final String USER_ALL_SQL = Resourcer.getString("dao.user.all.sql");
    private static final String SAVE_USER_SQL = Resourcer.getString("dao.user.create.sql");
    private static final String UPDATE_USER_SQL = Resourcer.getString("dao.user.update.sql");
    private static final String USER_ALL_WITHOUT_ADMINS_AND_NOT_DELETED =
            Resourcer.getString("dao.user.all.notAdmins.notDeleted.sql");

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

    @Override
    public List<User> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_ALL_SQL);
    }

    @Override
    public List<User> findUsersNotDeletedWithoutAdmins() {
        return jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_ALL_WITHOUT_ADMINS_AND_NOT_DELETED);
    }

    @Override
    public void saveUser(User user) {
        this.jdbcQueryExecutor.executeTransactionalQuery(SAVE_USER_SQL, user, this.saveUserObjectMapper);
    }

    @Override
    public void updateUser(User user) {
        this.jdbcQueryExecutor.executeTransactionalQuery(UPDATE_USER_SQL, user, this.updateUserObjectMapper);
    }

    private final RowMapper<User> userRowMapper = (row) -> new User(
            ((BigDecimal) row.get(Resourcer.getString("dao.user.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.user.column.login")).toString(),
            row.get(Resourcer.getString("dao.user.column.password")).toString(),
            roleDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.roleId")))
                    .longValue()),
            statusDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.statusId")))
                    .longValue()));

    private final ObjectMapper<User> saveUserObjectMapper = user -> new String[] {
            user.getLogin(),
            user.getPassword(),
            user.getRole().getId().toString(),
            user.getStatus().getId().toString()
    };

    private final ObjectMapper<User> updateUserObjectMapper = user -> new String[] {
            user.getLogin(),
            user.getPassword(),
            user.getRole().getId().toString(),
            user.getStatus().getId().toString(),
            user.getId().toString()
    };
}
