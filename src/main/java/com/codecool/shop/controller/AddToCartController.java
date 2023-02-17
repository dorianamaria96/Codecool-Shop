package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.User;
import com.codecool.shop.service.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add-to-cart"})

public class AddToCartController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddToCartController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product_id");
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        HttpSession session = req.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("username") : null;
        if (userName != null) {
            User user = databaseManager.getUserObject(userName);
            databaseManager.addProductToCart(user.getId(), Integer.parseInt(productId));
            logger.info("User with name: {} and id: {} added product with id: {} to his shopping cart", user.getName(), user.getId(), productId);
            resp.sendRedirect("/");
        }
    }


}


