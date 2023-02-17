package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.DatabaseManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("username") : null;
        if (userName != null) {
            User user = databaseManager.getUserObject(userName);
            Map<Product, Integer> productAndAmounts = databaseManager.getAllProductsFromCart(user.getId());
            Map<Map.Entry, Double> allProductInfo = new HashMap<>();

            AtomicReference<Double> totalSum = new AtomicReference<>(0.0);
            productAndAmounts.forEach((key, value) ->
                    productAndAmounts.entrySet().forEach((newValue) ->
                            allProductInfo.put(newValue, (double) Math
                                    .round(newValue.getKey().getDefaultPrice().doubleValue() * newValue.getValue()))
                    ));

            productAndAmounts.forEach((key, value) ->
                    totalSum.updateAndGet(v -> v + key.getDefaultPrice().doubleValue() * value));

            totalSum.updateAndGet(v -> (double) Math.round(v));

            context.setVariable("allProductInfo", allProductInfo);
            context.setVariable("totalSum", totalSum);


        }
        engine.process("shopping-cart.html", context, resp.getWriter());
    }
}

