package com.apps.kanchan.mcq.POJOs;

import com.apps.kanchan.mcq.POJOs.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 3/1/19.
 */

public class Test {
    private List<Question> mQuestions;
    private boolean mIsLocked;
    public Test(List<Question> questions) {
        mQuestions = questions;
        mIsLocked = false;
    }

    public boolean isIsLocked() {
        return mIsLocked;
    }

    public void setIsLocked(boolean mIsLocked) {
        this.mIsLocked = mIsLocked;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(ArrayList<Question> mQuestions) {
        this.mQuestions = mQuestions;
    }

    public int getTotalNumberQuestions() {
        if(mQuestions != null) {
            return mQuestions.size();
        }
        return 0;
    }
}
