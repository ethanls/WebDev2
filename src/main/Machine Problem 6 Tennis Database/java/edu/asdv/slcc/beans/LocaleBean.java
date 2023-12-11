/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author ethan
 */
@Named(value = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable
{


    public void setSpanish()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

        viewRoot.setLocale(new Locale("es"));
    }
    
    public void setEnglish()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

        viewRoot.setLocale(new Locale("en"));
    }
    
    public void setGreek()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

        viewRoot.setLocale(new Locale("el"));
    }
    
    public void setRussian()
    {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

        viewRoot.setLocale(new Locale("ru"));
    }
    
    
    /**
     * Creates a new instance of LocaleBean
     */
    public LocaleBean()
    {
    }
    
}
