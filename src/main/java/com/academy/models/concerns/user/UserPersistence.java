package com.academy.models.concerns.user;

import com.academy.models.User;
import com.academy.services.DatabaseService;
import org.mariadb.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This handles the persistence of the object
 * @author Alexander Schilling
 */
public class UserPersistence {
    /**
     * Parses the database fields to a User object
     * @param rs Database ResultSet
     * @return User object
     * @throws SQLException Any SQL Error
     */
    public static User toObject(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("token")
        );
    }

    /**
     * Inserts the user to the database, setting the generated id to
     * the object
     * @param user User object
     * @throws SQLException Any SQL Error
     */
    public static void createUser(User user) throws SQLException {
        String sql = "INSERT INTO `users` (`username`, `password`, `firstname`, `lastname`, `token`)"
                + " VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = DatabaseService.createPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getToken());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No rows were created!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Couldn't create user!");
            }
        }
    }

    /**
     * Executes the update in the database
     * @param user User object
     * @throws SQLException Any SQL Error
     */
    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE `users` SET"
                + " `username` = ?,"
                + " `password` = ?,"
                + " `firstname` = ?,"
                + " `lastname` = ?,"
                + " `token` = ?"
                + " WHERE `id` = ?;";

        try (PreparedStatement ps = DatabaseService.createPreparedStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getToken());
            ps.setInt(6, user.getId());

            ps.executeUpdate();
        }
    }
}
