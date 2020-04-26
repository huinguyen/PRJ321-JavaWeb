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
public class AccountRegistratingErrors implements Serializable
{
    private String usernameLongErr;
    private String passwordLongErr;
    private String confirmNoMatchPassword;
    private String fullNameLongErr;
    private String usernameIsExisted;

    public AccountRegistratingErrors() 
    {
        
    }

    public AccountRegistratingErrors(String usernameLongErr, String passwordLongErr, String confirmNoMatchPassword, String fullNameLongErr, String usernameIsExisted) 
    {
        this.usernameLongErr = usernameLongErr;
        this.passwordLongErr = passwordLongErr;
        this.confirmNoMatchPassword = confirmNoMatchPassword;
        this.fullNameLongErr = fullNameLongErr;
        this.usernameIsExisted = usernameIsExisted;
    }

    /**
     * @return the usernameLongErr
     */
    public String getUsernameLongErr() {
        return usernameLongErr;
    }

    /**
     * @param usernameLongErr the usernameLongErr to set
     */
    public void setUsernameLongErr(String usernameLongErr) {
        this.usernameLongErr = usernameLongErr;
    }

    /**
     * @return the passwordLongErr
     */
    public String getPasswordLongErr() {
        return passwordLongErr;
    }

    /**
     * @param passwordLongErr the passwordLongErr to set
     */
    public void setPasswordLongErr(String passwordLongErr) {
        this.passwordLongErr = passwordLongErr;
    }

    /**
     * @return the confirmNoMatchPassword
     */
    public String getConfirmNoMatchPassword() {
        return confirmNoMatchPassword;
    }

    /**
     * @param confirmNoMatchPassword the confirmNoMatchPassword to set
     */
    public void setConfirmNoMatchPassword(String confirmNoMatchPassword) {
        this.confirmNoMatchPassword = confirmNoMatchPassword;
    }

    /**
     * @return the fullNameLongErr
     */
    public String getFullNameLongErr() {
        return fullNameLongErr;
    }

    /**
     * @param fullNameLongErr the fullNameLongErr to set
     */
    public void setFullNameLongErr(String fullNameLongErr) {
        this.fullNameLongErr = fullNameLongErr;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
