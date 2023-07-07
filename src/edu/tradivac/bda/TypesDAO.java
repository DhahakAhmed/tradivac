/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.bda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import edu.tradivac.entities.Types;

/**
 *
 * @author Meriem
 */
public class TypesDAO {

    private Connection conex;

    public TypesDAO() {

    }

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }
    
    

    public ArrayList<Types> objetotype() throws SQLException {
        ArrayList<Types> conjuntotypes = new ArrayList<>();

        String sql = "SELECT * FROM service";
        PreparedStatement ps = conex.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id_service");
            String nombre = rs.getString("type");

            Types type = new Types(id, nombre);

            conjuntotypes.add(type);

        }
        return conjuntotypes;
    }

}
