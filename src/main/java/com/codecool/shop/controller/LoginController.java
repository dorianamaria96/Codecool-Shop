package com.codecool.shop.controller;

import com.codecool.shop.config.ConnectionProperties;
import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String message = req.getParameter("message");
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("message", message);
            engine.process("login.html", context, resp.getWriter());
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(databaseManager.getUser(username)){
                User user = databaseManager.getUserObject(username);
                Security security = new Security();
                if(security.verifyPassword(password, user.getPassword())){
                    HttpSession session=request.getSession();
                    session.setAttribute("username",username);
                    logger.info("User with name: {} is logged in!", username);
                    response.sendRedirect("/");
                    return;
                }
                String message = "wrong password, try again!";
                response.sendRedirect("/login?message=" + message);
                logger.info("Login with password: {} and username: {} failed!", password, username);


            }
            String message = "wrong username, try again!";
            logger.info("Login with username: {} failed!", username);

        }


    }

