package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.ObjectMapper;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.User;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PierAssignmentDaoImpl implements PierAssignmentDao {
    private static final String PIER_ASSIGNMENT_BY_ID_SQL =
            Resourcer.getString("dao.pier_assignment.id.sql");
    private static final String PIER_ASSIGNMENT_BY_PIER_ID_SQL =
            Resourcer.getString("dao.pier_assignment.pier_id.sql");
    private static final String PIER_ASSIGNMENT_BY_USER_ID_SQL =
            Resourcer.getString("dao.pier_assignment.user_id.sql");
    private static final String SAVE_PIER_ASSIGNMENT_SQL =
            Resourcer.getString("dao.pier_assignment.create.sql");
    private static final String DELETE_PIER_ASSIGNMENT_SQL =
            Resourcer.getString("dao.pier_assignment.delete.sql");
    private static final String UPDATE_PIER_ASSIGNMENT_SQL =
            Resourcer.getString("dao.pier_assignment.update.sql");
    private static final String PIER_ASSIGNMENT_ALL_SQL =
            Resourcer.getString("dao.pier_assignment.all.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;
    private PierDao pierDao;
    private UserDao userDao;
    private RequestStatusDao requestStatusDao;

    public PierAssignmentDaoImpl(
            JdbcQueryExecutor jdbcQueryExecutor,
            PierDao pierDao,
            UserDao userDao,
            RequestStatusDao requestStatusDao
    ) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.pierDao = pierDao;
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
    }

    @Override
    public Optional<PierAssignment> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(this.jdbcQueryExecutor.executeQuery(
                this.pierAssignmentRowMapper,
                PIER_ASSIGNMENT_BY_ID_SQL,
                id.toString()));
    }

    @Override
    public List<PierAssignment> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.pierAssignmentRowMapper, PIER_ASSIGNMENT_ALL_SQL);
    }

    @Override
    public List<PierAssignment> findByPier(Pier pier) {
        return this.jdbcQueryExecutor.executeQuery(
                this.pierAssignmentRowMapper,
                PIER_ASSIGNMENT_BY_PIER_ID_SQL,
                pier.getId().toString());
    }

    @Override
    public Optional<PierAssignment> findByCaptain(User captain) {
        return OptionalCreator.createOptionalObjectFromList(this.jdbcQueryExecutor.executeQuery(
                this.pierAssignmentRowMapper,
                PIER_ASSIGNMENT_BY_USER_ID_SQL,
                captain.getId().toString()));
    }

    @Override
    public void save(PierAssignment pierAssignment) {
        this.jdbcQueryExecutor.executeTransactionalQuery(
                SAVE_PIER_ASSIGNMENT_SQL,
                pierAssignment,
                this.createPierAssignmentObjectMapper
        );
    }

    @Override
    public void delete(PierAssignment pierAssignment) {
        this.jdbcQueryExecutor.executeTransactionalQuery(
                DELETE_PIER_ASSIGNMENT_SQL,
                pierAssignment,
                this.deletePierAssignmentObjectMapper
        );
    }

    @Override
    public void update(PierAssignment pierAssignment) {
        this.jdbcQueryExecutor.executeTransactionalQuery(
                UPDATE_PIER_ASSIGNMENT_SQL,
                pierAssignment,
                this.updatePierAssignmentObjectMapper);
    }

    private final RowMapper<PierAssignment> pierAssignmentRowMapper = (row) -> {
        BigDecimal pierId = (BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.pier_id"));
        return new PierAssignment(
                ((BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.id"))).longValue(),
                (pierId != null) ?
                        this.pierDao.findById(pierId.longValue()).orElseThrow(IllegalArgumentException::new)
                        : new Pier(-1L),
                userDao.findById((
                        (BigDecimal) row.get(Resourcer.getString("dao.pier_assignment.column.user_id"))
                ).longValue()).orElseThrow(IllegalArgumentException::new),
                requestStatusDao.findById(((BigDecimal) row.get(
                        Resourcer.getString("dao.pier_assignment.column.request_status_id"))
                ).longValue()).orElseThrow(IllegalArgumentException::new)
        );
    };

    private final ObjectMapper<PierAssignment> createPierAssignmentObjectMapper = (pierAssignment) -> new String[]{
            null,
            pierAssignment.getCaptain().getId().toString(),
            pierAssignment.getRequestStatus().getId().toString()
    };

    private final ObjectMapper<PierAssignment> updatePierAssignmentObjectMapper = (pierAssignment) -> new String[]{
            pierAssignment.getPier().getId().toString(),
            pierAssignment.getCaptain().getId().toString(),
            pierAssignment.getRequestStatus().getId().toString(),
            pierAssignment.getId().toString()
    };

    private final ObjectMapper<PierAssignment> deletePierAssignmentObjectMapper = (pierAssignment) -> new String[]{
            pierAssignment.getId().toString()
    };
}
