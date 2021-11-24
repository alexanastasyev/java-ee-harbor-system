package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Product;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private static final String PRODUCT_BY_ID_SQL = Resourcer.getString("dao.product.id.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    private UserDao userDao;

    private PierDao pierDao;

    public ProductDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, UserDao userDao, PierDao pierDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.userDao = userDao;
        this.pierDao = pierDao;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.productRowMapper, PRODUCT_BY_ID_SQL, id.toString()));
    }

    private final RowMapper<Product> productRowMapper = (row) -> new Product(
            ((BigDecimal) row.get(Resourcer.getString("dao.product.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.product.column.title")).toString(),
            ((BigDecimal) row.get(Resourcer.getString("dao.product.column.quantity"))).intValueExact(),
            userDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.product.column.captain_id")))
                    .longValue()).orElseThrow(IllegalArgumentException::new),
            pierDao.findById(((BigDecimal) row.get(Resourcer.getString("dao.product.column.pier_id")))
                    .longValue()).orElseThrow(IllegalArgumentException::new),
            (LocalDate) row.get(Resourcer.getString("dao.product.column.arrival_date")),
            (LocalDate) row.get(Resourcer.getString("dao.product.column.departure_date"))
    );
}
