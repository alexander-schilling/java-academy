package com.academy.models.concerns;

import com.academy.models.Course;
import com.academy.models.Topic;
import org.json.JSONArray;
import org.json.JSONObject;

public class CourseBuilder {
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
