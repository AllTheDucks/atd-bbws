package com.alltheducks.bbws.model;

/**
 * Created by Wiley Fuller on 5/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class MarkDto {
    private String externalUserKey;
    private String value;

    public String getExternalUserKey() {
        return externalUserKey;
    }

    public void setExternalUserKey(String externalUserKey) {
        this.externalUserKey = externalUserKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
