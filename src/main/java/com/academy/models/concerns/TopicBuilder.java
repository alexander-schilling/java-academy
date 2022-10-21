package com.academy.models.concerns;

import com.academy.models.Topic;
import org.json.JSONObject;

/**
 * This handles different types of responses for the Topic object
 * @author Alexander Schilling
 */
public class TopicBuilder {
    /**
     * @param topic Topic object
     * @return JSONObject with id, courseId, title, description and content
     */
    public static JSONObject toJSONObject(Topic topic) {
        JSONObject json = new JSONObject();

        json.put("id", topic.getId());
        json.put("courseId", topic.getCourseId());
        json.put("title", topic.getTitle());
        json.put("description", topic.getDescription());
        json.put("content", topic.getContent());

        return json;
    }
}
