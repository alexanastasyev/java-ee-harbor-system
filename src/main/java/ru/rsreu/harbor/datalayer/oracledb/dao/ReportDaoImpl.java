package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.ReportDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.Report;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReportDaoImpl implements ReportDao {
    private static final String REPORT_BY_ID_SQL = Resourcer.getString("dao.report.id.sql");

    private final JdbcClient jdbcClient;
    private final UserDao userDao;

    public ReportDaoImpl(JdbcClient jdbcClient, UserDao userDao) {
        this.jdbcClient = jdbcClient;
        this.userDao = userDao;
    }

    @Override
    public Report findById(Long id) {
        Report report = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(REPORT_BY_ID_SQL, id.toString());
            if (!queryResult.isEmpty()) {
                report = this.extractReport(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    private Report extractReport(Map<String, Object> row) {
        return new Report(
                ((BigDecimal) row.get(Resourcer.getString("dao.report.column.id"))).longValue(),
                userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.report.column.from_id"))).longValue()),
                userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.report.column.to_id"))).longValue()),
                row.get(Resourcer.getString("dao.report.column.text")).toString());
    }
}
