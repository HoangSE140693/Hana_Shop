/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.controllers;

import hoangddh.dtos.Product;
import hoangddh.daos.ProductDAO;
import hoangddh.dtos.ProductErrorObj;
import hoangddh.dtos.UpdateHistoryDTO;
import hoangddh.dtos.User;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
public class UpdateProductController extends HttpServlet {

        private static final Logger LOGGER = Logger.getLogger(UpdateProductController.class.getName());

    private static final String SUCCESS = "ViewProductController";
    private static final String FAILED = "error.jsp";
    private static final String INVALiD = "editProduct.jsp";

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
        String url = FAILED;
        try {
            ProductDAO dao = new ProductDAO();
            String productID = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProductName");
            String image = request.getParameter("txtImage");
            float price = 0;

            String category = request.getParameter("cbCategory");
            String status = request.getParameter("cbStatus");
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
            String updateDate = dateTime.toString().split("T")[0];
            Product product = new Product(productID, productName, image, category, description, quantity, price);
            if (status.equals("inactive")) {
                dao.deleteProduct(productID);
                url = SUCCESS;
            } else {
                if (dao.updateProduct(product) && !isInvalid) {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("infoUser");
                    UpdateHistoryDTO historyDTO = new UpdateHistoryDTO(dao.getLastIDOfUpdateHistory() + 1, updateDate, user.getUserID(), productID);
                    dao.recordUpdateHistory(historyDTO);
                } else {
                    url = INVALiD;
                    request.setAttribute("INVALID", errorObj);
                    request.setAttribute("infoProduct", product);
                }
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
