/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Asus
 */
public class Cart implements Serializable {

    private String customerID;
    private HashMap<String, ProductInCartDTO> shoppingCart;

    public HashMap<String, ProductInCartDTO> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<String, ProductInCartDTO> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Cart(String customerID) {
        this.customerID = customerID;
        this.shoppingCart = new HashMap<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Cart(String customerName, HashMap<String, ProductInCartDTO> shoppingCart) {
        this.customerID = customerName;
        this.shoppingCart = shoppingCart;
    }

    public void addToCart(Product product, int quantity) throws Exception {
        if (this.shoppingCart.containsKey(product.getProductID())) {
            int updateQuantity = this.shoppingCart.get(product.getProductID()).getQuantityInCart() + quantity;
            this.shoppingCart.get(product.getProductID()).setQuantityInCart(updateQuantity);
        } else {
            ProductInCartDTO productInCartDTO = new ProductInCartDTO(product, quantity);
            this.shoppingCart.put(product.getProductID(), productInCartDTO);
        }
    }

    public void update(String id, int quantity) throws Exception {
        if (this.shoppingCart.containsKey(id)) {
            this.shoppingCart.get(id).setQuantityInCart(quantity);
        }
    }

    public void remove(String id) throws Exception {
        if (this.shoppingCart.containsKey(id)) {
            this.shoppingCart.remove(id);

        }
    }

    public float getTotal() throws Exception {
        float total = 0;
        for (ProductInCartDTO dto : this.shoppingCart.values()) {
            total += (dto.getProduct().getPrice() * dto.getQuantityInCart());
        }
        return total;
    }
}
