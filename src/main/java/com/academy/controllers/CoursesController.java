package com.academy.controllers;

import com.academy.models.Course;
import com.academy.models.concerns.CourseBuilder;
import com.academy.services.StorageService;
import io.javalin.http.Context;
import org.json.JSONArray;

import java.util.List;

public class CoursesController {
    private static List<Course> courses;

    public static void setupCourses() {
        courses = StorageService.getCourses();
    }

    public static List<Course> getCourses() { return courses; }

    public static Course getCourseFromId(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    public static void Index(Context context) {
        JSONArray coursesJSON = new JSONArray();

        for (Course course : courses) {
            coursesJSON.put(CourseBuilder.toJSONObject(course));
        }

        context.status(200).result(coursesJSON.toString());
    }
}
