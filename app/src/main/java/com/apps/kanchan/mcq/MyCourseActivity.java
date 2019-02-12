package com.apps.kanchan.mcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyCourseActivity extends AppCompatActivity {
    private static CardView sStudyCard;
    private static CardView sRevisionCard;
    private static CardView sTestCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);

        sStudyCard = (CardView) findViewById(R.id.studyCardView);
        sRevisionCard = (CardView) findViewById(R.id.revisionCardView);
        sTestCardView = (CardView) findViewById(R.id.testCardView);

        final Intent intent = new Intent(this,StudyActivity.class);

        sStudyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("activity","study");
                startActivity(intent);
            }
        });

        sRevisionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("activity","revision");
                startActivity(intent);
            }
        });

    }
}
