package com.apps.kanchan.mcq.POJOs;

/**
 * Created by root on 13/12/18.
 */

public class TextOption {
    private String mOption;
    public TextOption(char index,String option,boolean isChecked) {
        mOption = option;
    }


    public String getOption() {
        return mOption;
    }

    public void setOption(String mOption) {
        this.mOption = mOption;
    }
}
