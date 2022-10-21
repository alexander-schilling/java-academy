package com.academy.models.concerns;

import com.academy.models.Course;
import com.academy.models.Topic;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This handles different types of responses for the Course object
 * @author Alexander Schilling
 */
public class CourseBuilder {
    /**
     * @param course Course object
     * @return JSONObject with id, identifier, topics array (each as a JSONObject)
     */
    public static JSONObject toJSONObject(Course course) {
        JSONObject json = new JSONObject();

        json.put("id", course.getId());
        json.put("identifier", course.getIdentifier());

        JSONArray topicsJSONArray = new JSONArray();
        for (Topic topic : course.getTopics()) {
            topicsJSONArray.put(TopicBuilder.toJSONObject(topic));
        }

        json.put("topics", topicsJSONArray);

        return json;
    }
}
