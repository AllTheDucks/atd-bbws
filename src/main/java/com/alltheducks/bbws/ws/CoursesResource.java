package com.alltheducks.bbws.ws;

import blackboard.data.course.Course;
import blackboard.data.course.CourseCourse;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseCourseDbLoader;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.gradebook2.*;
import blackboard.platform.gradebook2.impl.*;
import com.alltheducks.bbws.model.AssessmentItemDto;
import com.alltheducks.bbws.model.CourseDto;
import com.alltheducks.bbws.model.MarkDto;
import com.alltheducks.bbws.security.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("courses")
@RequiresAuthentication
public class CoursesResource {
    final Logger logger = LoggerFactory.getLogger(CoursesResource.class);
    public static final String CLICHED_MESSAGE = "Hello World!";

    @Inject
    private CourseDbLoader courseDbLoader;

    @Inject
    private GradableItemDAO gradableItemDAO;
    @Inject
    private GradingSchemaDAO gradingSchemaDAO;
    @Inject
    private UserDbLoader userDbLoader;
    @Inject
    private GradeDetailDAO gradeDetailDAO;

    @Inject
    private CourseCourseDbLoader courseCourseDbLoader;


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
            List<CourseCourse> children = courseCourseDbLoader.loadByParentId(bbCourse.getId());
            course = convertBbCourseToCourseDto(bbCourse);
            if (children != null && !children.isEmpty()) {
                course.setChildren(new ArrayList<CourseDto>());
                for (CourseCourse childCc : children) {
                    Course childBbCourse = courseDbLoader.loadById(childCc.getChildCourseId());
                    CourseDto childCourse = convertBbCourseToCourseDto(childBbCourse);
                    course.getChildren().add(childCourse);
                }
            }

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


    @GET
    @Path("/{courseId}/gradebook/assessments/{assessmentId}")
    @Produces("application/json")
    public AssessmentItemDto getAssessmentItemForCourse(
            @PathParam("courseId") String courseId,
            @PathParam("assessmentId") String assessmentIdStr) {

        AssessmentItemDto assessmentItem;
        try {
            Course bbCourse = courseDbLoader.loadByCourseId(courseId);
            Id assessmentId = Id.generateId(GradableItem.DATA_TYPE, assessmentIdStr);

            //TODO Check that the gradableItem actually belongs to this course.
            GradableItem gradableItem = gradableItemDAO.loadById(assessmentId);

            assessmentItem = convertGradableItemToAssessmentDto(gradableItem);

        } catch (KeyNotFoundException ex) {
            logger.debug(String.format("No Course with CourseId {} found.", courseId));
            throw new WebApplicationException(String.format("No Course with CourseId %s found.", courseId), 404);
        } catch (PersistenceException ex) {
            logger.error("Error while retrieving courses", ex);
            throw new WebApplicationException("Error retrieving Courses", 500);
        }
        return assessmentItem;
    }

    @GET
    @Path("/{courseId}/gradebook/assessments/{assessmentId}/marks")
    @Produces("application/json")
    public List<MarkDto> getMarksForAssessmentItem(
            @PathParam("courseId") String courseId,
            @PathParam("assessmentId") String assessmentIdStr) {

        List<MarkDto> marks = new ArrayList<>();
        try {
            Course bbCourse = courseDbLoader.loadByCourseId(courseId);
            Id assessmentId = Id.generateId(GradableItem.DATA_TYPE, assessmentIdStr);

            //TODO Check that the gradableItem actually belongs to this course.
            GradableItem gradableItem = gradableItemDAO.loadById(assessmentId);
            if (!courseId.equals(gradableItem.getCourseId())) {
                throw new WebApplicationException("Invalid IDs in request.", 400);
            }



            List<GradeDetail> grades = gradeDetailDAO.getGradeDetails(gradableItem.getId());
            for (GradeDetail grade : grades) {
                //TODO This is probably not very efficient. Fix it.
                User user = userDbLoader.loadUserByCourseMembership(grade.getCourseUserId());
                MarkDto mark = new MarkDto();
                mark.setValue(grade.getGrade(gradableItem.getAggregationModel()));
                mark.setUsername(user.getUserName());
                marks.add(mark);
            }



        } catch (KeyNotFoundException ex) {
            logger.debug(String.format("No Course with CourseId {} found.", courseId));
            throw new WebApplicationException(String.format("No Course with CourseId %s found.", courseId), 404);
        } catch (PersistenceException ex) {
            logger.error("Error while retrieving courses", ex);
            throw new WebApplicationException("Error retrieving Courses", 500);
        }
        return marks;
    }

    private List<AssessmentItemDto> convertGradableItemsToAssessmentDtos(List<GradableItem> gradableItems) throws PersistenceException {
        List<AssessmentItemDto> assessmentItems = new ArrayList<>();

        for (GradableItem gradableItem : gradableItems) {
            AssessmentItemDto assessmentItem = convertGradableItemToAssessmentDto(gradableItem);
            assessmentItems.add(assessmentItem);
        }

        return assessmentItems;
    }

    private AssessmentItemDto convertGradableItemToAssessmentDto(GradableItem item) throws PersistenceException {
        AssessmentItemDto assessmentItem = new AssessmentItemDto();
        assessmentItem.setId(item.getId().getExternalString());
        assessmentItem.setName(item.getDisplayTitle());
        assessmentItem.setInternalName(item.getTitle());
        assessmentItem.setPointsPossible(item.getPoints());
        assessmentItem.setUserCreatedAssessment(item.isUserCreatedColumn());

        GradingSchema schema = gradingSchemaDAO.loadById(item.getGradingSchemaId());
        GradingSchema.Type bbType = schema.getScaleType();

        logger.debug("Gradable item type is: {}", bbType);
        if (bbType == BaseGradingSchema.Type.SCORE) {
            assessmentItem.setValueType(AssessmentItemDto.ValueType.NUMBER);
        } else if (bbType == BaseGradingSchema.Type.PERCENT) {
            assessmentItem.setValueType(AssessmentItemDto.ValueType.PERCENT);
        } else if (bbType == BaseGradingSchema.Type.TEXT) {
            assessmentItem.setValueType(AssessmentItemDto.ValueType.TEXT);
        }
        return assessmentItem;
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
