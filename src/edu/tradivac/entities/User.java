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
 * @author wassou
 */
public class User {
    
    private Integer id;
    private Boolean isOnline;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String country;
    private String profilePicture;
    private String passportPicture;
    private String passportId;
    private String cinPicture;
    private String cinId;
    private String driverLicensePicture;
    private String driverLicenseId;
    private Date creationDate;
    private Date updateDate;
    private Date birthDate;
    private UserStatus status;
    private UserRole role;
    private UserLanguage language;
    private UserSexe sexe;
    
}
