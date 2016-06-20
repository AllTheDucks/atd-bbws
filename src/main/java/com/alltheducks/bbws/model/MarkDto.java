package com.alltheducks.bbws.model;

/**
 * Created by Wiley Fuller on 5/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class MarkDto {
    private String externalUserKey;
    private String text;
    private Double score;

    public String getExternalUserKey() {
        return externalUserKey;
    }

    public void setExternalUserKey(String externalUserKey) {
        this.externalUserKey = externalUserKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
