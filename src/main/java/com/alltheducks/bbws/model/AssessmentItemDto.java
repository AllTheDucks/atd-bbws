package com.alltheducks.bbws.model;

/**
 * Created by Wiley Fuller on 4/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class AssessmentItemDto {
    private String id;
    private String name;
    private String internalName;
    private ValueType valueType;
    private double pointsPossible;
    private boolean userCreatedAssessment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(double pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    public boolean isUserCreatedAssessment() {
        return userCreatedAssessment;
    }

    public void setUserCreatedAssessment(boolean userCreatedAssessment) {
        this.userCreatedAssessment = userCreatedAssessment;
    }

    public enum ValueType {
        TEXT,
        NUMBER,
        PERCENT,
        DATE
    }
}

