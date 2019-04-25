package com.apps.kanchan.mcq.Helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.kanchan.mcq.ExamSelectorActivity;
import com.apps.kanchan.mcq.MainActivity;
import com.apps.kanchan.mcq.R;

/**
 * Created by root on 28/1/19.
 */

public class ExamCategoryOnClickHandler {
    private static int mmSelectedItem;
    private static View mSelectedView;
    private static TextView mSelectedTextView;

    private static final String SELECTED_VIEW_BACKGROUND_COLOR = "#FFE4E1";
    private static final String UNSELECTED_VIEW_BACKGROUND_COLOR = "#FFFFFF";

    private ExamCategoryOnClickHandler() {
        mmSelectedItem = -1;
        mSelectedTextView = null;
        mSelectedView = null;
    }

    public static ExamCategoryOnClickHandler getInstance() {
        return  new ExamCategoryOnClickHandler();
    }

    public static void processOnclick(View view) {
        TextView textView = ((TextView)((CardView)view).getChildAt(0));
        if(mSelectedView != null) {
            mSelectedTextView = ( (TextView) ((CardView) mSelectedView).getChildAt(0));
        }

        if(mmSelectedItem == -1) {
            //view.setBackgroundColor(Color.parseColor(SELECTED_VIEW_BACKGROUND_COLOR));
            //textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            textView.setTextColor(view.getResources().getColor(R.color.deepSkyBlue));
            //MainActivity.setExamCategoryRecycleView((String) textView.getText());

            MainActivity.SELECTED_CATEGORY = (String) textView.getText(); //Set instructions

            mmSelectedItem = (int) view.getTag();
            mSelectedView = view;

        }else if(mmSelectedItem == (int) view.getTag())  {
            //view.setBackgroundColor(Color.parseColor(UNSELECTED_VIEW_BACKGROUND_COLOR));
            textView.setTextColor(view.getResources().getColor(R.color.black));
            //textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            //MainActivity.setExamCategoryRecycleView("");
            MainActivity.SELECTED_CATEGORY = ""; //Set instructions

            mmSelectedItem = -1;
            mSelectedView = null;

        }else {
            //view.setBackgroundColor(Color.parseColor(SELECTED_VIEW_BACKGROUND_COLOR));
            textView.setTextColor(view.getResources().getColor(R.color.deepSkyBlue));
            //textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            //mSelectedView.setBackgroundColor(Color.parseColor(UNSELECTED_VIEW_BACKGROUND_COLOR));
            //mSelectedTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mSelectedTextView.setTextColor(view.getResources().getColor(R.color.black));
            //MainActivity.setExamCategoryRecycleView((String) textView.getText());

            MainActivity.SELECTED_CATEGORY = (String) textView.getText(); //Set instructions


            mSelectedView = view;
            mmSelectedItem = (int) view.getTag();
        }
        //ExamSelectorActivity.setInstructions();
        ExamSelectorActivity.setStartPreparationState(); // Set start preparation textView
    }
}
