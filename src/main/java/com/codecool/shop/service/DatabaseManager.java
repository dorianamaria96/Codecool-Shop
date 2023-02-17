package com.codecool.shop.service;

import com.codecool.shop.config.ConnectionProperties;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.codecool.shop.dao.jdbc.*;
import com.codecool.shop.model.*;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    private static DatabaseManager instance = null;
    private ProductCategoryDaoJdbc productCategoryDao;
    private ProductDaoJdbc productDao;
    private SupplierDaoJdbc supplierDao;
    private UserDaoJdbc userDao;

    private CartDaoJdbc cartDao;

    public DatabaseManager(){}

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        productCategoryDao =  ProductCategoryDaoJdbc.getInstance();
        productCategoryDao.init(dataSource);
        productDao = ProductDaoJdbc.getInstance();
        productDao.init(dataSource);
        supplierDao = SupplierDaoJdbc.getInstance();
        supplierDao.init(dataSource);
        userDao = UserDaoJdbc.getInstance();
        userDao.init(dataSource);
        cartDao = CartDaoJdbc.getInstance();
        cartDao.init(dataSource);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<Product> getProductsByCategory(int id) {
        return productDao.getByCategory(id);
    }

    public List<Product> getProductsBySupplier(int id) {
        return productDao.getBySupplier(id);
    }

    public void registerUser(User user){
        userDao.add(user);
    }


    public User getUserObject(String username) {
        return userDao.getUserObject(username);
    }
    public Boolean getUser(String user) {
        return userDao.findBy(user);
    }
//    public Boolean getUser(String user, String password) {
//        return userDao.findBy(user, password);
//    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ConnectionProperties connectionProperties = ConnectionProperties.getInstance();
        String dbName = connectionProperties.getDatabase();
        String user = connectionProperties.getUser();
        String password = connectionProperties.getPassword();
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        logger.info("Trying to connect to database");
        dataSource.getConnection().close();
        logger.info("Database Connection ok!");

        return dataSource;
    }
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void addCart(Cart cart) {
        cartDao.createCart(cart);
    }

    public void addProductToCart(int userId, int productId) {
        cartDao.addProductToCart(userId, productId);
    }

    public Map<Product, Integer> getAllProductsFromCart(int userId) {
        return cartDao.getAllProductsFromCart(userId);
    }

    public void removeProductFromCart(int productId, int userId) {
        cartDao.updateCart(productId, userId);
    }
}
