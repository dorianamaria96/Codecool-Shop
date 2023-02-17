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

//`/api/supplier/products?supplier_id=${supplierId}`

@WebServlet(name = "supplierProductsApi", urlPatterns = {"/api/supplier/products"}, loadOnStartup = 4)

public class ApiSupplierProducts extends HttpServlet implements JSONConverter {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("vici");
        String supplierId = request.getParameter("supplier_id");
        System.out.println("supplierid  "+supplierId);
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        List<Product> products = databaseManager.getProductsBySupplier(Integer.parseInt(supplierId));
        System.out.println(products);
        PrintWriter out = response.getWriter();
        String json = gson.toJson(products);
        System.out.println(json);
        response.setContentType("api/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
