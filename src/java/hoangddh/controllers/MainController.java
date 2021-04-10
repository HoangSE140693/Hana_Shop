/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String VIEW_PRODUCT = "ViewProductController";
    private static final String SEARCH_BY_NAME = "SearchByLikeNameController";
    private static final String SEARCH_BY_CATEGORY = "SearchByCategoryController";
    private static final String SEARCH_BY_PRICE = "SearchByPriceController";
    private static final String COMMON_SEARCH = "SearchController";
    private static final String EDIT_PRODUCT = "EditProductController";
    private static final String UPDATE_PRODUCT = "UpdateProductController";
    private static final String DELETE_PRODUCT = "DeleteProductController";
    private static final String ADD_NEW_PRODUCT = "AddNewProductController";
    private static final String ADD_TO_CART = "AddProductToCartController";
    private static final String DELETE_PRODUCT_FROM_CART = "DeleteProductFromCartController";
    private static final String UPDATE_PRODUCT_IN_CART = "UpdateProductInCartController";
    private static final String CHECK_OUT = "SubmitOrderController";
    private static final String VIEW_ORDER_HISTORY = "ViewOrderHistoryController";
    private static final String VIEW_ORDER_DETAILS_HISTORY = "ViewOrderDetailHistoryController";
    private static final String SEARCH_ORDER_BY_PRODUCT_NAME = "SearchOrderByProductNameController";
    private static final String SEARCH_ORDER_BY_ORDER_DATE = "SearchOrderHistoryByDateController";

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
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("viewProduct")) {
                url = VIEW_PRODUCT;
            } else if (action.equals("searchByName")) {
                url = SEARCH_BY_NAME;
            } else if (action.equals("searchCate")) {
                url = SEARCH_BY_CATEGORY;
            } else if (action.equals("searchByPrice")) {
                url = SEARCH_BY_PRICE;
            } else if (action.equals("search")) {
                url = COMMON_SEARCH;
            } else if (action.equals("editProduct")) {
                url = EDIT_PRODUCT;
            } else if (action.equals("deleteProduct")) {
                url = DELETE_PRODUCT;
            } else if (action.equals("Update")) {
                url = UPDATE_PRODUCT;
            } else if (action.equals("Create")) {
                url = ADD_NEW_PRODUCT;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("addToCart")) {
                url = ADD_TO_CART;
            } else if (action.equals("deleteProductFromCart")) {
                url = DELETE_PRODUCT_FROM_CART;
            } else if (action.equals("updateCart")) {
                url = UPDATE_PRODUCT_IN_CART;
            } else if (action.equals("checkOut")) {
                url = CHECK_OUT;
            } else if (action.equals("showHistory")) {
                url = VIEW_ORDER_HISTORY;
            } else if (action.equals("viewDetail")) {
                url = VIEW_ORDER_DETAILS_HISTORY;
            } else if (action.equals("searchOrderedProductByName")) {
                url = SEARCH_ORDER_BY_PRODUCT_NAME;
            } else if (action.equals("searchByDate")) {
                url = SEARCH_ORDER_BY_ORDER_DATE;
            } else {
                request.setAttribute("ERROR", "Action isn't existed!");
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
