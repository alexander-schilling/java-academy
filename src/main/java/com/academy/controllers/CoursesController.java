package com.academy.controllers;

import com.academy.models.Course;
import com.academy.models.concerns.course.CourseBuilder;
import com.academy.services.StorageService;
import io.javalin.http.Context;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

/**
 * Controls app courses and routes logic
 * @author Alexander Schilling
 */
public class CoursesController {
    private static List<Course> courses;

    public static void setupCourses() throws SQLException {
        courses = StorageService.getCourses();
    }

    public static List<Course> getCourses() { return courses; }

    /**
     * @param id Course id
     * @return Course found or null
     */
    public static Course getCourseFromId(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    /**
     * Answers the /courses route with a JSON Array with each course
     * @param context Javalin http context
     */
    public static void Index(Context context) {
        JSONArray coursesJSON = new JSONArray();

        for (Course course : courses) {
            coursesJSON.put(CourseBuilder.toJSONObject(course));
        }

        context.status(200).result(coursesJSON.toString());
    }

    /**
     * Answers the /courses/{id} route with a JSON Object for the course
     * @param context Javalin http context
     */
    public static void Show(Context context) {
        int courseId;
        try {
            courseId = Integer.parseInt(context.pathParam("id"));
        } catch (NumberFormatException e) {
            context.status(400);
            return;
        }

        for (Course course : courses) {
            if (course.getId() == courseId) {
                context.status(200).result(CourseBuilder.toJSONObject(course).toString());
                return;
            }
        }

        context.status(404);
    }
}
