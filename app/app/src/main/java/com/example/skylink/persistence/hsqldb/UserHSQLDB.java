package com.example.skylink.persistence.hsqldb;

import com.example.skylink.objects.Flight;
import com.example.skylink.objects.UserProperties;
import com.example.skylink.persistence.IFlightDatabase;
import com.example.skylink.persistence.IUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserHSQLDB implements IUser {

    private final String dbPath;

    private final String CREATE_TABLE = "CREATE TABLE USER("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "full_name VARCHAR(100) NOT NULL,"
            + "email VARCHAR(100) NOT NULL UNIQUE,"
            + "password VARCHAR(100) NOT NULL,"
            + "gender VARCHAR(10),"
            + "address VARCHAR(255),"
            + "phone VARCHAR(20),"
            + "date_of_birth DATE,"
            + "country_of_origin VARCHAR(50)"
            + ")";

    public UserHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
    }

    public long addUser_Auth(UserProperties user) {
        String sql = "INSERT INTO user_table (full_name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean update_user_info(long user_id,UserProperties user){
        String sql = "UPDATE user_table SET gender=?, address=?, phone=?, date_of_birth=?, country_of_origin=? WHERE user_id=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getGender());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getDateOfBirth());
            ps.setString(5, user.getCountryOfOrigin());
            ps.setLong(6, user_id);

            int rowsAffected = ps.executeUpdate();

            // Check if the update was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String findPassword(String email) {
        String sql = "SELECT password FROM user_table WHERE email=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public UserHSQLDB initialize() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            this.drop();
            stmt.executeUpdate(CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public UserHSQLDB drop() {
        String sql = "DROP TABLE USER";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

}
