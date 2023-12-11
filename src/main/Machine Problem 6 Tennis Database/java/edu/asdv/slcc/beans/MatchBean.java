/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import edu.asdv.slcc.bl.DatabaseManipulationMatch;
import edu.asdv.slcc.bl.Match;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ethan
 */
@Named(value = "matchBean")
@SessionScoped
public class MatchBean implements Serializable
{

    List<Match> matches;

    public void insertMatch()
    {
        System.out.println("InsertPlayer(Player p)");
        matches.add(new Match());
    }
    
    public void deleteMatch()
    {
        int count = 0;
        if (matches == null)
            return;
        for (Match m : this.matches)
        {
            if (m.isModify())
            {
                try
                {
                    count += this.dmm.delete(m);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this supplier.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.matches = dmm.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (count == 0)
        {
            String msg = "Number of rows deleted: " + count;
                addMessage(msg, "no exception", FacesMessage.SEVERITY_ERROR);
        }
        else
        {
            String msg = "Number of rows deleted: " + count;
                addMessage(msg, "no exception", FacesMessage.SEVERITY_INFO);
        }
    }

    public void saveToDatabase(Match m)
    {
        int num = 0;
        try
        {
            num = dmm.insert(m);
        }
        catch (SQLException e)
        {
            addMessage("Cannot insert this match.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        try
        {
            this.matches = dmm.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (num == 0)
        {
            addMessage("Error adding match.", "", FacesMessage.SEVERITY_ERROR);
        }
        else if (num > 0)
            addMessage("Match added.", "", FacesMessage.SEVERITY_INFO);

    }

    boolean testModify = false;

    public boolean isTestModify()
    {
        return testModify;
    }

    public void setTestModify(boolean testModify)
    {
        this.testModify = testModify;
    }

    //DatabaseManipulationSupplier dms = new DatabaseManipulationSupplier();
    @Inject
    DatabaseManipulationMatch dmm;

    public DatabaseManipulationMatch getDmm()
    {
        return dmm;
    }

    public List<Match> getMatches()
    {
        return matches;
    }

    public void saveFromUpdate()
    {
        if (matches == null)
        {
            return;
        }
        int totalRowsUpdated = 0;
        for (Match m : this.matches)
        {
            if (m.isModify())
            {
                try
                {
                    System.out.println("");
                    int rowsUpdated = this.dmm.update(m);
                    if (rowsUpdated > 0)
                    {
                        m.setModify(false);
                    }
                    totalRowsUpdated += rowsUpdated;
                }
                catch (Exception e)
                {
                    String msg = "Number of rows updated is 0.";
                    addMessage(msg, e.getMessage(), FacesMessage.SEVERITY_ERROR);
                    break;
                }

            }
        }
        String msg = "Number of rows updated: " + totalRowsUpdated;
        addMessage(msg, "no exception", FacesMessage.SEVERITY_INFO);

    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity)
    {
        FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Creates a new instance of SupplierBean
     */
//    public SupplierBean() 
//    {
//      
//    }
    @PostConstruct
    public void init()
    {
        try
        {
            matches = dmm.listAll();
        }
        catch (Exception e)
        {
            matches = null;
        }
    }

    public void displayExceptionMessagesAtPreRenderListener(ComponentSystemEvent event)
    {
        if (matches == null)
        {
            addMessage("Database Problem occurred.",
                    "", FacesMessage.SEVERITY_ERROR);
        }
    }

}
