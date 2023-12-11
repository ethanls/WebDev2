/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

/**
 *
 * @author ethan
 */
public class Match
{
    int matchno;
    int teamno;
    int playerno;
    int won;
    int lost;
    boolean modify;
    boolean keyModify;

    public Match(int matchno, int teamno, int playerno, int won, int lost)
    {
        this.matchno = matchno;
        this.teamno = teamno;
        this.playerno = playerno;
        this.won = won;
        this.lost = lost;
        this.modify = false;
    }

    public Match()
    {
        this.modify = true;
        this.keyModify = true;
    }

    @Override
    public String toString()
    {
        return "Match{" + "matchno=" + matchno + ", teamno=" + teamno + ", playerno=" + playerno + ", won=" + won + ", lost=" + lost + '}';
    }
    
    
    

    public int getMatchno()
    {
        return matchno;
    }

    public void setMatchno(int matchno)
    {
        this.matchno = matchno;
    }

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

    public int getWon()
    {
        return won;
    }

    public void setWon(int won)
    {
        this.won = won;
    }

    public int getLost()
    {
        return lost;
    }

    public void setLost(int lost)
    {
        this.lost = lost;
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
    
    
    
}
