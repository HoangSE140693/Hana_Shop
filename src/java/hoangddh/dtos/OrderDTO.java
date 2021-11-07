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
public class OrderDTO implements Serializable {

    private String orderID, customerID, dateOrder;
    private float total;
    private boolean status;

    public OrderDTO(String orderID, String customerID, String dateOrder, float total, boolean status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.dateOrder = dateOrder;
        this.total = total;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public OrderDTO(String orderID, String customerID, String dateOrder, float total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.dateOrder = dateOrder;
        this.total = total;
    }

}
