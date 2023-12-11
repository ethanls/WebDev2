/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.slcc.beans;

import edu.asdv.slcc.bl.Player;
import edu.asdv.slcc.bl.DatabaseManipulationPlayer;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ethan
 */
@Named(value = "playerBean")
@SessionScoped
public class PlayerBean implements Serializable
{

    List<Player> players;

    public void insertPlayer()
    {
        System.out.println("InsertPlayer(Player p)");
        players.add(new Player());
    }

    public void saveToDatabase(Player p)
    {
        System.out.println(p);
        int num = 0;
        try
        {
            num = dmp.insert(p);
        }
        catch (SQLException e)
        {
            addMessage("Cannot insert this player.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

        try
        {
            this.players = dmp.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (num == 0)
        {
            addMessage("Error adding player.", "", FacesMessage.SEVERITY_ERROR);
        }
        else if (num > 0)
            addMessage("Player added.", "", FacesMessage.SEVERITY_INFO);

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
    DatabaseManipulationPlayer dmp;

    public DatabaseManipulationPlayer getDmp()
    {
        return dmp;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public void saveFromUpdate()
    {
        if (players == null)
        {
            return;
        }
        int totalRowsUpdated = 0;
        for (Player p : this.players)
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
    
    public void deletePlayer()
    {
        int count = 0;
        if (players == null)
            return;
        for (Player p : this.players)
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
            this.players = dmp.listAll();
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
            players = dmp.listAll();
        }
        catch (Exception e)
        {
            players = null;
        }
    }

    public void displayExceptionMessagesAtPreRenderListener(ComponentSystemEvent event)
    {
        if (players == null)
        {
            addMessage("Database Problem occurred.",
                    "", FacesMessage.SEVERITY_ERROR);
        }
    }
}
