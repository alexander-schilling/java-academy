package com.academy.controllers;

import com.academy.models.Course;
import com.academy.models.Topic;
import com.academy.services.StorageService;

import java.sql.SQLException;
import java.util.List;

/**
 * Controls app topics and routes logic
 * @author Alexander Schilling
 */
public class TopicsController {
    private static List<Topic> topics;

    /**
     * Gets topics froms storage and links each object to its relation
     */
    public static void setupTopics() throws SQLException {
        topics = StorageService.getTopics();

        linkTopicCourses();
    }

    /**
     * For each topic, set its Course object and add it to the TopicCourse topics list
     */
    private static void linkTopicCourses() {
        for (Topic topic : topics) {
            Course topicCourse = CoursesController.getCourseFromId(topic.getCourseId());

            if (topicCourse == null) return;

            topic.setCourse(topicCourse);
            topicCourse.addTopic(topic);
        }
    }

    public static List<Topic> getTopics() { return topics; }

    /**
     * @param id Topic id
     * @return Topic object or null
     */
    public static Topic getTopicFromId(int id) {
        for (Topic topic : topics) {
            if (topic.getId() == id) {
                return topic;
            }
        }

        return null;
    }
}
