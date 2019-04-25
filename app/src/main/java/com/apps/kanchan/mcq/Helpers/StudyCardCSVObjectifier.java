package com.apps.kanchan.mcq.Helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.apps.kanchan.mcq.POJOs.StudyCard;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 28/1/19.
 */

public class StudyCardCSVObjectifier {
    private AssetManager mAssetManager;
    //private InputStream inputStream;
    private List<StudyCard> mStudyCards;
    private CSVReader mCsvReader;
    private int mVersion;
    private String mExam;
    private String mStream;

    public StudyCardCSVObjectifier(Context context,String source) throws IOException {
        mAssetManager = context.getAssets();
        mStudyCards = new ArrayList<>();
        String[] tokens;
        int count = 1;
        try {
            //inputStream = mAssetManager.open(source);
            mCsvReader = new CSVReader(new InputStreamReader(mAssetManager.open(source), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tokens = mCsvReader.readNext();
        if(tokens.length == 3) {
            mVersion = Integer.parseInt(tokens[0]);
            mExam = tokens[1];
            mStream = tokens[2];
        }else {
            Log.i("Error","StudyCard file have wrong input");
            return;
        }
        while((tokens = mCsvReader.readNext()) != null){

            if(tokens.length == 3) {
                mStudyCards.add(new StudyCard(mVersion,mExam,mStream,tokens[0],tokens[1],tokens[2]));
            }else {
                Log.i("Error","StudyCard file have wrong input");
                return;
            }
        }
    }

    public List<StudyCard> getStudyCards() {
        return mStudyCards;
    }

}
