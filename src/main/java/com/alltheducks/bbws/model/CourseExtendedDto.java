package com.alltheducks.bbws.model;

/**
 * Created by hanleybrand on 8/19/16.
 */

public class CourseExtendedDto extends CourseDto {


    String rowStatus;
    Boolean isAvailable;


    public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailableInd(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


}
