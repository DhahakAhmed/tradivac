/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entities;

import edu.tradivac.entities.enums.UserLanguage;
import edu.tradivac.entities.enums.UserRole;
import edu.tradivac.entities.enums.UserSexe;
import edu.tradivac.entities.enums.UserStatus;
import java.sql.Date;

/**
 *
 * @author devhk
 */
public class Admin extends User {
    
    public Admin(String firstName, String lastName, String userName, UserSexe sexe, String email, String phone, String address, String country, UserLanguage language, String profilePicture, Boolean isOnline, UserStatus status, Date creationDate, Date updateDate, Date birthDate, UserRole role) {
        super(firstName, lastName, userName, sexe, email, phone, address, country, language, profilePicture, isOnline, status, creationDate, updateDate, birthDate, role);
    }
    
}

