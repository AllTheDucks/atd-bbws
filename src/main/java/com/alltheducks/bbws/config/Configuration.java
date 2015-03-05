package com.alltheducks.bbws.config;

import net.sourceforge.stripes.validation.Validate;

/**
 * Created by wiley on 19/11/14.
 */
public class Configuration {

    private String settingOne;
    private int settingTwo;

    private String username;
    private String password;

    public String getSettingOne() {
        return settingOne;
    }

    public void setSettingOne(String settingOne) {
        this.settingOne = settingOne;
    }

    public int getSettingTwo() {
        return settingTwo;
    }

    public void setSettingTwo(int settingTwo) {
        this.settingTwo = settingTwo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
