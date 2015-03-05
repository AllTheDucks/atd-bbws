package com.alltheducks.bbws.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wiley Fuller on 5/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class MarkDto {
    private String username;
    private String value;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
