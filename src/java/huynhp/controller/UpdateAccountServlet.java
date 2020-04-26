/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.controller;

import huynhp.account.AccountDAO;
import huynhp.account.AccountUpdatingErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "updateError.jsp";
    private final String SEARCH_PAGE = "search.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String url = SEARCH_PAGE;
        AccountUpdatingErrors errors = new AccountUpdatingErrors();
        boolean foundErr = false;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        String lastSearchValue = request.getParameter("lastSearchValue");
        
        boolean role = false;
        if (admin != null)
        {
            role = true;
        
        }
        try
        {
            if (password.trim().length() == 0)
            {
                foundErr = true;
                errors.setPasswordEmpty("Password can not be empty!!!");
            }
            if ((password.trim().length() >= 1 && password.trim().length() < 6) || password.trim().length() > 30)
            {
                foundErr = true;
                errors.setPasswordEmpty("Password's length must be greater than 6 and lower than 30!");
            }
            if (foundErr)
            {
                url = "SearchLastnameServlet?"
                        + "&txtSearch=" + lastSearchValue;
                
//                request.setAttribute("LASTSEARCHVALUE", lastSearchValue);
                request.setAttribute("ERRORUPDATE", errors);
                request.setAttribute("ERRORUSER", username);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else
            {
                AccountDAO dao = new AccountDAO();
                boolean result = dao.updateAccount(username, password, role);
                if (result)
                {
                    url = "searchLastname?"
                        + "&txtSearch=" + lastSearchValue;
                }
                response.sendRedirect(url);
            }
        }
        catch (NamingException ne)
        {
            log("UpdateServlet_NamingException " + ne.getMessage());
        }
        catch (SQLException se)
        {
            log("UpdateServlet_SQLException " + se.getMessage());
        }
        finally
        {
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
