package edu.tradivac.services.constants;

public final class UserQueries {
    
    private UserQueries() {}
    public static String ADD_ADMIN_QUERY = "INSERT INTO user (first_name, last_name, user_name, email, phone, address, creation_date, " +
            "birth_date, update_date, is_online, status, role, language, sexe, profile_picture, country)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public static String DEL_ADMIN_QUERY = "DELETE FROM user WHERE id_user = ?";
    public static String UPDATE_ADMIN_QUERY = "UPDATE user SET first_name = ?, last_name = ?, user_name = ?, email = ?, phone = ?, " +
            "address = ?, update_date = ?, status = ?, role = ?, language = ?, sexe = ?, profile_picture = ?, " +
            "country = ?, is_online = ? WHERE id_user = ?";
    
    public static String GET_ADMIN_QUERY = "SELECT * FROM user WHERE id_user = ?";
    public static String GET_ADMIN_ALL_QUERY = "SELECT * FROM user";
}
