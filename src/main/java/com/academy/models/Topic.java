package com.academy.models;

public class Topic {
    private int id;
    private int courseId;
    private String title;
    private String description;
    private String content;
    private Course course;

    public Topic(int id, int courseId, String title, String description, String content) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    /**
     * Getters & Setters
     */

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
