/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import jakarta.security.enterprise.credential.Password;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author ethan
 */
@Named(value = "passwordBean")
@RequestScoped
public class PasswordBean
{
    private Password password;
    private String username;
    private String pass;

    public Password getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
        this.password = new Password(pass);
    }
    
    
    
    public String logIn()
    {
        String s = this.pass;
        
        if (s.length() < 8)
        {
            FacesContext.getCurrentInstance().addMessage(
                    "Password needs to be 8 characters long", 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Password needs to be 8 characters long.", 
                            ""));
            return "bad";
        }
        
        char[] p = s.toCharArray();
        boolean valid = false;
        for (char c : p)
        {
            if (c == '!' || c == '&' || c == '+')
                valid = true;
        }
        
        if (!valid)
        {
            
            FacesContext.getCurrentInstance().addMessage(
                    "", 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Password requires one of the following: "
                                    + "(! + &)", 
                            ""));
            return "bad";
        }
        
        return "good";
    }
    
    
    
    /**
     * Creates a new instance of PasswordBean
     */
    public PasswordBean()
    {
    }
    
    
}
