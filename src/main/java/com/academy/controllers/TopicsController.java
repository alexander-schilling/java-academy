package com.academy.controllers;

import com.academy.models.Course;
import com.academy.models.Topic;
import com.academy.services.StorageService;

import java.util.List;

public class TopicsController {
    private static List<Topic> topics;

    public static void setupTopics() {
        topics = StorageService.getTopics();

        linkTopicCourses();
    }

    private static void linkTopicCourses() {
        for (Topic topic : topics) {
            Course topicCourse = CoursesController.getCourseFromId(topic.getCourseId());

            if (topicCourse == null) return;

            topic.setCourse(topicCourse);
            topicCourse.addTopic(topic);
        }
    }

    public static List<Topic> getTopics() { return topics; }

    public static Topic getTopicFromId(int id) {
        for (Topic topic : topics) {
            if (topic.getId() == id) {
                return topic;
            }
        }

        return null;
    }
}
