package com.alltheducks.bbws.ws;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import com.alltheducks.bbws.model.CourseDto;
import com.alltheducks.bbws.util.BbCourseHelper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Harry Scells on 5/05/2016.
 * Copyright All the Ducks Pty. Ltd.
 */
@Path("users")
public class UsersResource {

    @Inject
    private CourseDbLoader courseDbLoader;

    @GET
    @Path("{userId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseDto> getCoursesForUser(@PathParam("userId") String userId) throws PersistenceException {

        List<Course> courses = courseDbLoader.loadByUserId(Id.generateId(User.DATA_TYPE, userId));
        return  BbCourseHelper.convertBbCoursesToCourseDtos(courses);

    }

}
