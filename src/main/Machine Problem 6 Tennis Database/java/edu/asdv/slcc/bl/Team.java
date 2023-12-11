/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

/**
 *
 * @author ethan
 */
public class Team
{
    private int teamno;
    private int playerno;
    private String division;
    private boolean modify;
    private boolean keyModify;
    
    public int getTeamno()
    {
        return teamno;
    }

    public void setTeamno(int teamno)
    {
        this.teamno = teamno;
    }

    public int getPlayerno()
    {
        return playerno;
    }

    public void setPlayerno(int playerno)
    {
        this.playerno = playerno;
    }

    public String getDivision()
    {
        return division;
    }

    public void setDivision(String division)
    {
        this.division = division;
    }

    public boolean isModify()
    {
        return modify;
    }

    public void setModify(boolean modify)
    {
        this.modify = modify;
    }

    public boolean isKeyModify()
    {
        return keyModify;
    }

    public void setKeyModify(boolean keyModify)
    {
        this.keyModify = keyModify;
    }
    
    

    @Override
    public String toString()
    {
        return "Team{" + "teamno=" + teamno + ", playerno=" + playerno + ", division=" + division + ", modify=" + modify + ", keyModify=" + keyModify + '}';
    }
    
    

    public Team(int teamno, int playerno, String division)
    {
        this.teamno = teamno;
        this.playerno = playerno;
        this.division = division;
        this.modify = false;
    }

    public Team()
    {
        this.modify = true;
        this.keyModify = true;
    }
    
    

    
    
    
    
    
}
