package com.alltheducks.bbws.ws;

import blackboard.data.course.Course;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.platform.gradebook2.BaseGradingSchema;
import blackboard.platform.gradebook2.GradableItem;
import blackboard.platform.gradebook2.impl.GradableItemDAO;
import blackboard.util.GradeFormat;
import com.alltheducks.bbws.model.AssessmentItemDto;
import com.alltheducks.bbws.model.CourseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("courses")
public class CoursesResource {
    final Logger logger = LoggerFactory.getLogger(CoursesResource.class);
    public static final String CLICHED_MESSAGE = "Hello World!";

    @Inject
    private CourseDbLoader courseDbLoader;
    @Inject
    private GradableItemDAO gradableItemDAO;


    @GET
    @Produces("application/json")
    public List<CourseDto> listCourses() {
        List<CourseDto> courses = new ArrayList<CourseDto>();
        try {
            List<Course> bbCourses = courseDbLoader.loadAllCourses();
            courses = convertBbCoursesToCourseDtos(bbCourses);
        } catch (PersistenceException ex) {
            logger.error("Error while retrieving courses", ex);
            throw new WebApplicationException("Error retrieving Courses", 500);
        }

        return courses;
    }

    @GET
    @Path("/{courseId}")
    @Produces("application/json")
    public CourseDto getCourseInfo(@PathParam("courseId") String courseId) {
        CourseDto course;

        try {
            Course bbCourse = courseDbLoader.loadByCourseId(courseId);
            course = convertBbCourseToCourseDto(bbCourse);
        } catch (KeyNotFoundException ex) {
            logger.debug(String.format("No Course with CourseId {} found.", courseId));
            throw new WebApplicationException(String.format("No Course with CourseId %s found.", courseId), 404);
        } catch (PersistenceException ex) {
            logger.error("Error while retrieving courses", ex);
            throw new WebApplicationException("Error retrieving Courses", 500);
        }

        return course;
    }

    @GET
    @Path("/{courseId}/gradebook/assessments")
    @Produces("application/json")
    public List<AssessmentItemDto> getAssessmentItemsForCourse(@PathParam("courseId") String courseId) {
        List<AssessmentItemDto> assessmentItems = new ArrayList<>();
        try {
            Course bbCourse = courseDbLoader.loadByCourseId(courseId);

            List<GradableItem> gradableItems = gradableItemDAO.loadCourseGradebook(bbCourse.getId(), 0);

            assessmentItems = convertGradableItemsToAssessmentDtos(gradableItems);

        } catch (KeyNotFoundException ex) {
            logger.debug(String.format("No Course with CourseId {} found.", courseId));
            throw new WebApplicationException(String.format("No Course with CourseId %s found.", courseId), 404);
        } catch (PersistenceException ex) {
            logger.error("Error while retrieving courses", ex);
            throw new WebApplicationException("Error retrieving Courses", 500);
        }
        return assessmentItems;
    }

    private List<AssessmentItemDto> convertGradableItemsToAssessmentDtos(List<GradableItem> gradableItems) {
        List<AssessmentItemDto> assessmentItems = new ArrayList<>();

        for (GradableItem gradableItem : gradableItems) {
            AssessmentItemDto assessmentItem = new AssessmentItemDto();
            assessmentItem.setTitle(gradableItem.getTitle());
            assessmentItem.setPointsPossible(gradableItem.getPoints());
//            assessmentItem.setType();
//            gradableItem.getGradingSchema().getScaleType();
//            BaseGradingSchema.Type.

            assessmentItems.add(assessmentItem);
        }

        return assessmentItems;
    }


    private List<CourseDto> convertBbCoursesToCourseDtos(List<Course> bbCourses) {
        List<CourseDto> courses = new ArrayList<>();
        for (Course bbCourse : bbCourses) {
            courses.add(convertBbCourseToCourseDto(bbCourse));
        }
        return courses;
    }

    private CourseDto convertBbCourseToCourseDto(Course bbCourse) {
        CourseDto course = new CourseDto();
        course.setId(bbCourse.getId().getExternalString());
        course.setTitle(bbCourse.getTitle());
        course.setExternalId(bbCourse.getBatchUid());
        course.setCourseId(bbCourse.getCourseId());
        return course;
    }
}
