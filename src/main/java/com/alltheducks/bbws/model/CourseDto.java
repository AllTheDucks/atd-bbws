package com.alltheducks.bbws.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Wiley Fuller on 4/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class CourseDto {

    String id;
    String courseId;
    String externalId;
    String title;
    List<CourseDto> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseDto> getChildren() {
        return children;
    }

    public void setChildren(List<CourseDto> children) {
        this.children = children;
    }
}
