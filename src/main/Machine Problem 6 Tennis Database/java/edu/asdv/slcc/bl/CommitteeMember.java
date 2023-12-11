/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

import java.util.Date;

/**
 *
 * @author ethan
 */
public class CommitteeMember
{
    int playerno;
    String begin_date;
    String end_date;
    String position;
    boolean modify;
    boolean keyModify;
    private Date bdate;
    private Date edate;

    @Override
    public String toString()
    {
        return "CommitteeMember{" + "playerno=" + playerno + ", begin_date=" + begin_date + ", end_date=" + end_date + ", position=" + position + '}';
    }
    
    
    
    public Date getBdate()
    {
        bdate = UtilitiesDatabase.stringDateToUtilDate(this.begin_date);
        return bdate;
    }

    public void setBdate(Date bdate)
    {
        this.bdate = bdate;
        int year = bdate.getYear() + 1900;
        int month = bdate.getMonth() + 1;
        int day = bdate.getDate();
        
        String s = year + "";
        s += "-";
        if (month < 10)
        {
            s += "0" + month + "";
        }
        else
        {
            s += month + "";
        }
        s += "-";
        if (day < 10)
        {
            s += "0" + day + "";
        }
        else
        {
            s += day + "";
        }
        this.begin_date = s;
    }

    public Date getEdate()
    {
        edate = UtilitiesDatabase.stringDateToUtilDate(this.end_date);
        return edate;
    }

    public void setEdate(Date edate)
    {
        this.edate = edate;
        int year = edate.getYear() + 1900;
        int month = edate.getMonth() + 1;
        int day = edate.getDate();
        
        String s = year + "";
        s += "-";
        if (month < 10)
        {
            s += "0" + month + "";
        }
        else
        {
            s += month + "";
        }
        s += "-";
        if (day < 10)
        {
            s += "0" + day + "";
        }
        else
        {
            s += day + "";
        }
        this.end_date = s;
    }

    public CommitteeMember(int playerno, String begin_date, String end_date, String position)
    {
        this.playerno = playerno;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.position = position;
    }

    public CommitteeMember()
    {
        this.begin_date = "0000-00-00";
        this.end_date = "0000-00-00";
        this.modify = true;
        this.keyModify = true;
    }
    
    public int getPlayerno()
    {
        return playerno;
    }

    public void setPlayerno(int playerno)
    {
        this.playerno = playerno;
    }

    public String getBegin_date()
    {
        return begin_date;
    }

    public void setBegin_date(String begin_date)
    {
        this.begin_date = begin_date;
    }

    public String getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(String end_date)
    {
        this.end_date = end_date;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
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
