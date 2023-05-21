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
public class PrestataireService extends User {
    private String passportPicture;
    private String passportId;
    private String cinPicture;
    private String cinId;
    private String driverLicensePicture;
    private String driverLicenseId;

    public PrestataireService(String passportPicture, String passportId, String cinPicture, String cinId, String driverLicensePicture, String driverLicenseId, String firstName, String lastName, String userName, UserSexe sexe, String email, String phone, String address, String country, UserLanguage language, String profilePicture, Boolean isOnline, UserStatus status, Date creationDate, Date updateDate, Date birthDate, UserRole role) {
        super(firstName, lastName, userName, sexe, email, phone, address, country, language, profilePicture, isOnline, status, creationDate, updateDate, birthDate, role);
        this.passportPicture = passportPicture;
        this.passportId = passportId;
        this.cinPicture = cinPicture;
        this.cinId = cinId;
        this.driverLicensePicture = driverLicensePicture;
        this.driverLicenseId = driverLicenseId;
    }
    
    public String getPassportPicture() {
        return passportPicture;
    }

    public void setPassportPicture(String passportPicture) {
        this.passportPicture = passportPicture;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getCinPicture() {
        return cinPicture;
    }

    public void setCinPicture(String cinPicture) {
        this.cinPicture = cinPicture;
    }

    public String getCinId() {
        return cinId;
    }

    public void setCinId(String cinId) {
        this.cinId = cinId;
    }

    public String getDriverLicensePicture() {
        return driverLicensePicture;
    }

    public void setDriverLicensePicture(String driverLicensePicture) {
        this.driverLicensePicture = driverLicensePicture;
    }

    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public void setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }

    @Override
    public String toString() {
        return "PrestataireService{" + super.toString() + "passportPicture=" + passportPicture + ", passportId=" + passportId + ", cinPicture=" + cinPicture + ", cinId=" + cinId + ", driverLicensePicture=" + driverLicensePicture + ", driverLicenseId=" + driverLicenseId + '}';
    }
    
}
