package com.codecool.shop.controller.api;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.DatabaseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "categoryProductsApi", urlPatterns = {"/api/category/products"}, loadOnStartup = 3)

public class ApiCategoryProducts extends HttpServlet implements JSONConverter {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryId = request.getParameter("category_id");
        System.out.println(categoryId);
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        List<Product> products = databaseManager.getProductsByCategory(Integer.parseInt(categoryId));
        PrintWriter out = response.getWriter();
        String json = gson.toJson(products);
        System.out.println(json);
        response.setContentType("api/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
