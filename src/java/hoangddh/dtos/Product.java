/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.dtos;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class Product implements Serializable {

    String productID, name, image, category, createDate, description;
    int quantity;
    float price;

    public Product(String productID, String name, String image, String category, String description, int quantity, float price) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String productID, String name, String image, String category, String createDate, String description, int quantity, float price) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.category = category;
        this.createDate = createDate;
        this.description = description;
        this.quantity = quantity;
        this.price = price;

    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
