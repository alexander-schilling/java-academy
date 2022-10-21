package com.academy.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String identifier;
    private List<Topic> topics;

    public Course(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
        this.topics = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public List<Topic> getTopics() { return topics; }
    public void setTopics(List<Topic> topics) { this.topics = topics; }
    public void addTopic(Topic topic) { this.topics.add(topic); }
}