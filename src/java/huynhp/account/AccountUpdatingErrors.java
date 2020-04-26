/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.account;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AccountUpdatingErrors implements Serializable
{
    private String passwordEmpty;
    private String passwordLength;

    public AccountUpdatingErrors() 
    {
        
    }

    /**
     * @return the passwordEmpty
     */
    public String getPasswordEmpty() {
        return passwordEmpty;
    }

    /**
     * @param passwordEmpty the passwordEmpty to set
     */
    public void setPasswordEmpty(String passwordEmpty) {
        this.passwordEmpty = passwordEmpty;
    }

    /**
     * @return the passwordLength
     */
    public String getPasswordLength() {
        return passwordLength;
    }

    /**
     * @param passwordLength the passwordLength to set
     */
    public void setPasswordLength(String passwordLength) {
        this.passwordLength = passwordLength;
    }
    
    
}
