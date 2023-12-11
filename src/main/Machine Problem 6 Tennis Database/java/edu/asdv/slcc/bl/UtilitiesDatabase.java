/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ASDV
 */
public class UtilitiesDatabase 
{
    
   public static void closeDatabaseConnection( Connection con)
    {
        try
          {
            if (con != null)
              {
                con.close();
              }
          }
        catch (SQLException e)
          {
              System.out.println(e);
              e.printStackTrace();
          }
    }
   
    public static Connection connection(String databaseName, String userName, String password, String URL2) //throws InstantiationException, IllegalAccessException
    {

//        String databaseName = "suppliers_parts_23";
//        String userName = "root";
//        String password = "";
//        String URL2 = "com.mysql.jdbc.Driver";

        Connection con = null;
        try
          {// Load Sun's jdbc driver
            Class.forName(URL2).newInstance();
            System.out.println("JDBC Driver loaded!");
          }
        catch (Exception e) // driver not found
          {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
          }
        String ip = "localhost"; //internet connection
        String url = "jdbc:mysql://" + ip + ":3306/" + databaseName + "?useSSL=false";
        try
          {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
          }
        catch (Exception e)
          {
            System.err.println(e.toString());
            return null;
          }
        System.out.println("connection successfull");
        return con;
    }
    
    public static java.sql.Date stringDateToSqlDate(String sDate)
    {
        java.util.Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
          {
            date = sdf.parse(sDate);
          }
        catch (ParseException e)
          {
            e.printStackTrace();
          }
        return new  java.sql.Date( date.getTime() );
        
    }
    
    public static java.sql.Date stringDateToSqlYear(String sDate)
    {
        java.util.Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try
          {
            date = sdf.parse(sDate);
          }
        catch (ParseException e)
          {
            e.printStackTrace();
          }
        return new  java.sql.Date( date.getTime() );
        
    }
    
    public static java.util.Date stringDateToUtilDate(String sDate)
    {
        java.sql.Date sqlDate = stringDateToSqlDate(sDate);
        java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
        return javaDate;
    }
}
