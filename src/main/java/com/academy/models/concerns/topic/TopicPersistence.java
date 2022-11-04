package com.academy.models.concerns.topic;

import com.academy.models.Topic;
import com.academy.services.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This handles the persistence of the object
 * @author Alexander Schilling
 */
public class TopicPersistence {
    /**
     * Parses the database fields to a Topic object
     * @param rs Database ResultSet
     * @return Topic object
     * @throws SQLException Any SQL Error
     */
    public static Topic toObject(ResultSet rs) throws SQLException {
        return new Topic(
                rs.getInt("id"),
                rs.getInt("course_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("content")
        );
    }

    /**
     * Executes the update in the database
     * @param topic Topic object
     * @throws SQLException Any SQL Error
     */
    public static void updateTopic(Topic topic) throws SQLException {
        String sql = "UPDATE `topics` SET"
                + " `title` = ?,"
                + " `description` = ?,"
                + " `content` = ?,"
                + " WHERE `id` = ?;";

        try (PreparedStatement ps = DatabaseService.createPreparedStatement(sql)) {
            ps.setString(1, topic.getTitle());
            ps.setString(2, topic.getDescription());
            ps.setString(3, topic.getContent());
            ps.setInt(4, topic.getId());

            ps.executeUpdate();
        }
    }
}
