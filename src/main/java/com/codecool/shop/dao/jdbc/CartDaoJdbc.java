package com.codecool.shop.dao.jdbc;

import com.codecool.shop.config.ConnectionProperties;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.ArrayUtils;


import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class CartDaoJdbc implements CartDao, DaoJdbc {
    private static final Logger logger = LoggerFactory.getLogger(CartDaoJdbc.class);


    private static CartDaoJdbc instance = null;
    private DataSource dataSource;


    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }

    private CartDaoJdbc() {
    }

    @Override
    public void createCart(Cart cart) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cart.getUser().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            cart.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            logger.error("Connecting to database from failed!");
            throw new RuntimeException(e);
        }
    }

    public void addProductToCart(int userId, int productId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart SET product_id = array_append(product_id, ?) WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(exception);
        }
    }


    @Override
    public Cart find(int userId) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Cart> getAll() {
        return null;
    }


    public Map<Product, Integer> getAllProductsFromCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
           /* String sql = "SELECT products.*,\n" +
                    "        cart.product_id as list\n" +
                    "FROM products\n" +
                    "JOIN cart on products.id = ANY(cart.product_id)\n" +
                    "WHERE cart.user_id = ?\n" +
                    "group by id, name, description, price, currency, image, product_category_id, supplier_id, cart.product_id";
           */
            String sql = """ 
                            SELECT products.*,
                                   cart.product_id as list
                            FROM products
                            JOIN cart on products.id = ANY(cart.product_id)
                            WHERE cart.user_id = ?
                            group by id, name, description, price, currency, image, product_category_id, supplier_id, cart.product_id""";

            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setInt(1, userId);
            ResultSet resultSet = prepareStatement.executeQuery();
            Map<Product, Integer> result = new HashMap<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String currency = resultSet.getString("currency");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("product_category_id");
                int supplier = resultSet.getInt("supplier_id");
                Integer[] listOfIds = (Integer[]) resultSet.getArray("list").getArray();

                Product product = new Product(name, price, currency, description, image, ProductCategoryDaoJdbc.getInstance().find(category), SupplierDaoJdbc.getInstance().find(supplier));
                product.setId(id);

                int occurrences = Collections.frequency(List.of(listOfIds), id);
                result.put(product, occurrences);

                System.out.println("RESULT " + result);
            }
            return result;
        } catch (SQLException exception) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(exception);
        }
    }


    public Integer[] findArray(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT product_id FROM cart WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            return (Integer[]) resultSet.getArray("product_id").getArray();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public Integer[] removeProduct(int userId, int productId) {
        Integer[] productsId = findArray(userId);

        List<Integer> productsIdAsList = new ArrayList<>(Arrays.asList(productsId));

        for (Integer integer : productsId ) {
            if (integer == productId) {
                productsIdAsList.remove(integer);
                break;
            }
        }

        Integer[] arr = new Integer [productsIdAsList.size()];

        productsIdAsList.toArray(arr);
        return arr;
    }

    public List<Product> updateCart( int productId, int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                        UPDATE cart SET product_id = ? WHERE user_id = ?
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);

            Integer[] productIdArray = removeProduct(userId, productId);

            Array listOfProducts = conn.createArrayOf("int", productIdArray);
            statement.setArray(1, listOfProducts);
            statement.setInt(2, userId);

            statement.executeUpdate();

            return null;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


