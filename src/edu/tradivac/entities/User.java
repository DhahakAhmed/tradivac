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
import java.util.Objects;

/**
 *
 * @author wassou
 */
public class User {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private UserSexe sexe;
    private String email;
    private String phone;
    private String address;
    private String country;
    private UserLanguage language;
    private String profilePicture;    
    private Boolean isOnline;
    private UserStatus status;
    private Date creationDate;
    private Date updateDate;
    private Date birthDate;
    private UserRole role;

    public User(String firstName, String lastName, String userName, UserSexe sexe, String email, String phone, String address, String country, UserLanguage language, String profilePicture, Boolean isOnline, UserStatus status, Date creationDate, Date updateDate, Date birthDate, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.sexe = sexe;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.language = language;
        this.profilePicture = profilePicture;
        this.isOnline = isOnline;
        this.status = status;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.birthDate = birthDate;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserSexe getSexe() {
        return sexe;
    }

    public void setSexe(UserSexe sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserLanguage getLanguage() {
        return language;
    }

    public void setLanguage(UserLanguage language) {
        this.language = language;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    
    @Override
    public String toString() {
        return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", sexe=" + sexe + ", email=" + email + ", phone=" + phone + ", address=" + address + ", country=" + country + ", language=" + language + ", profilePicture=" + profilePicture + ", isOnline=" + isOnline + ", status=" + status + ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", birthDate=" + birthDate + ", role=" + role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
