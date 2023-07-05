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
import org.apache.commons.codec.digest.DigestUtils;
import edu.tradivac.entities.Pack;
import edu.tradivac.entities.User;

/**
 *
 * @author itoga
 */
public class UserDAO {
    private Connection conex;

    public UserDAO() {
    }

    public Connection getConex() {
        return conex;
    }

    public void setConex(Connection conex) {
        this.conex = conex;
    }
    
    public void registraruser(User user) throws SQLException{
        String sql = "INSERT INTO users (id, rol, nombre, mail, mobile, card, `password`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conex.prepareStatement(sql);

        ps.setInt(1, user.getId());
        ps.setString(2, user.getRol());
        ps.setString(3, user.getNombre());
        ps.setString(4, user.getMail());
        ps.setInt(5, user.getPhone());
        ps.setLong(6, user.getCard());
        ps.setString(7, user.getPwd());

        ps.executeUpdate();
    }
    
    public boolean checkuserYaRegistrado(String mail) throws SQLException{
        String sql = "SELECT mail FROM users WHERE mail = ?";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, mail);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if(rs.getString("mail").equalsIgnoreCase(mail)){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkPWD(String pwd) throws SQLException{
        String sql = "SELECT password FROM users WHERE password = ?";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String pwdMD5=DigestUtils.md5Hex(pwd);
        ps.setString(1, pwdMD5);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if(rs.getString("password").equalsIgnoreCase(pwdMD5)){
                return true;
            }
        }
        return false;
    }
    
    public String nombreuser(Pack pack) throws SQLException{
        String sql = "SELECT users.nombre FROM packs,users WHERE packs.user_id = users.id && packs.id = ?;";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setInt(1, pack.getIdPack());
        ResultSet rs = ps.executeQuery();
        String nombre = "";
        while (rs.next()) {
            nombre = rs.getString("users.nombre");
        }
        return nombre;
    }
    
    public int getIduser(String mail) throws SQLException{
        String sql = "SELECT id FROM users WHERE mail = ?";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, mail);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }
    
    public String getRol(int iduser) throws SQLException{
        String sql = "SELECT rol FROM users WHERE id = ?;";

        PreparedStatement ps = conex.prepareCall(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setInt(1, iduser);
        ResultSet rs = ps.executeQuery();
        String rol = "";
        while (rs.next()) {
            rol = rs.getString("rol");
        }
        return rol;
    }
}
