package com.apps.kanchan.mcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String totalQuestions = intent.getStringExtra("totalQuestions");
        String totalScore = intent.getStringExtra("totalScore");
        Log.i("Info",totalQuestions);
        Log.i("Info",totalScore);
        TextView totalScoreTV = (TextView) findViewById(R.id.totalScoreTV);
        TextView totalQuestionsTV = (TextView) findViewById(R.id.totalQuestionsTV);
        totalScoreTV.setText(totalScore);
        totalQuestionsTV.setText(totalQuestions);

        TextView reviewAllTextView = (TextView) findViewById(R.id.ReviewAllTextView);
        TextView reviewIncorrectTextView = (TextView) findViewById(R.id.ReviewIncorrectTextView);

        reviewAllTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),TestActivity.class);
                intent1.putExtra("reviewType","all");
                startActivity(intent1);
            }
        });
        if(Integer.parseInt(totalScore) < Integer.parseInt(totalQuestions)) {
            reviewIncorrectTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), TestActivity.class);
                    intent1.putExtra("reviewType", "incorrect");
                    startActivity(intent1);
                }
            });
        }
    }
}
