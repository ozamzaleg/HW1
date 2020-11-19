package com.example.oz_tal_application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private TextView score_LBL_title;
    private Button score_BTN_close;
    private TextView main_LBL_scoreWinner;
    private Button score_BTN_RECORDS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        score_LBL_title=findViewById(R.id.score_LBL_title);
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        score_LBL_title.setText(sessionId);
        main_LBL_scoreWinner=findViewById(R.id.main_LBL_scoreWinner);
        String sessionId1 = getIntent().getStringExtra("EXTRA_SESSION_ID1");
        main_LBL_scoreWinner.setText(sessionId1);
        score_BTN_close=findViewById(R.id.score_BTN_close);
        score_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}