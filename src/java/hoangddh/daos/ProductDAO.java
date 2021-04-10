/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.daos;

import hoangddh.dtos.Category;
import hoangddh.dtos.Product;
import hoangddh.dtos.UpdateHistoryDTO;
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
public class ProductDAO implements Serializable {

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
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int getNumberPage() throws Exception {

        try {
            String sql = "select count(ProductID) from tblProducts where Status='true' AND Quantity > 0";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 9;
                if (total % 9 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public Product getProductByID(String productID) throws Exception {
        Product product = null;
        try {
            String sql = "select p.ProductID,p.Name,p.Image,p.Price,c.CategoryName,p.Quantity,p.CreateDate,p.Description\n"
                    + "from tblProducts p join tblCategories c ON p.CategoryID=c.CategoryID\n"
                    + "where p.ProductID = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name, image, categoryName, creatDate, description;
                float price;
                int quantity;
                productID = rs.getString("ProductID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                price = rs.getFloat("Price");
                categoryName = rs.getString("CategoryName");
                quantity = rs.getInt("Quantity");
                creatDate = rs.getString("CreateDate");
                description = rs.getString("Description");
                product = new Product(productID, name, image, categoryName, creatDate, description, quantity, price);
            }
        } finally {
            closeConnection();
        }
        return product;
    }

    public List<Product> getListProduct(int index) throws Exception {
        List<Product> list = null;
        try {
            String sql = "select p.ProductID,p.Name,p.Image,p.Price,c.CategoryName,p.Quantity,p.CreateDate,p.Description\n"
                    + "from tblProducts p join tblCategories c ON p.CategoryID=c.CategoryID\n"
                    + "where status='true' AND Quantity >0\n "
                    + "order by CreateDate\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH FIRST 9 ROWS ONLY";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 9);
            rs = ps.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                String productID, name, image, categoryName, creatDate, description;
                float price;
                int quantity;
                productID = rs.getString("ProductID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                price = rs.getFloat("Price");
                categoryName = rs.getString("CategoryName");
                quantity = rs.getInt("Quantity");
                creatDate = rs.getString("CreateDate");
                description = rs.getString("Description");
                list.add(new Product(productID, name, image, categoryName, creatDate, description, quantity, price));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Category> getCategory() throws Exception {
        List<Category> result = null;
        try {
            String sql = "select CategoryID,CategoryName from tblCategories";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            result = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                String cateID, cateName;
                cateID = rs.getString("CategoryID");
                cateName = rs.getString("CategoryName");
                result.add(new Category(cateID, cateName));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int numberSearchedProduct(String name, String categoryID, float minPrice, float maxPrice) throws Exception {
        try {
            String sql = "select count(p.ProductID)\n"
                    + "from tblProducts p join tblCategories c ON p.CategoryID=c.CategoryID\n"
                    + "where p.Name like ? AND c.CategoryID = ? AND (p.Price >= ? and p.Price <= ?)"
                    + " AND status='true' AND Quantity >0\n";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + categoryID + "%");
            ps.setFloat(3, minPrice);
            ps.setFloat(4, maxPrice);
            rs = ps.executeQuery();
            if (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 9;
                if (total % 9 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<Product> searchProduct(String name, String categoryID, float minPrice, float maxPrice, int index) throws Exception {
        List<Product> result = null;
        try {
            if (categoryID.equals("")) {
                String sql = "select p.ProductID,p.Name,p.Image,p.Price,c.CategoryName,p.Quantity,p.CreateDate,p.Description\n"
                        + "from tblProducts p join tblCategories c ON p.CategoryID=c.CategoryID\n"
                        + "where p.Name like ? AND (p.Price >= ? and p.Price <=?)"
                        + " AND status='true' AND Quantity >0\n"
                        + "order by CreateDate\n"
                        + "OFFSET ? ROWS\n"
                        + "FETCH FIRST 9 ROWS ONLY";
                connection = MyConnection.getMyConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setFloat(2, minPrice);
                ps.setFloat(3, maxPrice);
                ps.setInt(4, (index - 1) * 9);
            } else {
                String sql = "select p.ProductID,p.Name,p.Image,p.Price,c.CategoryName,p.Quantity,p.CreateDate,p.Description\n"
                        + "from tblProducts p join tblCategories c ON p.CategoryID=c.CategoryID\n"
                        + "where p.Name like ? AND c.CategoryID = ? AND (p.Price >= ? and p.Price <=?)"
                        + " AND status='true' AND Quantity >0\n"
                        + "order by CreateDate\n"
                        + "OFFSET ? ROWS\n"
                        + "FETCH FIRST 9 ROWS ONLY";
                connection = MyConnection.getMyConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setInt(2, Integer.parseInt(categoryID));
                ps.setFloat(3, minPrice);
                ps.setFloat(4, maxPrice);
                ps.setInt(5, (index - 1) * 9);
            }

            result = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID, image, categoryName, creatDate, description;
                float price;
                int quantity;
                productID = rs.getString("ProductID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                price = rs.getFloat("Price");
                categoryName = rs.getString("CategoryName");
                quantity = rs.getInt("Quantity");
                creatDate = rs.getString("CreateDate");
                description = rs.getString("Description");
                result.add(new Product(productID, name, image, categoryName, creatDate, description, quantity, price));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean addNewProduct(Product product) throws Exception {
        boolean check = false;
        try {
            ArrayList<Category> listCate = (ArrayList) getCategory();
            String sql = "Insert into tblProducts(ProductID,Name,Image,Price,CategoryID,Quantity,Status,CreateDate,Description) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, product.getProductID());
            ps.setString(2, product.getName());
            ps.setString(3, product.getImage());
            ps.setFloat(4, product.getPrice());
            for (Category category : listCate) {
                if (category.getCategoryName().equals(product.getCategory())) {
                    ps.setString(5, category.getCategoryID());
                }
            }
            ps.setInt(6, product.getQuantity());
            ps.setBoolean(7, true);
            ps.setString(8, product.getCreateDate());
            ps.setString(9, product.getDescription());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateProduct(Product product) throws Exception {
        boolean check = false;
        ArrayList<Category> listCate = (ArrayList) getCategory();
        try {
            String sql = "Update tblProducts set Name=?,Image=?,Price=?,CategoryID=?,Quantity=?,Description=? \n"
                    + "where ProductID = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setFloat(3, product.getPrice());
            for (Category category : listCate) {
                if (category.getCategoryName().equals(product.getCategory())) {
                    ps.setString(4, category.getCategoryID());
                }
            }
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getProductID());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteProduct(String productID) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblProducts set Status = 'false' \n"
                    + "where ProductID = ?";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, productID);
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getLastIDProduct() throws Exception {
        int lastID = 0;
        try {
            String sql = "SELECT TOP 1 ProductID FROM tblProducts\n"
                    + "ORDER BY ProductID DESC;";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastID = Integer.parseInt(rs.getString("ProductID").substring(1));
            }
        } finally {
            closeConnection();
        }
        return lastID;
    }

    public float getMaximumPrice() throws Exception {
        float result = 0;
        try {
            String sql = "select MAX(Price) as 'Max Price'\n"
                    + "from tblProducts";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getFloat("Max Price");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean recordUpdateHistory(UpdateHistoryDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblUpdateHistory(ID,DateOfUpdate,UserID,ProductID)\n"
                    + "values(?,?,?,?)";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, dto.getID());
            ps.setString(2, dto.getDateUpdate());
            ps.setString(3, dto.getUserID());
            ps.setString(4, dto.getProductID());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getLastIDOfUpdateHistory() throws Exception {
        try {
            String sql = "select Max(ID) from tblUpdateHistory";
            connection = MyConnection.getMyConnection();
            ps = connection.prepareStatement(sql);
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
