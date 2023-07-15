/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author wassou
 */
public class MySQLConnection {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tradivac?";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PWD = "";
    
    private Connection connection = null;
    
    public static MySQLConnection instance = null;
    
    public static synchronized MySQLConnection getInstance() 
    {
        if (Objects.isNull(instance))
            instance = new MySQLConnection();
        return instance;
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
    private MySQLConnection()
    {
        try 
        {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
            System.out.println("Connexion Etablie....");
        } catch (SQLException ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
