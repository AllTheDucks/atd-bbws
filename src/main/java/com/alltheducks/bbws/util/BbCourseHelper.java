package com.alltheducks.bbws.util;

import blackboard.data.course.Course;
import com.alltheducks.bbws.model.CourseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry Scells on 5/05/2016.
 * Copyright All the Ducks Pty. Ltd.
 */
public class BbCourseHelper {

    public static List<CourseDto> convertBbCoursesToCourseDtos(List<Course> bbCourses) {
        List<CourseDto> courses = new ArrayList<>();
        for (Course bbCourse : bbCourses) {
            courses.add(convertBbCourseToCourseDto(bbCourse));
        }
        return courses;
    }

    public static CourseDto convertBbCourseToCourseDto(Course bbCourse) {
        CourseDto course = new CourseDto();
        course.setId(bbCourse.getId().getExternalString());
        course.setTitle(bbCourse.getTitle());
        course.setExternalId(bbCourse.getBatchUid());
        course.setCourseId(bbCourse.getCourseId());
        return course;
    }

}
