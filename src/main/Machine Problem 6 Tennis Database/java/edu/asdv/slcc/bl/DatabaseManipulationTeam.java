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
public class DatabaseManipulationTeam implements DBase<Team>, Serializable
{

    @Override
    public ArrayList<Team> listAll()
            throws SQLException
    {
        //test exception code
//        if (true)
//        {
//            throw new SQLException();
//        }
        ArrayList<Team> tableTeams = new ArrayList<Team>();
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
        String sqlStr = "SELECT  *  FROM teams";
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
                int teamno = rs.getInt(1);
                int playerno = rs.getInt(2);
                String division = rs.getString(3);
                Team t = new Team(teamno, playerno, division);
                tableTeams.add(t);
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

        return tableTeams;
    }

    @Override
    public int update(Team t)
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
        PreparedStatement updateTeam = null;
        try
        {

//            updatePlayer = con.prepareStatement(
//                    "UPDATE players SET playerno=?, name=?, init=?, "
//                    + "birth_date=?, sex=?, joined=YEAR(?), street=?, "
//                    + "houseno=?, postcode=?, town=?, phoneno=?, "
//                    + "leagueno=? WHERE playerno=?");
            updateTeam = con.prepareStatement(
                    "UPDATE teams SET teamno=?, playerno=?, division=? "
                    + "WHERE teamno=?");

            updateTeam.setInt(1, t.getTeamno());
            updateTeam.setInt(2, t.getPlayerno());
            updateTeam.setString(3, t.getDivision());
            updateTeam.setInt(4, t.getTeamno());
            System.out.println(updateTeam);
            
            result = updateTeam.executeUpdate();

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
                if (updateTeam != null)
                {
                    updateTeam.close();
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
    public int delete(Team t) throws SQLException
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
            String query = "DELETE FROM teams WHERE teamno=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, t.getTeamno());
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
    public int insert(Team t) throws SQLException
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
        PreparedStatement updateTeam = null;
        updateTeam = con.prepareStatement(
                    "INSERT INTO `teams` (`teamno`, `playerno`, `division`) "
                            + "VALUES (?, ?, ?)");
        try
        {
            updateTeam.setInt(1, t.getTeamno());
            updateTeam.setInt(2, t.getPlayerno());
            updateTeam.setString(3, t.getDivision());
            
            System.out.println(updateTeam);

            int updateCount = updateTeam.executeUpdate();

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
                if (updateTeam != null)
                {
                    updateTeam.close();
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
