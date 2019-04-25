package com.apps.kanchan.mcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.kanchan.mcq.Adapters.optionListViewAdapter;
import com.apps.kanchan.mcq.POJOs.Question;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    public static int questionNumber;
    public static int[] selectedOptions;
    public static boolean[] questionScored;
    static  ArrayList<Question> mQuestions;
    private int numberOfQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionNumber  = 0;
        mQuestions = (ArrayList<Question>) MainActivity.test.getQuestions();
        numberOfQuestions =  mQuestions.size();
        ImageButton prevImageButton = (ImageButton) findViewById(R.id.previousIB);
        ImageButton nextImageButton = (ImageButton) findViewById(R.id.nextIB);
        Button finishButton = (Button) findViewById(R.id.finishButton);

        Intent intent = getIntent();
        final String reviewType = intent.getStringExtra("reviewType");
        if(!MainActivity.test.isIsLocked()) {

            selectedOptions = new int[numberOfQuestions];
            questionScored = new boolean[numberOfQuestions];
            for (int i = 0; i < numberOfQuestions; i++) {
                selectedOptions[i] = -1;
            }

            setQuestion(questionNumber);
            prevImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionNumber > 0) {
                        questionNumber--;
                        setQuestion(questionNumber);
                    }
                }
            });

            nextImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionNumber < MainActivity.questions.size() - 1) {
                        questionNumber++;
                        setQuestion(questionNumber);
                    }
                }
            });

            finishButton.setOnClickListener(new View.OnClickListener() {
                int totalScore = 0;
                @Override
                public void onClick(View view) {
                    MainActivity.test.setIsLocked(true);
                    for (int i = 0; i < numberOfQuestions; i++) {
                        if (selectedOptions[i] != -1) {
                            if (selectedOptions[i] == MainActivity.questions.get(i).getCorrectOption()) {
                                questionScored[i] = true;
                                totalScore++;
                            } else {
                                questionScored[i] = false;
                            }
                        } else {
                            questionScored = null;
                        }
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                    Log.i("Info", totalScore + ":" + numberOfQuestions);
                    intent.putExtra("totalScore", totalScore + "");
                    intent.putExtra("totalQuestions", numberOfQuestions + "");
                    startActivity(intent);
                }
            });
        }else if(reviewType != null){
            questionNumber = 0;
            if(reviewType.equals("all")) {
                setQuestion(questionNumber);
                Log.i("Info","Q(Start): "+questionNumber);
            }else if(reviewType.equals("incorrect")) {
                while(selectedOptions[questionNumber] == mQuestions.get(questionNumber).getCorrectOption()) {
                    questionNumber++;
                }
                setQuestion(questionNumber);
            }
            prevImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionNumber > 0) {
                        if(reviewType.equals("incorrect")) {
                            questionNumber--;
                            while(selectedOptions[questionNumber] == mQuestions.get(questionNumber).getCorrectOption()) {
                                questionNumber--;
                            }
                            if(questionNumber >= 0){
                                setQuestion(questionNumber);
                            }
                        }else {
                            setQuestion(--questionNumber);
                        }
                    }
                }
            });

            nextImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Info","Q(NextClick): "+questionNumber);
                    if (questionNumber < numberOfQuestions -1) {
                        if(reviewType.equals("incorrect")) {
                            questionNumber++;
                            while(selectedOptions[questionNumber] == mQuestions.get(questionNumber).getCorrectOption()) {
                                questionNumber++;
                            }
                            if(questionNumber < numberOfQuestions) {
                                setQuestion(questionNumber);
                            }
                        }else {
                            setQuestion(++questionNumber);
                            Log.i("Info","Q(NextClicked): "+questionNumber);
                        }
                    }
                }
            });

            finishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    MainActivity.test.setIsLocked(false);
                    questionNumber  = 0;
                    startActivity(intent);
                }
            });

        }
    }
    public void setQuestion(int questionNumber){
        Log.i("Info"," Set question called "+ questionNumber);
        Question question = mQuestions.get(questionNumber);

        TextView questionTextView = (TextView) findViewById(R.id.questionTV);
        String questionString = (questionNumber+1)+"."+"\t"+question.getQuestion();
        questionTextView.setText(questionString);
        Log.i("Info",question.getQuestion());

        int imageId = question.getImageResourceId();
        ImageView questionIV = (ImageView) findViewById(R.id.questionIV);

        ListView optionListView = (ListView) findViewById(R.id.optionsLV);
        ArrayAdapter arrayAdapter = new optionListViewAdapter(this, question.getOptions());
        optionListView.setAdapter(arrayAdapter);

        if(imageId == 0) {
            questionIV.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) optionListView.getLayoutParams();
            lp.addRule(RelativeLayout.BELOW,questionTextView.getId());
            optionListView.setLayoutParams(lp);
        }
    }
}
