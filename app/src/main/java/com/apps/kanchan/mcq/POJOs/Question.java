package com.apps.kanchan.mcq.POJOs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14/12/18.
 */

public class Question {
    private String mQuestion;

    private ArrayList<String> mOptions;
    private int correctOption;
    private int mImageResourceId;
    private boolean isLocked;

    public Question(String question) {
        mQuestion = question;
        correctOption = -1;
        mImageResourceId = 0;
        isLocked = false;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int writeOption) {
        this.correctOption = writeOption;
    }

    public ArrayList<String> getOptions() {
        return mOptions;
    }

    public void setOptions(ArrayList<String> mOptions) {
        this.mOptions = mOptions;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }
}
