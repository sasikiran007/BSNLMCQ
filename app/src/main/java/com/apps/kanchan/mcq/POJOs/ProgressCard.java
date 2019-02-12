package com.apps.kanchan.mcq.POJOs;

/**
 * Created by root on 5/2/19.
 */

public class ProgressCard {
    private String mName;
    private int mTotalCount;
    private int mCompletedCount;

    public ProgressCard(String name,int totalCount,int completedCount) {
        mName = name;
        mTotalCount = totalCount;
        mCompletedCount = completedCount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(int mTotalCount) {
        this.mTotalCount = mTotalCount;
    }

    public int getCompletedCount() {
        return mCompletedCount;
    }

    public void setCompletedCout(int mCompletedCout) {
        this.mCompletedCount = mCompletedCout;
    }
}
