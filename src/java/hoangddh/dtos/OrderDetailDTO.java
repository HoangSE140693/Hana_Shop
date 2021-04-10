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
public class OrderDetailDTO implements Serializable {

    private String orderDetailID, productID, orderID;
    private int quantity;
    private float price;
    private String image;
    private String productName;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public OrderDetailDTO(String orderDetailID, int quantity, float price, String image, String productName) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public OrderDetailDTO(String orderDetailID, String productID, String orderID, int quantity, float price) {
        this.orderDetailID = orderDetailID;
        this.productID = productID;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
