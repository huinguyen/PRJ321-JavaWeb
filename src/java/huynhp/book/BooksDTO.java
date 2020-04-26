/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.book;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class BooksDTO implements Serializable
{
    private String title;
    private int quantity;
    private double price;

    public BooksDTO() 
    {
        
    }

    public BooksDTO(String title, int quantity, double price) 
    {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

  
}
