package com.apps.kanchan.mcq.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apps.kanchan.mcq.R;

/**
 * Created by root on 25/1/19.
 */

public  class ExamViewHolder extends RecyclerView.ViewHolder {
    protected TextView vExamName;

    public ExamViewHolder(View itemView) {
        super(itemView);
        vExamName = (TextView) itemView.findViewById(R.id.examNameTextView);
    }
}
