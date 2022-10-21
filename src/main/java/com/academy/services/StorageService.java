package com.academy.services;

import com.academy.models.Course;
import com.academy.models.Topic;
import com.academy.models.User;
import com.academy.utils.RandomNumber;

import java.util.ArrayList;
import java.util.List;

public class StorageService {
    private static final int NUMBER_OF_COURSES = 10;
    private static final int[] RANGE_OF_TOPICS = { 5, 15 };

    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_COURSES; i++) {
            courses.add(new Course(i, String.format("course_%03d", i)));
        }

        return courses;
    }

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

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1, "acl", "acl", "ACL", "Academy"));
        users.add(new User(2, "alexander", "hola123", "Alexander", "Schilling"));

        return users;
    }
}
