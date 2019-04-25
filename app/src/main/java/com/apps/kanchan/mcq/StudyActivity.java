package com.apps.kanchan.mcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.FrameLayout;

import com.apps.kanchan.mcq.Adapters.RevisionRecyclerAdapter;
import com.apps.kanchan.mcq.Adapters.StudyRecyclerAdapter;
import com.apps.kanchan.mcq.Helpers.StudyCardCSVObjectifier;
import com.apps.kanchan.mcq.POJOs.StudyCard;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        Intent intent =  getIntent();
        String activity = intent.getStringExtra("activity");

        StudyCardCSVObjectifier studyCardCSVObjectifier = null;
        try {
            studyCardCSVObjectifier = new StudyCardCSVObjectifier(getApplicationContext(),"e3e4m1m4.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<StudyCard> studyCards =  new ArrayList<>();
        studyCards = (ArrayList<StudyCard>) studyCardCSVObjectifier.getStudyCards();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.studyRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(activity.equals("study")) {
            StudyRecyclerAdapter studyRecyclerAdapter = new StudyRecyclerAdapter(studyCards);
            recyclerView.setAdapter(studyRecyclerAdapter);
        }else if(activity.equals("revision")) {
            RevisionRecyclerAdapter revisionRecyclerAdapter = new RevisionRecyclerAdapter(studyCards);
            recyclerView.setAdapter(revisionRecyclerAdapter);
        }
    }
}
