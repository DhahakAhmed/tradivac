/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.bda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Meriem
 */
public class ConexionDAO {
    
   private Connection conexion;

    public ConexionDAO() {
        
        
    }

    public boolean connect(String user, String pwd, String bda) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/" + bda + "?serverTimezone=UTC";

        conexion = DriverManager.getConnection(url, user, pwd);

        if (conexion != null) {
            return true;
        } else {
            return false;
        }

    }

    public Connection getConexion() {
        return conexion;
    }
    
    
    
}
