/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import edu.asdv.slcc.bl.CommitteeMember;
import edu.asdv.slcc.bl.DatabaseManipulationCommitteeMember;
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
@Named(value = "committeeBean")
@SessionScoped
public class CommitteeBean implements Serializable
{
    List<CommitteeMember> members;

    public void insertMember()
    {
        System.out.println("insertMember(Member c)");
        members.add(new CommitteeMember());
    }
    
    public void deleteMember()
    {
        int count = 0;
        if (members == null)
            return;
        for (CommitteeMember m : this.members)
        {
            if (m.isModify())
            {
                try
                {
                    count += this.dmc.delete(m);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this supplier.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.members = dmc.listAll();
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

    public void saveToDatabase(CommitteeMember c)
    {
        System.out.println(c);
        int num = 0;
        try
        {
            num = dmc.insert(c);
        }
        catch (SQLException e)
        {
            addMessage("Cannot insert this member.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        try
        {
            this.members = dmc.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (num == 0)
        {
            addMessage("Error adding member.", "", FacesMessage.SEVERITY_ERROR);
        }
        else if (num > 0)
            addMessage("Member added.", "", FacesMessage.SEVERITY_INFO);

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
    DatabaseManipulationCommitteeMember dmc;

    public DatabaseManipulationCommitteeMember getDmc()
    {
        return dmc;
    }

    public List<CommitteeMember> getMembers()
    {
        return members;
    }

    public void saveFromUpdate()
    {
        if (members == null)
        {
            return;
        }
        int totalRowsUpdated = 0;
        for (CommitteeMember c : this.members)
        {
            if (c.isModify())
            {
                try
                {
                    System.out.println("");
                    int rowsUpdated = this.dmc.update(c);
                    if (rowsUpdated > 0)
                    {
                        c.setModify(false);
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
            members = dmc.listAll();
        }
        catch (Exception e)
        {
            members = null;
        }
    }

    public void displayExceptionMessagesAtPreRenderListener(ComponentSystemEvent event)
    {
        if (members == null)
        {
            addMessage("Database Problem occurred.",
                    "", FacesMessage.SEVERITY_ERROR);
        }
    }
}
