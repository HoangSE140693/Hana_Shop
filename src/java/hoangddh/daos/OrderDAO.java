/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.daos;

import hoangddh.dtos.OrderDTO;
import hoangddh.dtos.OrderDetailDTO;
import hoangddh.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class OrderDAO implements Serializable {

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
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int getLastOrder() throws Exception {
        int id = 0;
        try {
            String sql = "select MAX(CAST(OrderID as int)) from tblOrders";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public int getLastOrderDetail() throws Exception {
        int id = 0;
        try {
            String sql = "select MAX(CAST(OrderDetailID as int)) from tblOrderDetails";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public boolean insertNewOrder(OrderDTO odto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblOrders(OrderID, CustomerID, DateOrder,TotalMoney)"
                    + " values(?,?,?,?)";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, odto.getOrderID());
            ps.setString(2, odto.getCustomerID());
            ps.setString(3, odto.getDateOrder());
            ps.setFloat(4, odto.getTotal());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertNewOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblOrderDetails(OrderDetailID, OrderID,ProductID,Quantity,Price)"
                    + " values(?,?,?,?,?)";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderDetailDTO.getOrderDetailID());
            ps.setString(2, orderDetailDTO.getOrderID());
            ps.setString(3, orderDetailDTO.getProductID());
            ps.setInt(4, orderDetailDTO.getQuantity());
            ps.setFloat(5, orderDetailDTO.getPrice());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDTO> getListOrderByUserID(String userID) throws Exception {
        List<OrderDTO> result = null;
        try {
            String sql = "select OrderID,DateOrder,TotalMoney from tblOrders where CustomerID = ? order by DateOrder DESC";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String orderID = rs.getString("OrderID");
                String dateOrder = rs.getString("DateOrder");
                float total = rs.getFloat("TotalMoney");
                result.add(new OrderDTO(orderID, userID, dateOrder, total));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDetailDTO> getListOrderDetailByOrderID(String orderID) throws Exception {
        List<OrderDetailDTO> result = null;
        try {
            String sql = "select od.OrderDetailID,od.Quantity,p.Image,p.Name,od.Price\n"
                    + "from tblOrderDetails od join tblProducts p ON od.ProductID=p.ProductID\n"
                    + "where OrderID = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String orderDetailID = rs.getString("OrderDetailID");
                int quantity = rs.getInt("Quantity");
                float price = rs.getFloat("Price");
                String image = rs.getString("Image");
                String productName = rs.getString("Name");
                result.add(new OrderDetailDTO(orderDetailID, quantity, price, image, productName));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrderByProductName(String productName, String customerID) throws Exception {
        List<OrderDTO> result = null;
        try {
            String sql = "select DISTINCT o.OrderID,o.DateOrder,o.TotalMoney\n"
                    + "from tblProducts p join tblOrderDetails od ON p.ProductID=od.ProductID join tblOrders o ON od.OrderID=o.OrderID \n"
                    + "where p.Name like ? AND o.CustomerID = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + productName + "%");
            ps.setString(2, customerID);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String orderID = rs.getString("OrderID");
                String dateOrder = rs.getString("DateOrder");
                float total = rs.getFloat("TotalMoney");
                result.add(new OrderDTO(orderID, customerID, dateOrder, total));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrderByOrderDate(String from, String to, String customerID) throws Exception {
        List<OrderDTO> result = null;
        try {
            String sql = "select OrderID,DateOrder,TotalMoney\n"
                    + "from tblOrders \n"
                    + "where DateOrder >= ? AND DateOrder <= ? AND CustomerID = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, customerID);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String orderID = rs.getString("OrderID");
                String dateOrder = rs.getString("DateOrder");
                float total = rs.getFloat("TotalMoney");
                result.add(new OrderDTO(orderID, customerID, dateOrder, total));
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public int checkQuantityProduct(String productID) throws Exception {
        try {
            String sql = "select Quantity from tblProducts where ProductID=?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
}
