package edu.myinst.config;

import net.sourceforge.stripes.validation.Validate;

/**
 * Created by wiley on 19/11/14.
 */
public class Configuration {

    private String settingOne;
    private int settingTwo;

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
}
