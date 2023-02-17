package com.codecool.shop.controller;

import com.codecool.shop.config.ConnectionProperties;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import com.codecool.shop.service.DatabaseManager;
import com.codecool.shop.util.Security;
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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("register.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Security security = new Security();
        String hashedPassword;
        try {
            hashedPassword = security.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Password hashing failed!");
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            logger.error("Password hashing failed!");
            throw new RuntimeException(e);
        }
        User user = new User(username, hashedPassword);
        databaseManager.registerUser(user);
        databaseManager.addCart(new Cart(user));
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        logger.info("User with name: {} is logged in!", username);
        response.sendRedirect("/");

    }


}
