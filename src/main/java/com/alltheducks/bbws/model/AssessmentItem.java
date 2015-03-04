package com.alltheducks.bbws.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wiley Fuller on 4/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class AssessmentItem {
    private String title;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
