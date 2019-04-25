package com.apps.kanchan.mcq.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.kanchan.mcq.Helpers.ExamCategoryOnClickHandler;
import com.apps.kanchan.mcq.R;

import java.util.ArrayList;

/**
 * Created by root on 27/1/19.
 */

public class ExamCategoryReclAdapter extends RecyclerView.Adapter<ExamCategoryReclAdapter.ExamCategoryViewHolder> {
    private ArrayList<String> examCategories;
    private Context mContext;

    public ExamCategoryReclAdapter(ArrayList<String> examCategories) {
        this.examCategories = examCategories;
    }

    @Override
    public ExamCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_category_cardview,parent,false);
        return new ExamCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExamCategoryViewHolder holder, int position) {
        holder.vExamCategory.setText(examCategories.get(position));
        holder.vItemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if(examCategories != null) {
            return examCategories.size();
        }
        return  0;
    }

    public static class ExamCategoryViewHolder extends RecyclerView.ViewHolder {
        protected TextView vExamCategory;
        protected View vItemView;
        ExamCategoryOnClickHandler examCategoryOnClickHandler = ExamCategoryOnClickHandler.getInstance();
        public ExamCategoryViewHolder(View itemView) {
            super(itemView);
            vItemView = itemView;
            vExamCategory = itemView.findViewById(R.id.examCategoryTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    examCategoryOnClickHandler.processOnclick(view);
                }
            });
        }
    }
}
