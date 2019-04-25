package com.apps.kanchan.mcq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apps.kanchan.mcq.Adapters.ExamCategoryReclAdapter;
import com.apps.kanchan.mcq.Adapters.ExamReclAdatpter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;


public class ExamSelectorActivity extends AppCompatActivity {

    static RecyclerView examCategoryReclList;
    static ExamCategoryReclAdapter examCategoryReclAdapter;
    //static TextView sInstructions;
    static TextView sStartPreparation;
    static Context sContext;
    static TextView sCategoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_recl);

        sContext = getApplicationContext();
        //sInstructions =  (TextView) findViewById(R.id.instructionsTextView);
        sStartPreparation = (TextView) findViewById(R.id.startTextView);
        sCategoryTextView = (TextView) findViewById(R.id.categoryTextView);
        setCategoryTextViewVisibility();
        setStartPreparationState();

        RecyclerView examReclList = (RecyclerView) findViewById(R.id.examsRecycleView2);
        examReclList.setHasFixedSize(true);
        LinearLayoutManager examLinearLayoutManager = new LinearLayoutManager(this);
        examLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //SnapHelper snapHelper = new LinearSnapHelper();
        //snapHelper.attachToRecyclerView(examReclList);
        examReclList.setLayoutManager(examLinearLayoutManager);
        ExamReclAdatpter examReclAdatpter = new ExamReclAdatpter(new ArrayList<>(MainActivity.examMap.keySet()));
        examReclList.setAdapter(examReclAdatpter);

        examCategoryReclList = (RecyclerView) findViewById(R.id.examCategoriesRecycleView);
        //examCategoryReclList.setHasFixedSize(true);
        final GridLayoutManager examCategoryGlm = new GridLayoutManager(this,2);
        examCategoryReclList.setLayoutManager(examCategoryGlm);
        /*examCategoryReclList.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        examCategoryReclList.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int viewWidth = examCategoryReclList.getMeasuredWidth();
                        float cardViewWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,136,getApplicationContext().getResources().getDisplayMetrics());
                        int newSpanCount = (int) Math.floor(viewWidth / cardViewWidth);
                        examCategoryGlm.setSpanCount(newSpanCount);
                        examCategoryGlm.requestLayout();
                    }
                }
        );*/

        TextView confirm = (TextView) findViewById(R.id.startTextView);
        final Intent intent = new Intent(this,MyCourseActivity.class);
        confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!(MainActivity.SELECTED_EXAM.equals("") || MainActivity.SELECTED_CATEGORY.equals(""))) {
                        startActivity(intent);
                    }
                }
            });

        final Intent intent1 = new Intent(this,MainActivity.class);
        ImageButton imageButton = (ImageButton) findViewById(R.id.selectionToBack);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

    }

    public  static void setCategoryTextViewVisibility() {
        if(MainActivity.SELECTED_EXAM.equals("")) {
            sCategoryTextView.setVisibility(View.INVISIBLE);
        }else {
            sCategoryTextView.setVisibility(View.VISIBLE);
        }
    }

    public static void setStartPreparationState() {
        if(MainActivity.SELECTED_EXAM.equals("") || MainActivity.SELECTED_CATEGORY.equals("")) {
            sStartPreparation.setBackgroundColor(ContextCompat.getColor(sContext,R.color.white));
            sStartPreparation.setTextColor(ContextCompat.getColor(sContext,R.color.lightGray));
        }else {
            sStartPreparation.setBackgroundColor(ContextCompat.getColor(sContext,R.color.deepSkyBlue));
            sStartPreparation.setTextColor(ContextCompat.getColor(sContext,R.color.white));
        }

    }

    public static void setExamCategoryRecycleView(String examName) {
        ArrayList<String> examCategories = (ArrayList<String>) MainActivity.examMap.get(examName);
        if(examCategories != null) {
            Log.i("Info","MainActivity, examCategories size "+examCategories.size()+" for "+examName);
        }
        examCategoryReclAdapter = new ExamCategoryReclAdapter(examCategories);
        examCategoryReclList.setAdapter(examCategoryReclAdapter);
    }

    /**public static void setInstructions() {
        if(!MainActivity.SELECTED_EXAM.equals("")) {
            if(MainActivity.SELECTED_CATEGORY.equals("")) {
                sInstructions.setText("You have selected: "+MainActivity.SELECTED_EXAM + "\nNow select the stream.");
            }else {
                sInstructions.setText("You have selected: "+MainActivity.SELECTED_EXAM + "\nYou have selected: "+MainActivity.SELECTED_CATEGORY);
            }
        }else {
            sInstructions.setText("Select the exam.");
        }
    }*/
}
