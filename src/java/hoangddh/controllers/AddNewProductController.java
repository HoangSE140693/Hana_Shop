/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.controllers;

import hoangddh.daos.ProductDAO;
import hoangddh.dtos.Product;
import hoangddh.dtos.ProductErrorObj;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
public class AddNewProductController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddProductToCartController.class.getName());
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewProductController";
    private static final String INVALID = "addNewProduct.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO dao = new ProductDAO();
            String productName = request.getParameter("txtProductName");
            String image = request.getParameter("txtImage");
            float price = 0;
            String category = request.getParameter("cbCategory");
            String description = request.getParameter("txtDescription");
            ProductErrorObj errorObj = new ProductErrorObj();

            boolean isInvalid = false;
            try {
                price = Float.parseFloat(request.getParameter("txtPrice"));
                if (price <= 0) {
                    errorObj.setPriceError("Price >  0");
                    isInvalid = true;
                }
            } catch (Exception e) {
                errorObj.setPriceError("Price is a number");
                isInvalid = true;
            }
            int quantity = 0;
            try {
                quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                if (quantity < 1) {
                    errorObj.setQuantityError("Quantity > 0");
                    isInvalid = true;
                }
            } catch (Exception e) {
                errorObj.setPriceError("Quantity must be integer number");
                isInvalid = true;
            }
            LocalDateTime dateTime = LocalDateTime.now();
            String createDate = dateTime.toString().split("T")[0];
            String productID = "P" + (dao.getLastIDProduct() + 1);
            Product product = new Product(productID, productName, image, category, createDate, description, quantity, price);
            if (dao.addNewProduct(product) && !isInvalid) {
                url = SUCCESS;
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("infoProduct", product);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
