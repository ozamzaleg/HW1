package com.example.oz_tal_application_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {
    final int FOUR=4;
    final int ONE=1;
    private int counterPlayer1 = 0;
    private int counterPlayer2 = 0;
    private TextView LBL_counterPlayer1;
    private TextView LBL_counterPlayer2;
    private Button BTN_Game;
    private ImageView IMG_Card1;
    private ImageView IMG_Card2;
    private ArrayList<Integer> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LBL_counterPlayer1 = findViewById(R.id.LBL_counterPlayer1);
        LBL_counterPlayer2 = findViewById(R.id.LBL_counterPlayer2);
        BTN_Game = findViewById(R.id.BTN_Game);
        IMG_Card1 = findViewById(R.id.IMG_Card1);
        IMG_Card2 = findViewById(R.id.IMG_Card2);
        image();
        BTN_Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.snd_cards);
                create();
            }
        });
        try {
            if (savedInstanceState != null) {
                int previousCount1 = savedInstanceState.getInt("ScorePlayer1");
                int previousCount2 = savedInstanceState.getInt("ScorePlayer2");
                counterPlayer1 = previousCount1;
                counterPlayer2 = previousCount2;
                LBL_counterPlayer1.setText("" + counterPlayer1);
                LBL_counterPlayer2.setText(""+counterPlayer2);
            }
        } catch (Exception ex) {
        }
    }
    private void image() {

        Field[] images = R.drawable.class.getFields();
        cards=new ArrayList<Integer>();
        for (int i = 0; i < images.length; i++) {
            if (images[i].getName().contains("poker_")) {
                cards.add(getResources().getIdentifier(images[i].getName(), "drawable", this.getPackageName()));
            }

        }
    }

    public void create() {
        int cardsNumber1,cardsNumber2;
        if(!cards.isEmpty()) {
            Random rnd = new Random();
            int indexCards = rnd.nextInt(cards.size());
            IMG_Card1.setImageResource(cards.get(indexCards));
            String str1 = IMG_Card1.getResources().getResourceEntryName(cards.get(indexCards));
            cards.remove(indexCards);
            indexCards = rnd.nextInt(cards.size());
            IMG_Card2.setImageResource(cards.get(indexCards));
            String str2 = IMG_Card2.getResources().getResourceEntryName(cards.get(indexCards));
            cards.remove(indexCards);
                cardsNumber1= (Integer.parseInt(str1.substring(str1.length() - 2)));
                cardsNumber2=(Integer.parseInt(str2.substring(str2.length() - 2)));
                if ((cardsNumber1/FOUR + ONE) > (cardsNumber2/FOUR+ONE)) {
                    counterPlayer1++;
                    LBL_counterPlayer1.setText("" + counterPlayer1);
                } else if((cardsNumber1/FOUR + ONE) < (cardsNumber2/FOUR+ONE)) {
                    counterPlayer2++;
                    LBL_counterPlayer2.setText("" + counterPlayer2);
                }
        }else {
            Intent intent = new Intent(Game.this, Winner.class);
            if (counterPlayer2 < counterPlayer1) {
                playSound(R.raw.snd_win);
                intent.putExtra("WINNER", "PLAYER 1 WIN");
                intent.putExtra("SCORE", "SCORE:"+counterPlayer1);
            } else if ( counterPlayer1 < counterPlayer2) {
                playSound(R.raw.snd_win);
                intent.putExtra("WINNER", "PLAYER 2 WIN");
                intent.putExtra("SCORE", "SCORE:"+counterPlayer2);
            } else if ( counterPlayer1 == counterPlayer2) {
                intent.putExtra("WINNER", "TIE");
                intent.putExtra("SCORE", "SCORE:"+counterPlayer2);
            }
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ScorePlayer1", counterPlayer1);
        outState.putInt("ScorePlayer2", counterPlayer2);
    }
    private MediaPlayer mp;
    private void playSound(int rawSound) {
        if (mp != null) {
            try {
                mp.reset();
                mp.release();
            } catch (Exception ex) { }
        }

        mp = MediaPlayer.create(this, rawSound);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }
        });
        mp.start();
    }

}