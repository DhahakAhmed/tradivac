/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author devhk
 */
public class MySQLConnection {
    private String url="jdbc:mysql://localhost:3306/tradivac";
    private String login="root2";
    private String pwd="rootmdp";
    Connection cnx;
    
    public MySQLConnection(){
        try {
             cnx = DriverManager.getConnection(url,login,pwd);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getCnx(){
        return cnx;
    }
}
