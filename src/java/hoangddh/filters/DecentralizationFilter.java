/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.filters;

import hoangddh.dtos.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Asus
 */
public class DecentralizationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(DecentralizationFilter.class.getName());
    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
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
    private static final String LOGIN_WITH_GOOGLE_MAIL = "LoginGoogleServlet";

    private ArrayList<String> admin;
    private ArrayList<String> guest;
    private ArrayList<String> user;

    public DecentralizationFilter() {
        admin = new ArrayList<>();
        admin.add(LOGOUT);
        admin.add(DELETE_PRODUCT);
        admin.add(EDIT_PRODUCT);
        admin.add(UPDATE_PRODUCT);
        admin.add(ADD_NEW_PRODUCT);
        admin.add("addNewProduct.jsp");
        admin.add("editProduct.jsp");

        guest = new ArrayList<>();
        guest.add("login.jsp");
        guest.add(LOGIN);
        guest.add(LOGIN_WITH_GOOGLE_MAIL);

        user = new ArrayList<>();
        user.add(LOGOUT);
        user.add(ADD_TO_CART);
        user.add(DELETE_PRODUCT_FROM_CART);
        user.add(UPDATE_PRODUCT_IN_CART);
        user.add(CHECK_OUT);
        user.add(VIEW_ORDER_HISTORY);
        user.add(VIEW_ORDER_DETAILS_HISTORY);
        user.add(SEARCH_ORDER_BY_PRODUCT_NAME);
        user.add(SEARCH_ORDER_BY_ORDER_DATE);
        user.add("shoppingHistory.jsp");
        user.add("viewCart.jsp");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DecentralizationFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DecentralizationFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        try {
            String uri = req.getRequestURI();
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            if (session != null && session.getAttribute("infoUser") != null) {
                User accountDTO = (User) session.getAttribute("infoUser");
                if (accountDTO.getRole().equals("admin") && admin.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    if (accountDTO.getRole().equals("user") && user.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(ERROR);
                    }
                }
            } else {
                if (guest.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(ERROR);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("DecentralizationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DecentralizationFilter()");
        }
        StringBuffer sb = new StringBuffer("DecentralizationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
