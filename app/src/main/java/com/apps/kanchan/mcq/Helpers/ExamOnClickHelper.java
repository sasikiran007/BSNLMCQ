package com.apps.kanchan.mcq.Helpers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.kanchan.mcq.ExamSelectorActivity;
import com.apps.kanchan.mcq.MainActivity;
import com.apps.kanchan.mcq.R;

/**
 * Created by root on 28/1/19.
 */

public class ExamOnClickHelper {
    private static int mmSelectedItem;
    private static View mSelectedView;
    private static TextView mSelectedTextView;
    
    private static final String SELECTED_VIEW_BACKGROUND_COLOR = "#FFE4E1";
    private static final String UNSELECTED_VIEW_BACKGROUND_COLOR = "#FFFFFF";

    private ExamOnClickHelper() {
        mmSelectedItem = -1;
        mSelectedTextView = null;
        mSelectedView = null;
    }
    
    public static ExamOnClickHelper getInstance() {
        return  new ExamOnClickHelper();
    }
    
    public static void processOnclick(View view) {
        TextView textView = ((TextView)((RelativeLayout)((CardView)view).getChildAt(0)).getChildAt(0));
        if(mSelectedView != null) {
            mSelectedTextView = ((TextView) ((RelativeLayout) ((CardView) mSelectedView).getChildAt(0)).getChildAt(0));
        }
        
        if(mmSelectedItem == -1) {
            //view.setBackgroundColor(Color.parseColor(SELECTED_VIEW_BACKGROUND_COLOR));
            //textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            textView.setTextColor(view.getResources().getColor(R.color.deepSkyBlue));
            ExamSelectorActivity.setExamCategoryRecycleView((String) textView.getText());

            MainActivity.SELECTED_EXAM = (String) textView.getText(); //Set instructions
            MainActivity.SELECTED_CATEGORY = ""; //Set instructions

            mmSelectedItem = (int) view.getTag();
            mSelectedView = view;

        }else if(mmSelectedItem == (int) view.getTag())  {
            //view.setBackgroundColor(Color.parseColor(UNSELECTED_VIEW_BACKGROUND_COLOR));
            //textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textView.setTextColor(view.getResources().getColor(R.color.black));
            ExamSelectorActivity.setExamCategoryRecycleView("");

            MainActivity.SELECTED_EXAM = ""; //Set instructions
            MainActivity.SELECTED_CATEGORY = ""; //Set instructions

            mmSelectedItem = -1;
            mSelectedView = null;

        }else {
            //view.setBackgroundColor(Color.parseColor(SELECTED_VIEW_BACKGROUND_COLOR));
            //textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            textView.setTextColor(view.getResources().getColor(R.color.deepSkyBlue));
            //mSelectedView.setBackgroundColor(Color.parseColor(UNSELECTED_VIEW_BACKGROUND_COLOR));
            //mSelectedTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mSelectedTextView.setTextColor(view.getResources().getColor(R.color.black));
            ExamSelectorActivity.setExamCategoryRecycleView((String) textView.getText());

            MainActivity.SELECTED_EXAM = (String) textView.getText(); //Set instructions
            MainActivity.SELECTED_CATEGORY = ""; //Set instructions

            mSelectedView = view;
            mmSelectedItem = (int) view.getTag();
        }
        //ExamSelectorActivity.setInstructions();
        ExamSelectorActivity.setStartPreparationState();
        ExamSelectorActivity.setCategoryTextViewVisibility();
    }
}
