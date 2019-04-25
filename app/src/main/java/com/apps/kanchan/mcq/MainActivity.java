package com.apps.kanchan.mcq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.apps.kanchan.mcq.Adapters.ExamReclAdatpterMain;
import com.apps.kanchan.mcq.Helpers.CSVtoListConverter;
import com.apps.kanchan.mcq.Helpers.StudyCardCSVObjectifier;
import com.apps.kanchan.mcq.Helpers.StudyNotesDBHelper;
import com.apps.kanchan.mcq.POJOs.Question;
import com.apps.kanchan.mcq.POJOs.StudyCard;
import com.apps.kanchan.mcq.POJOs.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static List<Question> questions = new ArrayList<>();
    public static Test test;
    static Map<String,List<String>> testCategories;
    static Map<String,ArrayList<String>> examMap;
    static public String SELECTED_EXAM =  "";
    public static String SELECTED_CATEGORY = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        deleteDatabase(StudyNotesDBHelper.DATABASE_NAME);
        StudyCardCSVObjectifier e3e4ManagementObjectifier = null;
        try {
            e3e4ManagementObjectifier = new StudyCardCSVObjectifier(getApplicationContext(),"e3e4m1m4.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StudyNotesDBHelper studyNotesDBHelper = StudyNotesDBHelper.getInstance(this);
        studyNotesDBHelper.insertData((ArrayList<StudyCard>) e3e4ManagementObjectifier.getStudyCards());



        CSVtoListConverter csVtoListConverter =  null;
        examMap =  null;
        try {
            csVtoListConverter =  new CSVtoListConverter(getApplicationContext(),"exam_categories.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(csVtoListConverter != null) {
            examMap = csVtoListConverter.getExamMap();
            Log.i("Info","ExamMap size :"+examMap.size()+"");
        }

        RecyclerView examReclList = (RecyclerView) findViewById(R.id.examsRecyclerView);
        examReclList.setHasFixedSize(true);
        GridLayoutManager examLinearLayoutManager = new GridLayoutManager(this,3);
        //examLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        examReclList.setLayoutManager(examLinearLayoutManager);
        ExamReclAdatpterMain examReclAdatpter = new ExamReclAdatpterMain(new ArrayList<>(examMap.keySet()));
        examReclList.setAdapter(examReclAdatpter);

        if(SELECTED_EXAM.equals("") || SELECTED_CATEGORY.equals("")) {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.progressFrameLayout);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.progressRecyclerView);
            frameLayout.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }

    }



    void inputData(String key,String value,Map<String,List<String>> map){
        if(map.get(key) == null) {
            map.put(key,new ArrayList<String>());
        }
        map.get(key).add(value);
    }
}
