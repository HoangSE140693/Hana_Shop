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
public class UpdateHistoryDTO implements Serializable {

    private int ID;
    private String dateUpdate, userID, productID;

    public UpdateHistoryDTO(int ID, String dateUpdate, String userID, String productID) {
        this.ID = ID;
        this.dateUpdate = dateUpdate;
        this.userID = userID;
        this.productID = productID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

}
