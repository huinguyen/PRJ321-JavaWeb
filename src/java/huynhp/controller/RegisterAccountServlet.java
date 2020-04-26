/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.controller;

import huynhp.account.AccountDAO;
import huynhp.account.AccountDTO;
import huynhp.account.AccountRegistratingErrors;
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
@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/RegisterAccountServlet"})
public class RegisterAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String REGISTER_CHECKING_PAGE = "registerAccount.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        String url = REGISTER_CHECKING_PAGE;
        
        AccountRegistratingErrors errorsAccount = new AccountRegistratingErrors();
        boolean foundErr = false;
        try
        {
            if (username.trim().length() < 6 || username.trim().length() > 20)
            {
                foundErr = true;
                errorsAccount.setUsernameLongErr("Username length is from 6 to 20 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30)
            {
                foundErr = true;
                errorsAccount.setPasswordLongErr("Password length is from 6 to 30 chars");
            }
            else if (!confirm.trim().equals(password.trim()))
            {
                foundErr = true;
                errorsAccount.setConfirmNoMatchPassword("Confirm must match the password");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50)
            {
                foundErr = true;
                errorsAccount.setFullNameLongErr("Fullname length is from 2 to 50 chars");
            }
            
            if (foundErr)
            {
                request.setAttribute("CREATEERRORS", errorsAccount);
            }
            else
            {
                AccountDTO dto = new AccountDTO(username, password, fullName, false);
                AccountDAO dao = new AccountDAO();
                
                boolean result = dao.registerNewAccount(dto);
                if (result)
                {
                    url = LOGIN_PAGE;
                }
            }
                    
        }
        catch (NamingException ne)
        {
            log("RegisterAccountServlet_NamingException " + ne.getMessage());
        }
        catch (SQLException se)
        {
            log("RegisterAccountServlet_SQLException " + se.getMessage());
            if (se.getMessage().contains("duplicate"))
            {
                errorsAccount.setUsernameIsExisted(username + " is existed!!!");
                request.setAttribute("CREATEERRORS", errorsAccount);
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
