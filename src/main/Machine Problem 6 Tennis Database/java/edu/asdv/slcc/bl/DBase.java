/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.asdv.slcc.bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASDV
 */
public interface DBase<T>
{
    ArrayList<T> listAll() throws SQLException;
    int update(T t)
            throws SQLException;
    int delete(T t)
            throws SQLException;
    int insert(T t)
            throws SQLException;
}
