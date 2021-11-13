package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.ReportDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Report;

import java.math.BigDecimal;

public class ReportDaoImpl implements ReportDao {
    private static final String REPORT_BY_ID_SQL = Resourcer.getString("dao.report.id.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;
    private UserDao userDao;

    public ReportDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, UserDao userDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.userDao = userDao;
    }

    @Override
    public Report findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(reportRowMapper, REPORT_BY_ID_SQL, id.toString()).get(0);
    }

    private final RowMapper<Report> reportRowMapper = (row) -> new Report(
            ((BigDecimal) row.get(Resourcer.getString("dao.report.column.id"))).longValue(),
            userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.report.column.from_id"))).longValue()),
            userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.report.column.to_id"))).longValue()),
            row.get(Resourcer.getString("dao.report.column.text")).toString()
    );
}
