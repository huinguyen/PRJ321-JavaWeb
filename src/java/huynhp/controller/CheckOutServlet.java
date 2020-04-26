/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.controller;

import huynhp.book.BooksDAO;
import huynhp.cart.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String CHECK_OUT = "viewcart.jsp";
    private final String CHECK_OUT_ERROR_PAGE = "checkOutError.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = CHECK_OUT_ERROR_PAGE;
        try
        {
            HttpSession session = request.getSession(false);
            if (session != null) 
            {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) 
                {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) 
                    {

                        try 
                        {           
                            boolean result = false;
                            int count = 0;
                            BooksDAO dao = new BooksDAO();
                            for (String itemName : items.keySet()) 
                            {
                                if (itemName.contains("Doraemon"))
                                {
                                    int no = ++count;
                                    String title = itemName;
                                    int quantity = items.get(itemName);
                                    double price = 35000;
                                    double total = quantity * price;
                                    result = dao.insertItems(no, title, quantity, price, total);
                                }
                                if (itemName.contains("Conan"))
                                {
                                    int no = ++count;
                                    String title = itemName;
                                    int quantity = items.get(itemName);
                                    double price = 78000;
                                    double total = quantity * price;
                                    result = dao.insertItems(no, title, quantity, price, total); 
                                }
                                if (itemName.contains("Cushin"))
                                {
                                   int no = ++count;
                                   String title = itemName;
                                   int quantity = items.get(itemName);
                                   double price = 56000;
                                   double total = quantity * price;
                                   result = dao.insertItems(no, title, quantity, price, total); 
                                }
                                if (itemName.contains("Batman"))
                                {
                                   int no = ++count;
                                   String title = itemName;
                                   int quantity = items.get(itemName);
                                   double price = 188000;
                                   double total = quantity * price;
                                   result = dao.insertItems(no, title, quantity, price, total); 
                                }
//                                int no = ++count;
//                                String title = itemName;
//                                int quantity = items.get(itemName);
//                                final double price = 20000;
//                                double total = quantity * price;
//                                    result = dao.insertItems(no, title, quantity, price, total);
                                if (result) 
                                {
                                    request.setAttribute("SUCCESSFULL", "Checkout successfull.");
                                    url = CHECK_OUT;
                                    session.removeAttribute("CART");
                                }
                            }
                        } 
                        catch (NamingException ne) 
                        {
                            log("CheckOutServlet_NamingException " + ne.getMessage());
                        } 
                        catch (SQLException se)
                        {
                            log("CheckOutServlet_SQLException " + se.getMessage());
                        }
                    }
                }
            }
        } 
        
        finally
        {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
