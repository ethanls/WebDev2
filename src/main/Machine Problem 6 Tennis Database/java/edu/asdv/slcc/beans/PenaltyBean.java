/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import edu.asdv.slcc.bl.DatabaseManipulationPenalty;
import edu.asdv.slcc.bl.Penalty;
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
@Named(value = "penaltyBean")
@SessionScoped
public class PenaltyBean implements Serializable
{
    List<Penalty> penalties;

    public void insertPenalty()
    {
        System.out.println("InsertPenalty(Penalty p)");
        penalties.add(new Penalty());
    }
    
    public void deletePenalty()
    {
        int count = 0;
        if (penalties == null)
            return;
        for (Penalty p : this.penalties)
        {
            if (p.isModify())
            {
                try
                {
                    count += this.dmp.delete(p);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this supplier.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.penalties = dmp.listAll();
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

    public void saveToDatabase(Penalty p)
    {
        System.out.println(p);
        
        int num = 0;

        try
        {
            num = dmp.insert(p);
        }
        catch (SQLException e)
        {
            addMessage("Cannot insert this penalty.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        try
        {
            this.penalties = dmp.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (num == 0)
        {
            addMessage("Error adding penalty.", "", FacesMessage.SEVERITY_ERROR);
        }
        else if (num > 0)
            addMessage("Penalty added.", "", FacesMessage.SEVERITY_INFO);

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
    DatabaseManipulationPenalty dmp;

    public DatabaseManipulationPenalty getDmp()
    {
        return dmp;
    }

    public List<Penalty> getPenalties()
    {
        return penalties;
    }

    public void saveFromUpdate()
    {
        if (penalties == null)
        {
            return;
        }
        int totalRowsUpdated = 0;
        for (Penalty p : this.penalties)
        {
            if (p.isModify())
            {
                try
                {
                    System.out.println("");
                    int rowsUpdated = this.dmp.update(p);
                    if (rowsUpdated > 0)
                    {
                        p.setModify(false);
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
            penalties = dmp.listAll();
        }
        catch (Exception e)
        {
            penalties = null;
        }
    }

    public void displayExceptionMessagesAtPreRenderListener(ComponentSystemEvent event)
    {
        if (penalties == null)
        {
            addMessage("Database Problem occurred.",
                    "", FacesMessage.SEVERITY_ERROR);
        }
    }
}
