package com.example.skylink.persistence.Implementations.hsqldb;

import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.persistence.Interfaces.IUserDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserHSQLDB implements IUserDB {

    private final String dbPath;

    private final String CREATE_TABLE = "CREATE TABLE USER("
            + "id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
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

    public long addUser_Auth(IUserProperties user) {
        String sql = "INSERT INTO USER (full_name, email, password) VALUES (?, ?, ?)";

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

    public boolean update_user_info(long user_id, IUserProperties user){
        String sql = "UPDATE USER SET gender=?, address=?, phone=?, date_of_birth=?, country_of_origin=? WHERE user_id=?";

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
        String sql = "SELECT password FROM USER WHERE email=?";

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
    public IUserProperties getUserByEmail(String email) {
        String sql = "SELECT * FROM USER WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            System.out.println(rs);

            if (rs.next()) {
                String name = rs.getString("full_name");
                String password = rs.getString("password");
                return new UserProperties(name, email, password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public IUserDB initialize() {
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
