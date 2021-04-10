/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.daos;

import hoangddh.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import hoangddh.dtos.User;

/**
 *
 * @author Asus
 */
public class UserDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public User checkLogin(String userID, String password) throws Exception {
        User user = null;
        try {
            String sql = "select Name,Email,Role from tblUsers where UserID = ? AND Password = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                String fullName = rs.getString("Name");
                String email = rs.getString("Email");
                user = new User(userID, email, fullName, role);
            }
        } finally {
            closeConnection();
        }
        return user;
    }

    public User checkLoginByEmail(String email) throws Exception {
        User user = null;
        try {
            String sql = "select UserID,Name,Email,Role from tblUsers where Email = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String role = rs.getString("Role");
                String fullName = rs.getString("Name");
                user = new User(userID, email, fullName, role);
            }
        } finally {
            closeConnection();
        }
        return user;
    }

}
