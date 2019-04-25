package com.apps.kanchan.mcq.POJOs;

/**
 * Created by root on 1/2/19.
 */

public class StudyCard {
    private int mVersion;
    private String mExamName;
    private String mStreamName;
    private String mChapterName;
    private String mQuestion;
    private String mAnswer;
    private int mIsRead;
    private int mCardNumber;
    private float mAcquaintance;

    public StudyCard() {
        mIsRead = 0;
        mAcquaintance = 0;
        mCardNumber = -1;
    }

    public StudyCard(int version,String exam, String stream, String chapterName,String question,String answer) {
        this();
        mChapterName = chapterName;
        mQuestion = question;
        mAnswer = answer;
        mVersion = version;
        mExamName = exam;
        mStreamName = stream;
    }

    public String getChapterName() {
        return mChapterName;
    }

    public void setChapterName(String mChapterName) {
        this.mChapterName = mChapterName;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public int isRead() {
        return mIsRead;
    }

    public void setIsRead(int i) {
        mIsRead = i;
    }

    public int getCardNumber() {
        return mCardNumber;
    }

    public void setCardNumber(int mCardNumber) {
        this.mCardNumber = mCardNumber;
    }

    public float getAquaintance() {
        return mAcquaintance;
    }

    public void setAquaintance(float mAquaintance) {
        this.mAcquaintance = mAquaintance;
    }

    public String getExamName() {
        return mExamName;
    }

    public void setExamName(String mExamName) {
        this.mExamName = mExamName;
    }

    public String getmStreamName() {
        return mStreamName;
    }

    public void setmStreamName(String mStreamName) {
        this.mStreamName = mStreamName;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int mVersion) {
        this.mVersion = mVersion;
    }
}
