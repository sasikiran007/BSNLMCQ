package com.apps.kanchan.mcq.Adapters;

/**
 * Created by root on 6/2/19.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.apps.kanchan.mcq.POJOs.StudyCard;
import com.apps.kanchan.mcq.R;

import java.util.ArrayList;


/**
 * Created by root on 1/2/19.
 */

public class RevisionRecyclerAdapter extends RecyclerView.Adapter<RevisionRecyclerAdapter.MyViewHolder> {
    private ArrayList<StudyCard> mStudyCards;

    public RevisionRecyclerAdapter(ArrayList<StudyCard> studyCards) {
        this.mStudyCards = studyCards;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.chapter.setText(mStudyCards.get(position).getChapterName());
        holder.question.setText(mStudyCards.get(position).getQuestion());
        holder.answer.setText(mStudyCards.get(position).getAnswer());
        holder.studyActionBar.setVisibility(View.INVISIBLE);
        holder.studyCardAnswer.setVisibility(View.INVISIBLE);
        holder.revisionActionBar.setVisibility(View.INVISIBLE);
        holder.studyCardAnswerShow.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mStudyCards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView chapter;
        private TextView question;
        private TextView answer;
        private FrameLayout studyCardAnswer;
        private FrameLayout studyCardAnswerShow;
        private FrameLayout studyActionBar;
        private FrameLayout revisionActionBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            chapter = itemView.findViewById(R.id.chapter);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
            studyCardAnswerShow = itemView.findViewById(R.id.studyCardAnswerShow);
            studyCardAnswer = itemView.findViewById(R.id.studyCardAnswer);
            revisionActionBar = itemView.findViewById(R.id.revisionActionBar);
            studyActionBar = itemView.findViewById(R.id.studyActionBar);
        }
    }
}
