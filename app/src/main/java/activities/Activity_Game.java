package activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import objects.GameManger;

import com.example.oz_tal_application_project.R;
import com.example.oz_tal_application_project.utils.Constans;
import com.example.oz_tal_application_project.utils.MySP;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Activity_Game extends Activity_Base {
    private TextView name_Number1;
    private TextView name_Number2;
    private String name1;
    private String name2;
    private TextView LBL_counterPlayer1;
    private TextView LBL_counterPlayer2;
    private ImageView IMG_Card1;
    private ImageView IMG_Card2;
    private GameManger gameManger;
    private ProgressBar progressBar;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findByView();
        setName();
        image();
    }


    private void findByView() {
        name_Number1 = findViewById(R.id.LBL_player1);
        name_Number2 = findViewById(R.id.LBL_player2);
        LBL_counterPlayer1 = findViewById(R.id.LBL_counterPlayer1);
        LBL_counterPlayer2 = findViewById(R.id.LBL_counterPlayer2);
        IMG_Card1 = findViewById(R.id.IMG_Card1);
        IMG_Card2 = findViewById(R.id.IMG_Card2);
        progressBar = findViewById(R.id.pBar);
    }

    private void setName() {
        name1 = MySP.getInstance().getString(MySP.KEYS.NAME1, "");
        name_Number1.setText(name1.isEmpty() ? Constans.PLAYER1 : name1);
        name2 = MySP.getInstance().getString(MySP.KEYS.NAME2, "");
        name_Number2.setText(name2.isEmpty() ? Constans.PLAYER2 : name2);
    }

    private void image() {
        gameManger = new GameManger();
        Field[] images = R.drawable.class.getFields();
        for (int i = 0; i < images.length; i++) {
            if (images[i].getName().contains(Constans.POKER)) {
                gameManger.addCards(getResources().getIdentifier(images[i].getName(), "drawable", this.getPackageName()));
            }

        }
    }

    @SuppressLint("ResourceAsColor")
    public void updateImage() {
        if (gameManger.getNumbersOfCards() != 0) {
            LBL_counterPlayer2.setTextColor(Color.WHITE);
            LBL_counterPlayer1.setTextColor(Color.WHITE);
            Random rnd = new Random();
            int indexCards = rnd.nextInt(gameManger.getNumbersOfCards());
            IMG_Card1.setImageResource(gameManger.getCardById(indexCards));
            String str1 = IMG_Card1.getResources().getResourceEntryName(gameManger.getCardById(indexCards));
            gameManger.removeCards(indexCards);
            indexCards = rnd.nextInt(gameManger.getNumbersOfCards());
            IMG_Card2.setImageResource(gameManger.getCardById(indexCards));
            String str2 = IMG_Card2.getResources().getResourceEntryName(gameManger.getCardById(indexCards));
            gameManger.removeCards(indexCards);
            String cards = gameManger.compareValueOfCards(str1, str2);
            if (cards.equals(Constans.PLAYER1)) {
                LBL_counterPlayer1.setTextColor(Color.YELLOW);
                LBL_counterPlayer1.setText("" + gameManger.getCounterPlayer1());
            }
             if(cards.equals(Constans.PLAYER2)) {
                LBL_counterPlayer2.setTextColor(Color.YELLOW);
                LBL_counterPlayer2.setText("" + gameManger.getCounterPlayer2());
            }
        }
    }

    public void winner() {
        if (gameManger.getCounterPlayer2() < gameManger.getCounterPlayer1()) {
            moveToNewActivity(name_Number1.getText().toString(), gameManger.getCounterPlayer1());
        } else if (gameManger.getCounterPlayer1() < gameManger.getCounterPlayer2()) {
            moveToNewActivity(name_Number2.getText().toString(), gameManger.getCounterPlayer2());
        } else {
            moveToNewActivity(Constans.TIE, gameManger.getCounterPlayer2());
        }
    }

    private void moveToNewActivity(String winner, int score) {
        Intent intent = new Intent(Activity_Game.this, Activity_Winner.class);
        intent.putExtra(Constans.WINNER, winner);
        intent.putExtra(Constans.SCORE, score);
        if (!winner.equals(Constans.TIE)) {
            playSound(R.raw.snd_win);
        }
        startActivity(intent);
        finish();
    }

    ScheduledFuture<?> scheduledFuture;

    public void start() {
        scheduledFuture = new ScheduledThreadPoolExecutor(5).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (gameManger.getNumbersOfCards() != 0) {
                            playSound(R.raw.snd_cards);
                            progressBar.setProgress(progressStatus++);
                            updateImage();
                        } else {
                            playSound(R.raw.snd_win);
                            winner();
                        }
                    }
                });
            }
        }, 0L, 2000, TimeUnit.MILLISECONDS);
    }

    private void stop() {
        scheduledFuture.cancel(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }

}