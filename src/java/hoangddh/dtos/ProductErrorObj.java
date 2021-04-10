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
public class ProductErrorObj implements Serializable {

    String quantityError, priceError;

    public ProductErrorObj() {
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public String getPriceError() {
        return priceError;
    }

}
