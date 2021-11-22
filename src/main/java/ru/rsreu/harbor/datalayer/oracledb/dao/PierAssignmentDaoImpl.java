package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

import java.math.BigDecimal;
import java.util.List;

public class PierAssignmentDaoImpl implements PierAssignmentDao {
    private static final String PIER_ASSIGNMENT_BY_ID_SQL = Resourcer.getString("dao.pier_assignment.id.sql");
    private static final String PIER_ASSIGNMENT_BY_PIER_ID_SQL =
            Resourcer.getString("dao.pier_assignment.pier_id.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;
    private PierDao pierDao;
    private UserDao userDao;
    private RequestStatusDao requestStatusDao;

    public PierAssignmentDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, PierDao pierDao, UserDao userDao, RequestStatusDao requestStatusDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.pierDao = pierDao;
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
    }

    @Override
    public PierAssignment findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(
                this.pierAssignmentRowMapper,
                PIER_ASSIGNMENT_BY_ID_SQL,
                id.toString())
                .get(0);
    }

    @Override
    public List<PierAssignment> findByPier(Pier pier) {
        return this.jdbcQueryExecutor.executeQuery(
                this.pierAssignmentRowMapper,
                PIER_ASSIGNMENT_BY_PIER_ID_SQL,
                pier.getId().toString());
    }

    private final RowMapper<PierAssignment> pierAssignmentRowMapper = (row) -> new PierAssignment(
            ((BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.id"))).longValue(),
            pierDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.pier_id"))).longValue()),
            userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.user_id"))).longValue()),
            requestStatusDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.request_status_id"))).longValue())
    );
}
