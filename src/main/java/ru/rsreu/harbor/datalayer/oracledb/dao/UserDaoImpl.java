package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.ObjectMapper;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.User;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String USER_BY_ID_SQL = Resourcer.getString("dao.user.id.sql");
    private static final String USER_BY_LOGIN_SQL = Resourcer.getString("dao.user.login.sql");
    private static final String USER_ALL_SQL = Resourcer.getString("dao.user.all.sql");
    private static final String SAVE_USER_SQL = Resourcer.getString("dao.user.create.sql");
    private static final String UPDATE_USER_SQL = Resourcer.getString("dao.user.update.sql");
    private static final String USER_ALL_WITHOUT_ADMINS_AND_NOT_DELETED =
            Resourcer.getString("dao.user.all.notAdmins.notDeleted.sql");
    private static final String USER_ALL_EXCLUDE_USER =
            Resourcer.getString("dao.user.all.notAdminsAndModerators.notDeleted.excludeUser.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    private RoleDao roleDao;

    private StatusDao statusDao;

    public UserDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, RoleDao roleDao, StatusDao statusDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public Optional<User> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_BY_ID_SQL, id.toString()));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_BY_LOGIN_SQL, login));
    }

    @Override
    public List<User> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_ALL_SQL);
    }

    @Override
    public List<User> findAllNotDeletedWithoutAdmins() {
        return jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_ALL_WITHOUT_ADMINS_AND_NOT_DELETED);
    }

    @Override
    public List<User> findAllNotDeletedWithoutAdminsAndModeratorsExcludeUser(User user) {
        return this.jdbcQueryExecutor.executeQuery(this.userRowMapper, USER_ALL_EXCLUDE_USER, user.getId().toString());
    }

    @Override
    public void save(User user) {
        this.jdbcQueryExecutor.executeTransactionalQuery(SAVE_USER_SQL, user, this.saveUserObjectMapper);
    }

    @Override
    public void update(User user) {
        this.jdbcQueryExecutor.executeTransactionalQuery(UPDATE_USER_SQL, user, this.updateUserObjectMapper);
    }

    private final RowMapper<User> userRowMapper = (row) -> new User(
            ((BigDecimal) row.get(Resourcer.getString("dao.user.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.user.column.login")).toString(),
            row.get(Resourcer.getString("dao.user.column.password")).toString(),
            roleDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.roleId")))
                    .longValue()).orElseThrow(IllegalArgumentException::new),
            statusDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.user.column.statusId")))
                    .longValue()).orElseThrow(IllegalArgumentException::new));

    private final ObjectMapper<User> saveUserObjectMapper = user -> new String[]{
            user.getLogin(),
            user.getPassword(),
            user.getRole().getId().toString(),
            user.getStatus().getId().toString()
    };

    private final ObjectMapper<User> updateUserObjectMapper = user -> new String[]{
            user.getLogin(),
            user.getPassword(),
            user.getRole().getId().toString(),
            user.getStatus().getId().toString(),
            user.getId().toString()
    };
}
