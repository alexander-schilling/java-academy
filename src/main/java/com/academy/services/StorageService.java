package com.academy.services;

import com.academy.models.Course;
import com.academy.models.Topic;
import com.academy.models.User;
import com.academy.models.concerns.course.CoursePersistence;
import com.academy.models.concerns.topic.TopicPersistence;
import com.academy.models.concerns.user.UserPersistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles how everything is stored and serves the objects from the desired storage.
 * Currently, it obtains every entry in the database.
 * @author Alexander Schilling
 */
public class StorageService {
    /**
     * Gets the courses from database
     * @return Course list
     */
    public static List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();

        try (ResultSet rs = DatabaseService.fetch("SELECT * FROM `courses`")) {
            while (rs.next()) {
                courses.add(CoursePersistence.toObject(rs));
            }
        }

        return courses;
    }

    /**
     * Gets the topics from database
     * @return Topic list
     */
    public static List<Topic> getTopics() throws SQLException {
        List<Topic> topics = new ArrayList<>();

        try (ResultSet rs = DatabaseService.fetch("SELECT * FROM `topics`")) {
            while (rs.next()) {
                topics.add(TopicPersistence.toObject(rs));
            }
        }

        return topics;
    }

    /**
     * Gets the users from database
     * @return User list
     */
    public static List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (ResultSet rs = DatabaseService.fetch("SELECT * FROM `users`")) {
            while (rs.next()) {
                users.add(UserPersistence.toObject(rs));
            }
        }

        return users;
    }
}
