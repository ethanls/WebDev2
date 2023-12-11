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
public class Penalty
{
    int paymentno;
    int playerno;
    String payment_date;
    double amount;
    boolean modify;
    boolean keyModify;
    private Date pdate;

    public Date getPdate()
    {
        pdate = UtilitiesDatabase.stringDateToUtilDate(this.payment_date);
        return pdate;
    }

    public void setPdate(Date pdate)
    {
        this.pdate = pdate;
        int year = pdate.getYear() + 1900;
        int month = pdate.getMonth() + 1;
        int day = pdate.getDate();
        
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
        this.payment_date = s;
    }
    
    

    public Penalty(int paymentno, int playerno, String payment_date, double amount)
    {
        this.paymentno = paymentno;
        this.playerno = playerno;
        this.payment_date = payment_date;
        this.amount = amount;
    }

    public Penalty()
    {
        this.payment_date = "0000-00-00";
        this.modify = true;
        this.keyModify = true;
    }

    @Override
    public String toString()
    {
        return "Penalty{" + "paymentno=" + paymentno + ", playerno=" + playerno + ", payment_date=" + payment_date + ", amount=" + amount + '}';
    }
    
    
    

    public int getPaymentno()
    {
        return paymentno;
    }

    public void setPaymentno(int paymentno)
    {
        this.paymentno = paymentno;
    }

    public int getPlayerno()
    {
        return playerno;
    }

    public void setPlayerno(int playerno)
    {
        this.playerno = playerno;
    }

    public String getPayment_date()
    {
        return payment_date;
    }

    public void setPayment_date(String payment_date)
    {
        this.payment_date = payment_date;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
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
