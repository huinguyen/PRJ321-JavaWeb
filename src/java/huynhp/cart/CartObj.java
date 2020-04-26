/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObj implements Serializable
{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() 
    {
        return items;
    }
    public void addItemToCart(String itemName)
    {
        // 1.Check items not existed
        if (this.items == null) 
        {
            this.items = new HashMap<>();
        }// end if items is null
        
        // 2.Check item existed
        int quantity = 1;
        if (this.items.containsKey(itemName)) 
        {
            quantity = this.items.get(itemName)+1;
        }// end if check item
        
        // 3.Dua do vao gio
        this.items.put(itemName, quantity);
    }
    public void removeItemFromCart(String itemName)
    {
        // 1.Check cart is exitsted
        if (this.items == null) 
        {
            return;
        }
        // 2.Check item is existed
        if (this.items.containsKey(itemName)) 
        {
            // 3.Delete item
            this.items.remove(itemName);
            if (this.items.isEmpty()) 
            {
                this.items = null;
            }
        }// end if item is existed
    }
    
}
