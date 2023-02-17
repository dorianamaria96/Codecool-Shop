package com.codecool.shop.model;

import java.util.List;

public class Cart extends BaseModel{

    private User user;

    private List<Product> products;

    public Cart(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void addProduct(Product product) {
        products.add(product);
    }


}
