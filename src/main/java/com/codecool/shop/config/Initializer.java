package com.codecool.shop.config;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseManager db = DatabaseManager.getInstance();
        try {
            db.setup();
        } catch (SQLException e) {
            logger.error("Setting up the database manager failed!");
            throw new RuntimeException(e);
        }

    }
}
