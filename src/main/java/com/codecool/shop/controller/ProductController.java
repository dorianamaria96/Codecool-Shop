package com.codecool.shop.controller;


import com.codecool.shop.model.User;
import com.codecool.shop.service.DatabaseManager;

import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("username") : null;
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("username", userName);
        context.setVariable("categories", databaseManager.getAllCategories());
        context.setVariable("products", databaseManager.getAllProducts());
        context.setVariable("suppliers", databaseManager.getAllSuppliers());
        engine.process("product/index.html", context, resp.getWriter());


    }

}
