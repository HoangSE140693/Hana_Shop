/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.controllers;

import hoangddh.daos.OrderDAO;
import hoangddh.daos.ProductDAO;
import hoangddh.dtos.Cart;
import hoangddh.dtos.OrderDTO;
import hoangddh.dtos.OrderDetailDTO;
import hoangddh.dtos.ProductInCartDTO;
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
public class SubmitOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SubmitOrderController.class.getName());
    private static final String SUCCESS = "ViewOrderHistoryController";
    private static final String ERROR = "error.jsp";
    private static final String OUT_STOCK = "viewCart.jsp";

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
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("shoppingCart");
            OrderDAO orderDAO = new OrderDAO();
            LocalDateTime dateTime = LocalDateTime.now();
            String dateCreate = dateTime.toString().split("T")[0];
            OrderDTO orderDTO = new OrderDTO(orderDAO.getLastOrder() + 1 + "", cart.getCustomerID(), dateCreate, cart.getTotal());

            boolean isAvailable = true;
            for (ProductInCartDTO dto : cart.getShoppingCart().values()) {
                if (orderDAO.checkQuantityProduct(dto.getProduct().getProductID()) == 0
                        || orderDAO.checkQuantityProduct(dto.getProduct().getProductID()) < dto.getQuantityInCart()) {
                    isAvailable = false;
                    request.setAttribute("AVAILABLE", "Quantity of " + dto.getProduct().getName() + " is not enough.");
                    url = OUT_STOCK;
                    break;
                }
            }
            ProductDAO productDAO = new ProductDAO();
            if (isAvailable) {
                orderDAO.insertNewOrder(orderDTO);//Insert order down database
                for (ProductInCartDTO dto : cart.getShoppingCart().values()) {
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDAO.getLastOrderDetail() + 1 + "",
                            dto.getProduct().getProductID(), orderDTO.getOrderID(), dto.getQuantityInCart(), dto.getProduct().getPrice());
                    orderDAO.insertNewOrderDetail(orderDetailDTO);
                    //Update quantity of product
                    dto.getProduct().setQuantity(dto.getProduct().getQuantity() - dto.getQuantityInCart());
                    productDAO.updateProduct(dto.getProduct());
                }
                session.removeAttribute("shoppingCart");
            }

        } catch (Exception e) {
            url = ERROR;
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
