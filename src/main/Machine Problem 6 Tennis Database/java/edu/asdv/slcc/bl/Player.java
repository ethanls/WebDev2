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
public class Player
{
    private int playerno;
    private String name;
    private String init;
    private String birth_date;
    private char sex;
    private String joined;
    private String street;
    private String houseno;
    private String postcode;
    private String town;
    private String phoneno;
    private int leagueno;
    private boolean modify;
    private boolean keyModify;
    private Date birthday_date;
    private Date join_date;

    public Player(int playerno, String name, String init, String birth_date, char sex, String joined, String street, String houseno, String postcode, String town, String phoneno, int leagueno)
    {
        this.playerno = playerno;
        this.name = name;
        this.init = init;
        this.birth_date = birth_date;
        this.sex = sex;
        this.joined = joined;
        this.street = street;
        this.houseno = houseno;
        this.postcode = postcode;
        this.town = town;
        this.phoneno = phoneno;
        this.leagueno = leagueno;
    }

    public Player()
    {
        modify = true;
        keyModify = true;
        birth_date = "0000-00-00";
        birthday_date = UtilitiesDatabase.stringDateToUtilDate(birth_date);
    }

    @Override
    public String toString()
    {
        return "Player{" + "playerno=" + playerno + ", name=" + name + ", init=" + init + ", birth_date=" + birth_date + ", sex=" + sex + ", joined=" + joined + ", street=" + street + ", houseno=" + houseno + ", postcode=" + postcode + ", town=" + town + ", phoneno=" + phoneno + ", leagueno=" + leagueno + ", modify=" + modify + ", keyModify=" + keyModify + ", birthday_date=" + birthday_date + ", join_date=" + join_date + '}';
    }
    
    

    public Date getJoin_date()
    {
        join_date = UtilitiesDatabase.stringDateToUtilDate(this.birth_date);
        return join_date;
    }

    public void setJoin_date(Date join_date)
    {
        this.join_date = join_date;
        int year = join_date.getYear() + 1900;
        
        String s = year + "";

        this.joined = s;
    }

    public Date getBirthday_date()
    {
        birthday_date = UtilitiesDatabase.stringDateToUtilDate(this.birth_date);
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date)
    {
       this.birthday_date = birthday_date;
        int year = birthday_date.getYear() + 1900;
        int month = birthday_date.getMonth() + 1;
        int day = birthday_date.getDate();
        
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
        this.birth_date = s;
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
    
    
    
    

    public int getPlayerno()
    {
        return playerno;
    }

    public void setPlayerno(int playerno)
    {
        this.playerno = playerno;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getInit()
    {
        return init;
    }

    public void setInit(String init)
    {
        this.init = init;
    }

    public String getBirth_date()
    {
        return birth_date;
    }

    public void setBirth_date(String birth_date)
    {
        this.birth_date = birth_date;
    }

    public char getSex()
    {
        return sex;
    }

    public void setSex(char sex)
    {
        this.sex = sex;
    }

    public String getJoined()
    {
        return joined;
    }

    public void setJoined(String joined)
    {
        this.joined = joined;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHouseno()
    {
        return houseno;
    }

    public void setHouseno(String houseno)
    {
        this.houseno = houseno;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getPhoneno()
    {
        return phoneno;
    }

    public void setPhoneno(String phoneno)
    {
        this.phoneno = phoneno;
    }

    public int getLeagueno()
    {
        return leagueno;
    }

    public void setLeagueno(int leagueno)
    {
        this.leagueno = leagueno;
    }
    
    
}
