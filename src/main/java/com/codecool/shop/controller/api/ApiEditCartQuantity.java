package com.codecool.shop.controller.api;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.DatabaseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "editCartApi", urlPatterns = {"/api/edit-quantity"}, loadOnStartup = 3)

public class ApiEditCartQuantity extends HttpServlet implements JSONConverter {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("product");
        String quantityChanger = request.getParameter("changer");

        HttpSession session = request.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("username") : null;

        System.out.println(productId);
        System.out.println(quantityChanger);
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        int userId = databaseManager.getUserObject(userName).getId();
        if (quantityChanger.equals("minus")) {
            databaseManager.removeProductFromCart(Integer.parseInt(productId), userId);
        } else if (quantityChanger.equals("plus")) {
            databaseManager.addProductToCart(userId, Integer.parseInt(productId));
        }

        //TODO response
        /*PrintWriter out = response.getWriter();
        //String json1 = gson.toJson(productId);
        String json = "{ \"productId\" : " + productId +", \"changer\" : " + quantityChanger +"}";
        //String json2 = gson.toJson(quantityChanger);
        System.out.println(json);
        //System.out.println(json2);
        response.setContentType("api/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        //out.print(json2);
        out.flush();*/
    }
}


