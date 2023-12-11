/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.asdv.slcc.bl.Team;

/**
 *
 * @author ASDV
 */
public class DatabaseManipulationMatch implements DBase<Match>, Serializable
{

    @Override
    public ArrayList<Match> listAll()
            throws SQLException
    {
        //test exception code
//        if (true)
//        {
//            throw new SQLException();
//        }
        ArrayList<Match> tableMatches = new ArrayList<Match>();
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
        String sqlStr = "SELECT  *  FROM matches";
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
                int matchno = rs.getInt(1);
                int teamno = rs.getInt(2);
                int playerno = rs.getInt(3);
                int won = rs.getInt(4);
                int lost = rs.getInt(5);
                Match m = new Match(matchno, teamno, playerno, won, lost);
                tableMatches.add(m);
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

        return tableMatches;
    }

    @Override
    public int update(Match m)
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
        PreparedStatement updateMatch = null;
        try
        {

//            updatePlayer = con.prepareStatement(
//                    "UPDATE players SET playerno=?, name=?, init=?, "
//                    + "birth_date=?, sex=?, joined=YEAR(?), street=?, "
//                    + "houseno=?, postcode=?, town=?, phoneno=?, "
//                    + "leagueno=? WHERE playerno=?");
            updateMatch = con.prepareStatement(
                    "UPDATE matches SET matchno=?, teamno=?, playerno=?, won=?, lost=? "
                    + "WHERE matchno=?");

            updateMatch.setInt(1, m.getMatchno());
            updateMatch.setInt(2, m.getTeamno());
            updateMatch.setInt(3, m.getPlayerno());
            updateMatch.setInt(4, m.getWon());
            updateMatch.setInt(5, m.getLost());
            updateMatch.setInt(6, m.getMatchno());
            System.out.println(updateMatch);
            
            result = updateMatch.executeUpdate();

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
                if (updateMatch != null)
                {
                    updateMatch.close();
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
    public int delete(Match m) throws SQLException
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
            String query = "DELETE FROM matches WHERE matchno=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, m.getMatchno());
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
    public int insert(Match t) throws SQLException
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
        PreparedStatement updateMatch = null;
        updateMatch = con.prepareStatement(
                    "INSERT INTO `matches` (`matchno`, `teamno`, `playerno`, `won`, `lost`) "
                            + "VALUES (?, ?, ?, ?, ?)");
        try
        {
            updateMatch.setInt(1, t.getMatchno());
            updateMatch.setInt(2, t.getTeamno());
            updateMatch.setInt(3, t.getPlayerno());
            updateMatch.setInt(4, t.getWon());
            updateMatch.setInt(5, t.getLost());
            
            System.out.println(updateMatch);

            int updateCount = updateMatch.executeUpdate();

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
                if (updateMatch != null)
                {
                    updateMatch.close();
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


    }
}
