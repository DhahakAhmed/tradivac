/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.bda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import edu.tradivac.entities.Activity;

/**
 *
 * @author itoga
 */
public class ActivityDAO {

    private Connection conex;

    public ActivityDAO() {
    }

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }

    public boolean insertaractivities(Activity activity) throws SQLException {
        String sql = "INSERT INTO activities VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conex.prepareStatement(sql);

        ps.setInt(1, activity.getIdActivity());
        ps.setString(2, activity.getNombre());
        ps.setString(3, activity.getDescription());
        ps.setString(4, activity.getImage());
        ps.setString(5, activity.getUrl());
        ps.setString(6, String.valueOf(activity.getQuality()));
        ps.setString(7, activity.gettype());
        ps.setDouble(8, activity.getPrice());
        ps.setString(9, null);

        int numRows = ps.executeUpdate();
        if (numRows > 0) {
            return true; //Se ha ejecutado el insert
        } else {
            return false;
        }
    }

    public boolean borraractivity(Activity activity) throws SQLException {
        String sql = "DELETE FROM activities WHERE id = ?;";
        PreparedStatement ps = conex.prepareStatement(sql);

        ps.setInt(1, activity.getIdActivity());

        int numRows = ps.executeUpdate();
        if (numRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editaractivity(Activity activityModif) throws SQLException {
        String sql = "UPDATE activities SET nombre = ?, description = ?, imagen = ?,"
                + " url = ?, quality = ?, type = ?, price = ? WHERE id = ?;";
        PreparedStatement ps = conex.prepareStatement(sql);
  
        ps.setString(1, activityModif.getNombre());
        ps.setString(2, activityModif.getDescription());
        ps.setString(3, activityModif.getImage());
        ps.setString(4, activityModif.getUrl());
        ps.setString(5, String.valueOf(activityModif.getQuality()));
        ps.setString(6, activityModif.gettype());
        ps.setDouble(7, activityModif.getPrice());
        ps.setInt(8, activityModif.getIdActivity());

        int numRows = ps.executeUpdate();
        if (numRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Activity> obteneractivities(int typeAct) throws SQLException {
        ArrayList<Activity> conjuntoactivities = new ArrayList<>();

        String sql = "SELECT * FROM activities WHERE type = ?;";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setInt(1, typeAct);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String description = rs.getString("description");
            String image = rs.getString("imagen");
            String url = rs.getString("url");
            int quality = rs.getInt("quality");
            String type = rs.getString("type");
            double price = rs.getDouble("price");
            String location = rs.getString("location");

            //create activity y añadir a HashSet
            Activity activity
                    = new Activity(id, nombre, description, image, url, quality, price, type, location);
            conjuntoactivities.add(activity);
        }
        return conjuntoactivities;
    }

    public int ultimoIdactivity() throws SQLException {

        String sql = "SELECT MAX(id) FROM activities;";

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

    public ArrayList<Activity> obtenerTodasactivities() throws SQLException {
        ArrayList<Activity> conjuntoactivities = new ArrayList<>();

        String sql = "SELECT * FROM activities;";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String description = rs.getString("description");
            String image = rs.getString("imagen");
            String url = rs.getString("url");
            int quality = rs.getInt("quality");
            String type = rs.getString("type");
            double price = rs.getDouble("price");

            //create activity y añadir a HashSet
            Activity activity
                    = new Activity(id, nombre, description, image, url, quality, price, type);
            conjuntoactivities.add(activity);
        }
        return conjuntoactivities;
    }
}
