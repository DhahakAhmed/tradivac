/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.Admin;
import edu.tradivac.entities.enums.UserRole;
import edu.tradivac.interfaces.IUserCrud;
import edu.tradivac.services.constants.UserQueries;
import edu.tradivac.utils.EnumMapper;
import edu.tradivac.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devhk
 */
public class AdminCrud implements IUserCrud<Admin> {
    
    public static final Connection connection = MySQLConnection.getInstance().getConnection();
    
    public static final UserRole userRole = UserRole.ADMIN;

    @Override
    public Admin addUser(Admin admin) 
    {
        String query = UserQueries.ADD_ADMIN_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getUserName());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getPhone());
            statement.setString(6, admin.getAddress());
            statement.setDate(7, admin.getCreationDate());
            statement.setDate(8, admin.getBirthDate());
            statement.setDate(9, admin.getUpdateDate());
            statement.setBoolean(10, admin.getIsOnline());
            statement.setString(11, EnumMapper.mapUserStatus(admin.getStatus()));
            statement.setString(12, EnumMapper.mapUserRole(admin.getRole()));
            statement.setString(13, EnumMapper.mapUserLanguage(admin.getLanguage()));
            statement.setString(14, EnumMapper.mapUserSexe(admin.getSexe()));
            statement.setString(15, admin.getProfilePicture());
            statement.setString(16, admin.getCountry());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding admin failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Adding admin failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            // Handle the exception appropriately
            System.out.println("Error: " + ex.getMessage());
        }

        return admin;
    }

    @Override
    public void deleteUser(int id) 
    {
        String query = UserQueries.DEL_ADMIN_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException ex) {
            // Handle the exception appropriately
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public Admin updateUser(Admin admin) 
    {
        String query = UserQueries.UPDATE_ADMIN_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getUserName());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getPhone());
            statement.setString(6, admin.getAddress());
            statement.setDate(7, admin.getUpdateDate());
            statement.setString(8, EnumMapper.mapUserStatus(admin.getStatus()));
            statement.setString(9, EnumMapper.mapUserRole(admin.getRole()));
            statement.setString(10, EnumMapper.mapUserLanguage(admin.getLanguage()));
            statement.setString(11, EnumMapper.mapUserSexe(admin.getSexe()));
            statement.setString(12, admin.getProfilePicture());
            statement.setString(13, admin.getCountry());
            statement.setInt(20, admin.getId());
            statement.setBoolean(10, admin.getIsOnline());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating admin failed, no rows affected.");
            }
        } catch (SQLException ex) {
            // Handle the exception appropriately
            System.out.println("Error: " + ex.getMessage());
        }
        
        return admin;
    }

    @Override
    public Admin getUserById(int id) 
    {
        String query = UserQueries.GET_ADMIN_QUERY;
        Admin admin = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) 
                {
                    admin = new Admin();
                    admin.setId(resultSet.getInt("id_user"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setUserName(resultSet.getString("user_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPhone(resultSet.getString("phone"));
                    admin.setAddress(resultSet.getString("address"));
                    admin.setCreationDate(resultSet.getDate("creation_date"));
                    admin.setBirthDate(resultSet.getDate("birth_date"));
                    admin.setUpdateDate(resultSet.getDate("update_date"));
                    admin.setIsOnline(resultSet.getBoolean("is_online"));
                    admin.setStatus(EnumMapper.mapUserStatus(resultSet.getString("status")));
                    admin.setRole(EnumMapper.mapUserRole(resultSet.getString("role")));
                    admin.setLanguage(EnumMapper.mapUserLanguage(resultSet.getString("language")));
                    admin.setSexe(EnumMapper.mapUserSexe(resultSet.getString("sexe")));
                    admin.setProfilePicture(resultSet.getString("profile_picture"));
                    admin.setCountry(resultSet.getString("country"));
                }
            }
        } catch (SQLException ex) 
        {
            // Handle the exception appropriately            
            System.out.println("Error: " + ex.getMessage());
        }

        return admin;
    }

    @Override
    public Admin getUser(Admin admin) 
    {
        return getUserById(admin.getId());
    }

    @Override
    public List<Admin> getAllUsers() 
    {
        String query = UserQueries.GET_ADMIN_ALL_QUERY;
        List<Admin> allAdmins = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) 
        {
            while (resultSet.next())
            {
                Admin admin = new Admin();
                admin = new Admin();
                admin.setId(resultSet.getInt("id_user"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setUserName(resultSet.getString("user_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setAddress(resultSet.getString("address"));
                admin.setCreationDate(resultSet.getDate("creation_date"));
                admin.setBirthDate(resultSet.getDate("birth_date"));
                admin.setUpdateDate(resultSet.getDate("update_date"));
                admin.setIsOnline(resultSet.getBoolean("is_online"));
                admin.setStatus(EnumMapper.mapUserStatus(resultSet.getString("status")));
                admin.setRole(EnumMapper.mapUserRole(resultSet.getString("role")));
                admin.setLanguage(EnumMapper.mapUserLanguage(resultSet.getString("language")));
                admin.setSexe(EnumMapper.mapUserSexe(resultSet.getString("sexe")));
                admin.setProfilePicture(resultSet.getString("profile_picture"));
                allAdmins.add(admin);
            }
   
        } catch (SQLException ex) 
        {
            // Handle the exception appropriately            
            System.out.println("Error: " + ex.getMessage());
        }

        return allAdmins;
    }
    
}
