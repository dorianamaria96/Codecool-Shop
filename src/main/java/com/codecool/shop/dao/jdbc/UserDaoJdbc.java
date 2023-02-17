package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJdbc implements DaoJdbc, UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);


    private static UserDaoJdbc instance = null;

    private DataSource dataSource;

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO user_table (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(e);
        }
    }

    public Boolean findBy(String username) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM user_table WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
            return true;

        } catch (SQLException exception) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(exception);
        }
    }


//    public Boolean findBy(String username, String password) {
//        try (Connection conn = dataSource.getConnection()) {
//            String sql = "SELECT * FROM user_table WHERE password = ? AND username = ?";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, password);
//            statement.setString(2, username);
//            ResultSet resultSet = statement.executeQuery();
//            if (!resultSet.next()) {
//                return false;
//            }
//            return true;
//
//        } catch (SQLException exception) {
//            throw new RuntimeException(exception);
//        }
//    }

    public User getUserObject(String username) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM user_table WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            int id = resultSet.getInt("id");
            String password = resultSet.getString("password");
            User user = new User(username, password);
            user.setId(id);
            return user;

        } catch (SQLException exception) {
            logger.error("Connecting to database failed!");
            throw new RuntimeException(exception);
        }
    }



    /*try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT player_name, health, attack_strength, x, y FROM player WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }
        String playerName = resultSet.getString(1);
        int health = resultSet.getInt(2);
        int attackStrength = resultSet.getInt(3);
        int x = resultSet.getInt(4);
        int y = resultSet.getInt(5);


        PlayerModel player = new PlayerModel(playerName, health, attackStrength, x, y);
        player.setId(id);
        return player;
    } catch (SQLException exception) {
        throw new RuntimeException(exception);
    }*/


    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
