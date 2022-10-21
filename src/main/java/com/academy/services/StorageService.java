package com.academy.services;

import com.academy.models.Course;
import com.academy.models.Topic;
import com.academy.models.User;
import com.academy.utils.RandomNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles how everything is stored and serves the objects from the desired storage.
 * Currently, it generates an established amount of courses and a random amount of
 * topics for each course.
 * @author Alexander Schilling
 */
public class StorageService {
    // Number of courses to be created
    private static final int NUMBER_OF_COURSES = 10;
    // Range of topics to be created for each course
    private static final int[] RANGE_OF_TOPICS = { 5, 15 };

    /**
     * Gets the courses, currently an established amount of objects with a simple identifier
     * @return Course list
     */
    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_COURSES; i++) {
            courses.add(new Course(i, String.format("course_%03d", i)));
        }

        return courses;
    }

    /**
     * Gets the topics, currently a random amount of objects for each course with sample data
     * @return Topic list
     */
    public static List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<>();

        for (int courseId = 1; courseId <= NUMBER_OF_COURSES; courseId++) {
            int numberOfTopics = RandomNumber.getRandomNumber(RANGE_OF_TOPICS[0], RANGE_OF_TOPICS[1]);

            for (int j = 1; j <= numberOfTopics; j++) {
                int topicId = topics.size() + 1;
                String title = String.format("Topic number %02d", j);
                String description = "This a simple description";
                String content = "This is the topic content and should be much longer";
                topics.add(new Topic(topicId, courseId, title, description, content));
            }
        }

        return topics;
    }

    /**
     * Gets the users, currently hard coded users with sample data
     * @return User list
     */
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1, "acl", "acl", "ACL", "Academy"));
        users.add(new User(2, "alexander", "hola123", "Alexander", "Schilling"));

        return users;
    }
}
