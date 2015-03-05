package com.alltheducks.bbws.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wiley Fuller on 4/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class AssessmentItemDto {
    private String id;
    private String title;
    private Type type;
    private double pointsPossible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(double pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    public enum Type {
        TEXT,
        NUMBER,
        PERCENT,
        DATE
    }
}

