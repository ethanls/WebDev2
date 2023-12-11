/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import edu.asdv.slcc.bl.DatabaseManipulationTeam;
import edu.asdv.slcc.bl.Team;
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
@Named(value = "teamBean")
@SessionScoped
public class TeamBean implements Serializable
{

    List<Team> teams;

    public void insertTeam()
    {
        System.out.println("InsertPlayer(Player p)");
        teams.add(new Team());
    }
    
    public void deleteTeam()
    {
        int count = 0;
        if (teams == null)
            return;
        for (Team t : this.teams)
        {
            if (t.isModify())
            {
                try
                {
                    count += this.dmt.delete(t);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this supplier.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.teams = dmt.listAll();
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

    public void saveToDatabase(Team t)
    {
        int num = 0;
        try
        {
            num = dmt.insert(t);
        }
        catch (SQLException e)
        {
            addMessage("Cannot insert this team.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        try
        {
            this.teams = dmt.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (num == 0)
        {
            addMessage("Error adding team.", "", FacesMessage.SEVERITY_ERROR);
        }
        else if (num > 0)
        {
            addMessage("Team added.", "", FacesMessage.SEVERITY_INFO);
        }
        

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
    DatabaseManipulationTeam dmt;

    public DatabaseManipulationTeam getDmt()
    {
        return dmt;
    }

    public List<Team> getTeams()
    {
        return teams;
    }

    public void saveFromUpdate()
    {
        if (teams == null)
        {
            return;
        }
        int totalRowsUpdated = 0;
        for (Team t : this.teams)
        {
            if (t.isModify())
            {
                try
                {
                    System.out.println("");
                    int rowsUpdated = this.dmt.update(t);
                    if (rowsUpdated > 0)
                    {
                        t.setModify(false);
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
            teams = dmt.listAll();
        }
        catch (Exception e)
        {
            teams = null;
        }
    }

    public void displayExceptionMessagesAtPreRenderListener(ComponentSystemEvent event)
    {
        if (teams == null)
        {
            addMessage("Database Problem occurred.",
                    "", FacesMessage.SEVERITY_ERROR);
        }
    }

}
