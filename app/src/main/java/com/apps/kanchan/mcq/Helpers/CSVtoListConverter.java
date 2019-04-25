package com.apps.kanchan.mcq.Helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 28/1/19.
 */

public class CSVtoListConverter {
    private AssetManager mAssetManager;
    //private InputStream inputStream;
    private Map<String,ArrayList<String>> mExamMap;
    private CSVReader mCsvReader;


    public CSVtoListConverter(Context context,String source) throws IOException {
        mAssetManager = context.getAssets();
        mExamMap = new HashMap<>();
        String[] tokens;
        try {
            //inputStream = mAssetManager.open(source);
            mCsvReader = new CSVReader(new InputStreamReader(mAssetManager.open(source), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while((tokens = mCsvReader.readNext()) != null){
            ArrayList<String> examCategories =  mExamMap.get(tokens[0]);
            if(mExamMap.containsKey(tokens[0])) {
                mExamMap.get(tokens[0]).add(tokens[1]);
            }else {
                mExamMap.put(tokens[0],new ArrayList<String>(Arrays.asList(tokens[1])));
            }
        }
    }

    public Map<String,ArrayList<String >> getExamMap() {
        return mExamMap;
    }
}
