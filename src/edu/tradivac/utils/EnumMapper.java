package edu.tradivac.utils;

import edu.tradivac.entities.enums.UserLanguage;
import edu.tradivac.entities.enums.UserRole;
import edu.tradivac.entities.enums.UserSexe;
import edu.tradivac.entities.enums.UserStatus;

public final class EnumMapper {
    public static UserStatus mapUserStatus(String status) {
        switch (status) {
            case "active":
                return UserStatus.ACTIVE;
            case "inactive":
                return UserStatus.INACTIVE;
            case "pending":
                return UserStatus.PENDING;
            case "banned":
                return UserStatus.BANNED;
            default:
                throw new IllegalArgumentException("Invalid user status: " + status);
        }
    }
    public static String mapUserStatus(UserStatus status)
    {
        switch (status)
        {
            case ACTIVE:
                return "active";
            case INACTIVE:
                return "inactive";
            case PENDING:
                return "pending";
            case BANNED:
                return "banned";
            default:
                throw new IllegalArgumentException("Invalid user status: " + status);
        }
    }

    public static UserSexe mapUserSexe(String sexe) 
    {
        switch (sexe) 
        {
            case "male":
                return UserSexe.MALE;
            case "female":
                return UserSexe.FEMALE;
            default:
                throw new IllegalArgumentException("Invalid user sexe: " + sexe);
        }
    }

    public static String mapUserSexe(UserSexe sexe)
    {
        switch (sexe)
        {
            case MALE:
                return "male";
            case FEMALE:
                return "female";
            default:
                throw new IllegalArgumentException("Invalid user sexe: " + sexe);
        }
    }

    public static UserRole mapUserRole(String role) 
    {
        switch (role) 
        {
            case "admin":
                return UserRole.ADMIN;
            case "prestataire_service":
                return UserRole.PRESTATAIRE_SERVICE;
            case "touriste":
                return UserRole.TOURISTE;
            default:
                throw new IllegalArgumentException("Invalid user role: " + role);
        }
    }

    public static String mapUserRole(UserRole role) 
    {
        switch (role) 
        {
            case ADMIN:
                return "admin";
            case PRESTATAIRE_SERVICE:
                return "prestataire_service";
            case TOURISTE:
                return "touriste";
            default:
                throw new IllegalArgumentException("Invalid user role: " + role);
        }
    }

    public static UserLanguage mapUserLanguage(String language) 
    {
        switch (language) 
        {
            case "english":
                return UserLanguage.ENGLISH;
            case "french":
                return UserLanguage.FRANCAIS;
            default:
                throw new IllegalArgumentException("Invalid user language: " + language);
        }
    }

    public static String mapUserLanguage(UserLanguage language) 
    {
        switch (language) 
        {
            case ENGLISH:
                return "english";
            case FRANCAIS:
                return "french";
            default:
                throw new IllegalArgumentException("Invalid user language: " + language);
        }
    }

}