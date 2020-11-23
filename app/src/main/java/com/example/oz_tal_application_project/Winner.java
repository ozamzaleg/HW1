package com.example.oz_tal_application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Winner extends AppCompatActivity {
    private TextView LBL_theWinner;
    private Button BTN_newGame;
    private TextView LBL_scoreWinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        LBL_theWinner=findViewById(R.id.LBL_theWinner);
        String theWinner = getIntent().getStringExtra("WINNER");
        LBL_theWinner.setText(theWinner);
        LBL_scoreWinner=findViewById(R.id.LBL_scoreWinner);
        String scoreWinner = getIntent().getStringExtra("SCORE");
        LBL_scoreWinner.setText(scoreWinner);
        BTN_newGame=findViewById(R.id.BTN_newGame);
        BTN_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Winner.this, StartGame.class);
                startActivity(intent);
                finish();
            }
        });
    }
}