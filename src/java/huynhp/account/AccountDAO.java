/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.account;

import huynhp.utils.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class AccountDAO implements Serializable
{
    public boolean checkLogin(String username, String password) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            con = Database.makeConnection();
            if(con != null)
            {
                String SQL = "Select username "
                            + "From Account "
                            + "Where username = ? And password = ?";
                stm = con.prepareStatement(SQL);
                stm.setString(1, username);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                if (rs.next())
                {
                    return true;
                }
            }
        }
        finally
        {
            if (rs != null)
            {
                rs.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return false;
    }
    public String getFullname(String username) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            con = Database.makeConnection();
            if (con != null)
            {
                String SQL = "Select lastname "
                            + "From Account "
                            + "Where username = ?";
                stm = con.prepareStatement(SQL);
                stm.setString(1, username);
                
                rs = stm.executeQuery();
                if (rs.next())
                {
                    String lastname = rs.getString("lastname");
                    return lastname;
                }
            }
        }
        finally
        {
            if (rs != null)
            {
                rs.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return null;
    }
    
    private List<AccountDTO> accountList;

    public List<AccountDTO> getAccountList() 
    {
        return accountList;
    }
    
    public void searchLastName(String searchValue) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
           con = Database.makeConnection();
           if (con != null)
           {
               String SQL = "Select username, password, lastname, isAdmin "   
                           + "From Account "
                           + "Where lastname LIKE ?";
               stm = con.prepareStatement(SQL);
               stm.setString(1, "%" + searchValue + "%");
               
               rs = stm.executeQuery();
               while (rs.next())
               {
                   String username = rs.getString("username");
                   String password = rs.getString("password");
                   String lastname = rs.getString("lastname");
                   Boolean role = rs.getBoolean("isAdmin");
                   
                   AccountDTO dto = new AccountDTO(username, password, lastname, role);
                   if (this.accountList == null)
                   {
                       this.accountList = new ArrayList<>();
                   }
                   this.accountList.add(dto);
               }
           }
        }
        finally
        {
            if (rs != null)
            {
                rs.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
    }
    
    public boolean updateAccount(String username, String password, boolean role) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            con = Database.makeConnection();
            String SQL = "Update Account "
                        + "Set password = ? ,isAdmin = ? "
                        + "Where username = ?";
            stm = con.prepareStatement(SQL);

            stm.setString(1, password);
            stm.setBoolean(2, role);
            stm.setString(3, username);
            int row = stm.executeUpdate();
            if (row > 0)
            {
                return true;
            }
         }
        finally
        {
            if (stm != null)
            {
                stm.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return false;
    }
    public boolean deleteAccount(String username) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            con = Database.makeConnection();
            if (con != null)
            {
                String SQL = "Delete From Account "
                            + "Where username = ?";
                stm = con.prepareStatement(SQL);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0)
                {
                    return true;
                }
            }
        }
        finally
        {
            if (stm != null)
            {
                stm.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return false;
    }
    public boolean registerNewAccount(AccountDTO dto) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            con = Database.makeConnection();
            if (con != null)
            {
                String SQL = "Insert into Account(username, password, lastname, isAdmin) "
                            + "values(?,?,?,?)";
                stm = con.prepareStatement(SQL);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastname());
                stm.setBoolean(4, dto.getRole());
                
                int row = stm.executeUpdate();
                if (row > 0)
                {
                    return true;
                }
            }       
        }
        finally
        {
           if (stm != null)
           {
               stm.close();
           }
           if (con != null)
           {
               con.close();
           } 
        }
        return false;
    }
    
}
