/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.bda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import edu.tradivac.entities.Activity;
import edu.tradivac.entities.DetailsPack;

/**
 *
 * @author Meriem
 */
public class DetailsPackDAO {

    private Connection conex;

    public DetailsPackDAO() {
    }

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }

    public boolean insertarDetalle(DetailsPack linea) throws SQLException {
        String sql = "INSERT INTO packs_details VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conex.prepareStatement(sql);

//        java.sql.Time durationDetalle = java.sql.Time.valueOf(linea.getduration());
        ps.setInt(1, linea.getIdPack());
        ps.setInt(2, linea.getNumLinea());
        ps.setInt(3, linea.getIdActivity());
        ps.setInt(4, linea.getNumPlaces());
        ps.setDate(5, Date.valueOf(linea.getStartDate()));
        ps.setDate(6, Date.valueOf(linea.getFinalDate()));
        ps.setTime(7, Time.valueOf(linea.getDuration()));
//        ps.setInt(7, );

        int numRows = ps.executeUpdate();
        if (numRows > 0) {
            return true; //Se ha ejecutado el insert
        } else {
            return false;
        }
    }

    public boolean deletedetaillePack(Activity activity) throws SQLException {
        String sql = "DELETE FROM packs_details WHERE id_activity = ?;";
        PreparedStatement ps = conex.prepareStatement(sql);

        ps.setInt(1, activity.getIdActivity());

        int numRows = ps.executeUpdate();
        if (numRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteDetailPack(Activity activitySelecc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
