/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.controllers;

import hoangddh.dtos.Product;
import hoangddh.daos.ProductDAO;
import java.io.IOException;
import java.util.List;
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
public class SearchController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class.getName());
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "productManagement.jsp";

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
            String productName = (String) session.getAttribute("searchByName");
            String categoryID = (String) session.getAttribute("searchCategoryID");
            float minPrice = (Float) session.getAttribute("minPrice");
            float maxPrice = (Float) session.getAttribute("maxPrice");
            int indexPage;
            try {
                indexPage = Integer.parseInt(request.getParameter("indexSearchPage"));
            } catch (Exception e) {
                indexPage = 1;
            }
            ProductDAO dao = new ProductDAO();
            System.out.println(productName +"|"+ categoryID +"|"+ minPrice +"|"+ indexPage);
            List<Product> listProduct = dao.searchProduct(productName, categoryID, minPrice, maxPrice, indexPage);
            System.out.println("Size: "+listProduct.size());
            session.setAttribute("numberPage", dao.numberSearchedProduct(productName, categoryID, minPrice, maxPrice));
            request.setAttribute("listSearchedProduct", listProduct);
            request.setAttribute("indexSearchPage", indexPage);
        } catch (Exception e) {
            e.printStackTrace();
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
