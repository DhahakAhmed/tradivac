/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.bda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import edu.tradivac.entities.Pack;

/**
 *
 * @author Meriem
 */
public class PackDAO {

    private Connection conex;

    public PackDAO() {
    }

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }

    public boolean insertPack(Pack pack) throws SQLException {
        String sql = "INSERT INTO packs VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conex.prepareStatement(sql);

        ps.setInt(1, pack.getIdPack());
        ps.setString(2, pack.getNombre());
        ps.setString(3, pack.getDescription());
        ps.setTimestamp(4, Timestamp.valueOf(pack.getTimeCreacion()));
        ps.setDate(5, Date.valueOf(pack.getstartDate()));
        ps.setDate(6, Date.valueOf(pack.getfinalDate()));
        ps.setInt(7, pack.getIduser());

        int numRows = ps.executeUpdate();
        if (numRows > 0) {
            return true; //Se ha ejecutado el insert
        } else {
            return false;
        }
    }

    public int ultimopack() throws SQLException {

        String sql = "SELECT MAX(id) FROM packs;";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("MAX(id)");
        }
        return id;

    }
}
