package com.apps.kanchan.mcq.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apps.kanchan.mcq.MainActivity;
import com.apps.kanchan.mcq.R;
import com.apps.kanchan.mcq.TestActivity;

import java.util.List;

/**
 * Created by root on 13/12/18.
 */

public class optionListViewAdapter extends ArrayAdapter<String> {
    private List<String> mOptions;
    private Context mContext;

    public optionListViewAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
        mOptions = objects;
        mContext = context;
        Log.i("Info","Constructor called");
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.i("Info","------------"+position+","+ TestActivity.questionNumber);
        View view = convertView;
        //Log.i("Info","getView called and selected option is"+view.toString());
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view  = inflater.inflate(R.layout.text_option,parent,false);
            Log.i("Info","-Null view-----"+position);
        }

        final String option = mOptions.get(position);

        TextView indexTextView = view.findViewById(R.id.indexTV);
        TextView optionTextView = view.findViewById(R.id.optionTV);
        final RadioButton  radioButton = view.findViewById(R.id.answerRB);

        char index = (char) ('a' + position);
        indexTextView.setText(Character.toString(index));
        optionTextView.setText(option);

        int selectedOption = TestActivity.selectedOptions[TestActivity.questionNumber];
        if (selectedOption == position) {
            radioButton.setChecked(true);
        }else if(selectedOption != -1) {
            radioButton.setChecked(false);
        }

        if(!MainActivity.test.isIsLocked()) {

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectedOption = TestActivity.selectedOptions[TestActivity.questionNumber];
                    if (selectedOption == position) {
                        radioButton.setChecked(false);
                        TestActivity.selectedOptions[TestActivity.questionNumber] = -1;
                        TestActivity.questionScored = null;
                    } else {
                        TestActivity.selectedOptions[TestActivity.questionNumber] = position;
                        notifyDataSetChanged();
                    }
                    notifyDataSetChanged();
                }
            });
        }
        else {
            radioButton.setClickable(false);
            int correctOption = 1;
            Log.i("Info","QuestionNumber"+TestActivity.questionNumber);
            correctOption = MainActivity.test.getQuestions().get(TestActivity.questionNumber).getCorrectOption();
            selectedOption = TestActivity.selectedOptions[TestActivity.questionNumber];
            Log.i("Info",position+","+selectedOption+","+correctOption);
            if(selectedOption == position) {
                if(selectedOption != correctOption) {
                    view.setBackgroundColor(Color.parseColor("#b3686e"));
                }
                else {
                    view.setBackgroundColor(Color.parseColor("#92aeb9"));
                }

            }else if(position == correctOption) {
                view.setBackgroundColor(Color.parseColor("#92aeb9"));
            }
        }
        return view;
    }
}
