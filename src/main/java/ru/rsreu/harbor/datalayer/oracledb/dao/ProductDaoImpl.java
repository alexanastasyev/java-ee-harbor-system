package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.ObjectMapper;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Product;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private static final String PRODUCT_BY_ID_SQL = Resourcer.getString("dao.product.id.sql");
    private static final String PRODUCT_ALL_SQL = Resourcer.getString("dao.product.all.sql");
    private static final String PRODUCT_ALL_IN_HARBOUR_SQL = Resourcer.getString("dao.product.all.in.harbour.sql");
    private static final String SAVE_PRODUCT_SQL = Resourcer.getString("dao.product.save.sql");
    private static final String UPDATE_PRODUCT_SQL = Resourcer.getString("dao.product.update.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    private UserDao userDao;

    private PierDao pierDao;

    public ProductDaoImpl(JdbcQueryExecutor jdbcQueryExecutor, UserDao userDao, PierDao pierDao) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
        this.userDao = userDao;
        this.pierDao = pierDao;
    }

    @Override
    public List<Product> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.productRowMapper, PRODUCT_ALL_SQL);
    }

    @Override
    public List<Product> findAllInHarbour() {
        return this.jdbcQueryExecutor.executeQuery(this.productRowMapper, PRODUCT_ALL_IN_HARBOUR_SQL);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.productRowMapper, PRODUCT_BY_ID_SQL, id.toString()));
    }

    @Override
    public void save(Product product) {
        this.jdbcQueryExecutor.executeTransactionalQuery(SAVE_PRODUCT_SQL, product, this.saveProductObjectMapper);
    }

    @Override
    public void update(Product product) {
        this.jdbcQueryExecutor.executeTransactionalQuery(UPDATE_PRODUCT_SQL, product, this.updateProductObjectMapper);
    }

    private final RowMapper<Product> productRowMapper = (row) -> {
        Object departureDate = row.get(Resourcer.getString("dao.product.column.departure_date"));
        return new Product(
                ((Integer) row.get(Resourcer.getString("dao.product.column.id"))).longValue(),
                row.get(Resourcer.getString("dao.product.column.title")).toString(),
                ((Integer) row.get(Resourcer.getString("dao.product.column.quantity"))).intValue(),
                userDao.findById(((Integer) row.get(Resourcer.getString("dao.product.column.captain_id")))
                        .longValue()).orElseThrow(IllegalArgumentException::new),
                pierDao.findById(((Integer) row.get(Resourcer.getString("dao.product.column.pier_id")))
                        .longValue()).orElseThrow(IllegalArgumentException::new),
                ((Timestamp) row.get(Resourcer.getString("dao.product.column.arrival_date")))
                        .toLocalDateTime().toLocalDate(),
                (departureDate == null) ? null : ((Timestamp) departureDate).toLocalDateTime().toLocalDate()
        );
    };

    private final ObjectMapper<Product> saveProductObjectMapper = (product) -> new String[] {
            product.getTitle(),
            String.valueOf(product.getQuantity()),
            product.getCaptain().getId().toString(),
            product.getPier().getId().toString()
    };

    private final ObjectMapper<Product> updateProductObjectMapper = (product) -> new String[] {
            product.getId().toString()
    };
}
