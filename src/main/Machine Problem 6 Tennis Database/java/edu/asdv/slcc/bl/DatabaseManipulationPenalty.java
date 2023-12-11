/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASDV
 */
public class DatabaseManipulationPenalty implements DBase<Penalty>, Serializable
{

    @Override
    public ArrayList<Penalty> listAll()
            throws SQLException
    {
        //test exception code
//        if (true)
//        {
//            throw new SQLException();
//        }
        ArrayList<Penalty> tablePenalties = new ArrayList<Penalty>();
        Connection con = UtilitiesDatabase.connection("tennis_23", "root", "", "com.mysql.jdbc.Driver");

        try
        {
            if (con == null)
            {
                throw new RuntimeException("I cannot connect to the database.");
            }
        }
        catch (RuntimeException e)
        {
            System.err.println(e);
        }

        String table = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM penalties";
        try
        {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            int row = 0;
            while (rs.next())
            {
//                String sNumber = rs.getString(1);
//                String sName = rs.getString(2);
//                String bdate = rs.getDate(3) + "";
//                int status = rs.getInt(4);
//                String city = rs.getString(5);
//                Supplier sup = new Supplier(sNumber.trim(), sName.trim(), bdate.trim(), status, city.trim());
//                tableSuppliers.add(sup);
                int paymentno = rs.getInt(1);
                int playerno = rs.getInt(2);
                String payment_date = rs.getDate(3) + "";
                double amount = rs.getDouble(4);
                Penalty p = new Penalty(paymentno, playerno, payment_date, amount);
                tablePenalties.add(p);
                row++;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                UtilitiesDatabase.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null)
                {
                    ps.close();
                }
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
                throw sqle;
            }
        }

        return tablePenalties;
    }

    @Override
    public int update(Penalty t)
            throws SQLException
    {
        int result = 0;
        Connection con = UtilitiesDatabase.connection("tennis_23", "root", "", "com.mysql.jdbc.Driver");
        try
        {
            if (con == null)
            {
                throw new RuntimeException("I cannot connect to the database.");
            }
        }
        catch (RuntimeException e)
        {
            System.err.println(e);
        }
        PreparedStatement updatePenalty = null;
        try
        {

            updatePenalty = con.prepareStatement(
                    "UPDATE penalties SET paymentno=?, playerno=?, payment_date=?, "
                    + "amount=? WHERE paymentno=?");

            updatePenalty.setInt(1, t.getPaymentno());
            updatePenalty.setInt(2, t.getPlayerno());
            String p_date = t.getPayment_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(p_date);
            updatePenalty.setDate(3, sqlDate);
            updatePenalty.setDouble(4, t.getAmount());
            updatePenalty.setInt(5, t.getPaymentno());
            
            result = updatePenalty.executeUpdate();

        }
        catch (SQLException ex)
        {
            System.err.println(ex.toString());
            throw ex;
        }
        finally
        {
            try
            {
                UtilitiesDatabase.closeDatabaseConnection(con);
                // close the resources 
                if (updatePenalty != null)
                {
                    updatePenalty.close();
                }

            }
            catch (SQLException sqlee)
            {
                sqlee.printStackTrace();
                throw sqlee;
            }
        }
        return result;
    }

    @Override
    public int delete(Penalty t) throws SQLException
    {
//        if (true)
//            throw new SQLException();

        int result = 0;
        Connection con = UtilitiesDatabase.connection("tennis_23", "root", "", "com.mysql.jdbc.Driver");
        if (con == null)
        {
            throw new RuntimeException("I cannot connect to the database.");
        }
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try
        {
            String query = "DELETE FROM penalties WHERE paymentno=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, t.getPaymentno());
            rowsAffected = ps.executeUpdate();
            result++;
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
        finally
        {
            try
            {
                UtilitiesDatabase.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null)
                {
                    ps.close();
                }

            }
            catch (SQLException sqlee)
            {
                sqlee.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int insert(Penalty t) throws SQLException
    {
        int result = 0;
        Connection con = UtilitiesDatabase.connection("tennis_23", "root", "", "com.mysql.jdbc.Driver");
        try
        {
            if (con == null)
            {
                throw new RuntimeException("I cannot connect to the database.");
            }
        }
        catch (RuntimeException e)
        {
            System.err.println(e);
        }
        PreparedStatement updatePenalty = null;
        updatePenalty = con.prepareStatement(
                    "INSERT INTO `penalties` (`paymentno`, `playerno`, `payment_date`, `amount`) "
                            + "VALUES (?, ?, ?, ?)");
        try
        {
            updatePenalty.setInt(1, t.getPaymentno());
            updatePenalty.setInt(2, t.getPlayerno());
            String p_date = t.getPayment_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(p_date);
            updatePenalty.setDate(3, sqlDate);
            updatePenalty.setDouble(4, t.getAmount());
            System.out.println(updatePenalty);
            int updateCount = updatePenalty.executeUpdate();

            result = updateCount;
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
        finally
        {
            try
            {
                UtilitiesDatabase.closeDatabaseConnection(con);
                // close the resources 
                if (updatePenalty != null)
                {
                    updatePenalty.close();
                }

            }
            catch (SQLException e)
            {
                System.err.println(e.toString());
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        try
        {
            DatabaseManipulationPenalty dmp = new DatabaseManipulationPenalty();
            ArrayList<Penalty> ar;
            ar = dmp.listAll();
            for(Penalty p : ar)
            {
                System.out.println(p);
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
    }
}
