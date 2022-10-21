package com.academy.models;

import java.util.ArrayList;
import java.util.List;

public class UserCourse {
    private int id;
    private int userId;
    private int courseId;
    private Course course;
    private List<UserTopic> userTopics;

    public UserCourse(int id, int userId, int courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.userTopics = new ArrayList<>();
    }

    public float getProgress() {
        int numberOfTopics = userTopics.size();
        float numberOfCompletedTopics = userTopics.stream()
                .filter(UserTopic::isCompleted)
                .count();
        return numberOfCompletedTopics / numberOfTopics;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<UserTopic> getUserTopics() { return userTopics; }
    public void setUserTopics(List<UserTopic> userTopics) { this.userTopics = userTopics; }
}
