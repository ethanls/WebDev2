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

/**
 *
 * @author ASDV
 */
public class DatabaseManipulationPlayer implements DBase<Player>, Serializable
{

    @Override
    public ArrayList<Player> listAll()
            throws SQLException
    {
        //test exception code
//        if (true)
//        {
//            throw new SQLException();
//        }
        ArrayList<Player> tablePlayers = new ArrayList<Player>();
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
        String sqlStr = "SELECT  *  FROM players";
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
                int pNo = rs.getInt(1);
                String name = rs.getString(2);
                String init = rs.getString(3);
                String bdate = rs.getDate(4) + "";
                char sex = rs.getString(5).charAt(0);
                String joined = rs.getDate(6) + "";
                String street = rs.getString(7);
                String houseno = rs.getString(8);
                String postcode = rs.getString(9);
                String town = rs.getString(10);
                String phoneno = rs.getString(11);
                int leagueno = rs.getInt(12);
                Player p = new Player(pNo, name, init, bdate, sex, joined, street, houseno, postcode, town, phoneno, leagueno);
                tablePlayers.add(p);
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

        return tablePlayers;
    }

    @Override
    public int update(Player t)
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
        PreparedStatement updatePlayer = null;
        try
        {

            updatePlayer = con.prepareStatement(
                    "UPDATE players SET playerno=?, name=?, init=?, "
                    + "birth_date=?, sex=?, joined=YEAR(?), street=?, "
                    + "houseno=?, postcode=?, town=?, phoneno=?, "
                    + "leagueno=? WHERE playerno=?");

            updatePlayer.setInt(1, t.getPlayerno());
            updatePlayer.setString(2, t.getName());
            updatePlayer.setString(3, t.getInit());
            String date = t.getBirth_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(date);
            updatePlayer.setDate(4, sqlDate);
            updatePlayer.setString(5, t.getSex() + "");
            String joined = t.getJoined();
            Date sqlJoined = UtilitiesDatabase.stringDateToSqlYear(joined);
            System.out.println(sqlJoined);
            updatePlayer.setDate(6, sqlJoined);
            updatePlayer.setString(7, t.getStreet());
            updatePlayer.setString(8, t.getHouseno());
            updatePlayer.setString(9, t.getPostcode());
            updatePlayer.setString(10, t.getTown());
            updatePlayer.setString(11, t.getPhoneno());
            updatePlayer.setInt(12, t.getLeagueno());
            updatePlayer.setInt(13, t.getPlayerno());
            result = updatePlayer.executeUpdate();

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
                if (updatePlayer != null)
                {
                    updatePlayer.close();
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
    public int delete(Player t) throws SQLException
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
            String query = "DELETE FROM players WHERE playerno=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, t.getPlayerno());
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
    public int insert(Player t) throws SQLException
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
        PreparedStatement updatePlayer = null;
        updatePlayer = con.prepareStatement(
                    "INSERT INTO `players` (`playerno`, `name`, `init`, `birth_date`, `sex`, `joined`, `street`, `houseno`, `postcode`, `town`, `phoneno`, `leagueno`) "
                            + "VALUES (?, ?, ?, ?, ?, YEAR(?), ?, ?, ?, ?, ?, ?)");
        try
        {
            updatePlayer.setInt(1, t.getPlayerno());
            updatePlayer.setString(2, t.getName());
            updatePlayer.setString(3, t.getInit());
            String date = t.getBirth_date();
            Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(date);
            updatePlayer.setDate(4, sqlDate);
            updatePlayer.setString(5, t.getSex() + "");
            String joined = t.getJoined();
            Date sqlJoined = UtilitiesDatabase.stringDateToSqlYear(joined);
            updatePlayer.setDate(6, sqlJoined);
            updatePlayer.setString(7, t.getStreet());
            updatePlayer.setString(8, t.getHouseno());
            updatePlayer.setString(9, t.getPostcode());
            updatePlayer.setString(10, t.getTown());
            updatePlayer.setString(11, t.getPhoneno());
            updatePlayer.setInt(12, t.getLeagueno());

            int updateCount = updatePlayer.executeUpdate();

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
                if (updatePlayer != null)
                {
                    updatePlayer.close();
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
        DatabaseManipulationPlayer dmp = new DatabaseManipulationPlayer();
        ArrayList<Player> ar;
        try
        {
            ar = dmp.listAll();
            Player p = ar.get(3);
            System.out.println(p);
            p.setJoined("1982");
            p.setName("Mike");
            p.setBirth_date("1999-07-09");
            dmp.update(p);
            System.out.println(p);
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
        
        

    }
}
