package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.ReportDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.ObjectMapper;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Report;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ReportDaoImpl implements ReportDao {
    private static final String REPORT_BY_ID_SQL = Resourcer.getString("dao.report.id.sql");
    private static final String REPORT_ALL_SQL = Resourcer.getString("dao.report.all.sql");
    private static final String REPORT_DELETE_ID_SQL = Resourcer.getString("dao.report.delete.id.sql");
    private static final String SAVE_REPORT_SQL = Resourcer.getString("dao.report.save.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;
    private UserDao userDao;

    public ReportDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, UserDao userDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.userDao = userDao;
    }

    @Override
    public Optional<Report> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(reportRowMapper, REPORT_BY_ID_SQL, id.toString()));
    }

    @Override
    public List<Report> findAll() {
        return this.jdbcQueryExecutor.executeQuery(reportRowMapper, REPORT_ALL_SQL);
    }

    @Override
    public void save(Report report) {
        this.jdbcQueryExecutor.executeTransactionalQuery(SAVE_REPORT_SQL, report, this.createReportObjectMapper);
    }

    @Override
    public void delete(Report report) {
        this.jdbcQueryExecutor.executeTransactionalQuery(REPORT_DELETE_ID_SQL, report, deleteReportObjectMapper);
    }

    private final RowMapper<Report> reportRowMapper = (row) -> new Report(
            ((BigDecimal) row.get(Resourcer.getString("dao.report.column.id"))).longValue(),
            userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.report.column.from_id")))
                    .longValue()).orElseThrow(IllegalArgumentException::new),
            userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.report.column.to_id")))
                    .longValue()).orElseThrow(IllegalArgumentException::new),
            row.get(Resourcer.getString("dao.report.column.text")).toString()
    );

    private final ObjectMapper<Report> createReportObjectMapper = (report) -> new String[]{
            report.getFromUser().getId().toString(),
            report.getToUser().getId().toString(),
            report.getText()
    };

    private final ObjectMapper<Report> deleteReportObjectMapper = (report) -> new String[]{
            report.getId().toString()
    };
}
