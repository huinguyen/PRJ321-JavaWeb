/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.book;

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
public class BooksDAO implements Serializable
{
    private List<BooksDTO> booksList;

    public List<BooksDTO> getBooksList() 
    {
        return booksList;
    }
    public void loadBooksData() throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            con = Database.makeConnection();
            if (con != null)
            {
                String SQL = "Select Title, Quantity, Price "
                            + "From StoredBook";
                stm = con.prepareStatement(SQL);
                rs = stm.executeQuery();
                while (rs.next())
                {
                    String title = rs.getString("Title");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");
                    
                    BooksDTO dto = new BooksDTO(title, quantity, price);
                    if (this.booksList == null)
                    {
                        this.booksList = new ArrayList<>();
                    }
                    this.booksList.add(dto);
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
    public boolean insertItems(int no, String Title, int Quantity, double Price, double TotalPrice) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            con = Database.makeConnection();
            if (con != null)
            {
                String SQL = "Insert into OrderDetail(No, Title, Quantity, Price, TotalPrice) "
                            + "values (?,?,?,?,?)";
                stm = con.prepareStatement(SQL);
                stm.setInt(1, no);
                stm.setString(2, Title);
                stm.setInt(3, Quantity);
                stm.setDouble(4, Price);
                stm.setDouble(5, TotalPrice);
                
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
