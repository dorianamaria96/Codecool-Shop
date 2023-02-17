package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao, DaoJdbc {
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoJdbc.class);

    private static SupplierDaoJdbc instance = null;
    private DataSource dataSource;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM supplier";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Supplier supplier = new Supplier(name, description);
                supplier.setId(id);
                result.add(supplier);
            }
            return result;
        } catch (SQLException exception) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(exception);
        }
    }
}
