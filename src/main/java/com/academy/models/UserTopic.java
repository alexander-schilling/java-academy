package com.academy.models;

public class UserTopic {
    private int id;
    private int userCourseId;
    private int topicId;
    private boolean isCompleted;

    private UserCourse userCourse;
    private Topic topic;

    public UserTopic(int id, int userCourseId, int topicId, boolean isCompleted) {
        this.id = id;
        this.userCourseId = userCourseId;
        this.topicId = topicId;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserCourseId() { return userCourseId; }
    public void setUserCourseId(int userCourseId) { this.userCourseId = userCourseId; }

    public int getTopicId() { return topicId; }
    public void setTopicId(int topicId) { this.topicId = topicId; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { this.isCompleted = completed; }

    public UserCourse getUserCourse() { return userCourse; }
    public void setUserCourse(UserCourse userCourse) { this.userCourse = userCourse; }

    public Topic getTopic() { return topic; }
    public void setTopic(Topic topic) { this.topic = topic; }
}
