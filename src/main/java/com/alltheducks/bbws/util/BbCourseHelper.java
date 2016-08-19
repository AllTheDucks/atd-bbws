package com.alltheducks.bbws.util;

import blackboard.admin.data.IAdminObject;
import blackboard.admin.data.course.CourseSite;
import blackboard.admin.persist.course.CourseSiteLoader;
import blackboard.admin.persist.course.impl.CourseSiteDbLoader;
import blackboard.data.course.Course;
import blackboard.persist.PersistenceException;
import com.alltheducks.bbws.model.CourseDto;
import com.alltheducks.bbws.model.CourseExtendedDto;

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

    public static List<CourseExtendedDto> convertBbCoursesToCourseExtendedDtos(List<Course> bbCourses) throws PersistenceException {
        List<CourseExtendedDto> courses = new ArrayList<>();
        for (Course bbCourse : bbCourses) {
            courses.add(convertBbCourseToCourseExtendedDto(bbCourse));
        }
        return courses;
    }

    public static CourseExtendedDto convertBbCourseToCourseExtendedDto(Course bbCourse) throws PersistenceException {
        CourseExtendedDto course = new CourseExtendedDto();
        CourseSiteLoader courseLoader = CourseSiteDbLoader.Default.getInstance();
        CourseSite cs = courseLoader.load(bbCourse.getBatchUid());
        IAdminObject.RowStatus status = cs.getRowStatus();
        course.setId(bbCourse.getId().getExternalString());
        course.setTitle(bbCourse.getTitle());
        course.setExternalId(bbCourse.getBatchUid());
        course.setCourseId(bbCourse.getCourseId());
        course.setAvailableInd(bbCourse.getIsAvailable());

        if (status.equals(IAdminObject.RowStatus.ENABLED)){                 course.setRowStatus("0"); }
            else if (status.equals(IAdminObject.RowStatus.SOFT_DELETE)){    course.setRowStatus("1"); }
            else if (status.equals(IAdminObject.RowStatus.DISABLED)){       course.setRowStatus("2"); }
            else if (status.equals(IAdminObject.RowStatus.DELETE_PENDING)){ course.setRowStatus("3"); }
            else if (status.equals(IAdminObject.RowStatus.COPY_PENDING)){   course.setRowStatus("4"); }
            /// this should probably never happen, but if it does:
            else {                                                          course.setRowStatus("U");}

        return course;
    }

}
