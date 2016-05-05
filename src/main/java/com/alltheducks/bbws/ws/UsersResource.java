package com.alltheducks.bbws.ws;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.security.Entitlement;
import blackboard.platform.security.SecurityUtil;
import com.alltheducks.bbws.model.CourseDto;
import com.alltheducks.bbws.util.BbCourseHelper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry Scells on 5/05/2016.
 * Copyright All the Ducks Pty. Ltd.
 */
@Path("users")
public class UsersResource {

    @Inject
    private CourseDbLoader courseDbLoader;
    @Inject
    private UserDbLoader userDbLoader;

    @GET
    @Path("{username}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseDto> getCoursesForUser(@PathParam("username") String username, @QueryParam("entitlement") @DefaultValue("") String entitlement) throws PersistenceException {

        User bbUser = userDbLoader.loadByUserName(username);
        Id bbUserId = bbUser.getId();

        List<Course> courses = courseDbLoader.loadByUserId(bbUserId);

        if (entitlement.isEmpty()) {
            // no entitlement specified, return all the courses
            return BbCourseHelper.convertBbCoursesToCourseDtos(courses);
        } else {
            // otherwise, filter courses based on the users entitlement
            Entitlement bbEntitlement = new Entitlement(entitlement);
            List<Course> filteredCourses = new ArrayList<>();
            for (Course course : courses) {
                if (SecurityUtil.userHasEntitlement(bbUserId, course.getId(), bbEntitlement)) {
                    filteredCourses.add(course);
                }
            }
            return BbCourseHelper.convertBbCoursesToCourseDtos(filteredCourses);
        }
    }

}
