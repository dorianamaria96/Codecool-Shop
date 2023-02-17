package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Product extends BaseModel {

    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;
    private String image;


    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, String image, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.supplier = supplier;
        this.productCategory = productCategory;
        this.image = image;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name=" + name +
                "defaultPrice=" + defaultPrice +
                ", defaultCurrency=" + defaultCurrency +
                ", productCategory=" + productCategory +
                ", supplier=" + supplier +
                ", image='" + image + '\'' +
                '}';
    }
}
