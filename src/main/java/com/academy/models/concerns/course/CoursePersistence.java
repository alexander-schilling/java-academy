package com.academy.models.concerns.course;

import com.academy.models.Course;
import com.academy.services.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This handles the persistence of the object
 * @author Alexander Schilling
 */
public class CoursePersistence {
    /**
     * Parses the database fields to a Course object
     * @param rs Database ResultSet
     * @return Course object
     * @throws SQLException Any SQL Error
     */
    public static Course toObject(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("image_url")
        );
    }

    /**
     * Executes the update in the database
     * @param course Course object
     * @throws SQLException Any SQL Error
     */
    public static void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE `courses` SET"
                + " `title` = ?,"
                + " `description` = ?,"
                + " `image_url` = ?,"
                + " WHERE `id` = ?;";

        try (PreparedStatement ps = DatabaseService.createPreparedStatement(sql)) {
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getImageUrl());
            ps.setInt(4, course.getId());

            ps.executeUpdate();
        }
    }
}
