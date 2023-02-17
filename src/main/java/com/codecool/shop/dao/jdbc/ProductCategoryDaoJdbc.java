package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao, DaoJdbc {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDaoJdbc.class);


    private static ProductCategoryDaoJdbc instance = null;

    private DataSource dataSource;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_categories (name, description, department) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product_categories";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            List<ProductCategory> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String department = resultSet.getString("department");

                ProductCategory category = new ProductCategory(name, description, department);
                category.setId(id);
                result.add(category);
            }
            return result;
        } catch (SQLException exception) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(exception);
        }


    }
}
