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
public class DatabaseManipulationCommitteeMember implements DBase<CommitteeMember>, Serializable
{

    @Override
    public ArrayList<CommitteeMember> listAll()
            throws SQLException
    {
        //test exception code
//        if (true)
//        {
//            throw new SQLException();
//        }
        ArrayList<CommitteeMember> tableC = new ArrayList<CommitteeMember>();
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
        String sqlStr = "SELECT  *  FROM committee_member";
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
                int playerno = rs.getInt(1);
                String begin_date = rs.getDate(2) + "";
                String end_date;
                String position;
                if (rs.getDate(3) == null)
                {
                    end_date = "0000-00-00";
                }
                else
                {
                    end_date = rs.getDate(3) + "";
                }
                if (rs.getString(4) == null)
                {
                    position = "";
                }
                else
                {
                    position = rs.getString(4);
                }
                CommitteeMember c = new CommitteeMember(playerno, begin_date, end_date, position);
                tableC.add(c);
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

        return tableC;
    }

    @Override
    public int update(CommitteeMember t)
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
        PreparedStatement updateC = null;
        try
        {

            updateC = con.prepareStatement(
                    "UPDATE committee_member SET playerno=?, begin_date=?, end_date=?, "
                    + "position=? WHERE playerno=? AND begin_date=?");

            updateC.setInt(1, t.getPlayerno());
            String b_date = t.getBegin_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(b_date);
            updateC.setDate(2, sqlDate);
            String e_date = t.getEnd_date();
            Date sqlDate2 = UtilitiesDatabase.stringDateToSqlDate(e_date);
            updateC.setDate(3, sqlDate2);
            updateC.setString(4, t.getPosition());
            updateC.setInt(5, t.getPlayerno());
            updateC.setDate(6, sqlDate);
            
            System.out.println(updateC);
            
            result = updateC.executeUpdate();

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
                if (updateC != null)
                {
                    updateC.close();
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
    public int delete(CommitteeMember t) throws SQLException
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
            String query = "DELETE FROM committee_member WHERE playerno=? AND begin_date=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, t.getPlayerno());
            String b_date = t.getBegin_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(b_date);
            ps.setDate(2, sqlDate);
            
            System.out.println(ps);
            
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
    public int insert(CommitteeMember t) throws SQLException
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
        PreparedStatement updateC = null;
        updateC = con.prepareStatement(
                    "INSERT INTO `committee_member` (`playerno`, `begin_date`, `end_date`, `position`) "
                            + "VALUES (?, ?, ?, ?)");
        try
        {
            updateC.setInt(1, t.getPlayerno());
            String b_date = t.getBegin_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(b_date);
            updateC.setDate(2, sqlDate);
            String e_date = t.getEnd_date();
            Date sqlDate2 = UtilitiesDatabase.stringDateToSqlDate(e_date);
            updateC.setDate(3, sqlDate2);
            updateC.setString(4, t.getPosition());
            System.out.println(updateC);
            int updateCount = updateC.executeUpdate();

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
                if (updateC != null)
                {
                    updateC.close();
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
            DatabaseManipulationCommitteeMember dmc = new DatabaseManipulationCommitteeMember();
            ArrayList<CommitteeMember> ar;
            ar = dmc.listAll();
            for (CommitteeMember c : ar)
            {
                System.out.println(c);
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
    }
}
