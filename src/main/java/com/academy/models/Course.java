package com.academy.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Course, it has an identifier, and it has a list of topics
 * @author Alexander Schilling
 */
public class Course {
    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private List<Topic> topics;

    public Course(int id, String title, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.topics = new ArrayList<>();
    }

    // START: Getters & Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public List<Topic> getTopics() { return topics; }
    public void setTopics(List<Topic> topics) { this.topics = topics; }
    public void addTopic(Topic topic) { this.topics.add(topic); }

    // END: Getters & Setters
}
