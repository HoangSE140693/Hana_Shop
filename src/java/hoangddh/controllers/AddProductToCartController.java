
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.controllers;

import hoangddh.dtos.Product;
import hoangddh.daos.ProductDAO;
import hoangddh.dtos.Cart;
import hoangddh.dtos.User;
import java.io.IOException;
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
public class AddProductToCartController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddProductToCartController.class.getName());
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
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("shoppingCart");
            if (cart == null) {
                User user = (User) session.getAttribute("infoUser");
                String customerID = user.getUserID();
                cart = new Cart(customerID);
            }
            ProductDAO productDAO = new ProductDAO();
            String productID = request.getParameter("productID");

            Product product = productDAO.getProductByID(productID);
            try {
                int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                cart.addToCart(product, quantity);
            } catch (Exception e) {
                cart.addToCart(product, 1);
            }
            session.setAttribute("shoppingCart", cart);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("ViewProductController").forward(request, response);
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
