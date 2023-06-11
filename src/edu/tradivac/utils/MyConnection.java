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
 * @author Ghanmi Hichem
 */
public class MyConnection {
      private String url="jdbc:mysql://localhost:3306/notes_service";
    private String login="root";
    private String pwd="";
    Connection cnx;
    
    public MyConnection(){
        try {
         cnx = DriverManager.getConnection(url,login,pwd);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getCnx(){
        return cnx;
        
    }
    public static MyConnection instance;
    public static MyConnection  getInstance(){
        if(instance==null){
            instance=new MyConnection();
        }
          return instance;
    }
}
    

