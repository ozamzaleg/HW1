package com.example.oz_tal_application_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private int counter1 = 0;
    private int counter2 = 0;
    private TextView main_LBL_counter1;
    private TextView main_LBL_counter2;
    private Button main_BTN_startGame;
    private ImageView main_IMG_Card1;
    private ImageView main_IMG_Card2;
    private ArrayList<Integer> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        main_LBL_counter1 = findViewById(R.id.main_LBL_counter1);
        main_LBL_counter2 = findViewById(R.id.main_LBL_counter2);
        main_BTN_startGame = findViewById(R.id.main_BTN_startGame);
        main_IMG_Card1 = findViewById(R.id.main_IMG_Card1);
        main_IMG_Card2 = findViewById(R.id.main_IMG_Card2);
        image();
        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();

            }
        });
        try {
            if (savedInstanceState != null) {
                int previousCount1 = savedInstanceState.getInt("MY_INT1");
                int previousCount2 = savedInstanceState.getInt("MY_INT2");
                counter1 = previousCount1;
                counter2 = previousCount2;
                main_LBL_counter1.setText("" + counter1);
                main_LBL_counter2.setText(""+counter2);
            }
        } catch (Exception ex) { }

    }


    private void image() {

        Field[] images = R.drawable.class.getFields();
        arr=new ArrayList<Integer>();//arr the will contain id of all image with name poker_
        for (int i = 0; i < images.length; i++) {
            if (images[i].getName().contains("poker_")) {
                arr.add(getResources().getIdentifier(images[i].getName(), "drawable", this.getPackageName()));//add id of all the image with name poker_
            }

        }
    }

    public void create() {
        int number1,number2;
        if(arr.size()>0) {
            Random rnd = new Random();
            int index = rnd.nextInt(arr.size());
            main_IMG_Card1.setImageResource(arr.get(index));
            String str1 = main_IMG_Card1.getResources().getResourceEntryName(arr.get(index));
            arr.remove(index);
            index = rnd.nextInt(arr.size());
            main_IMG_Card2.setImageResource(arr.get(index));
            String str2 = main_IMG_Card2.getResources().getResourceEntryName(arr.get(index));
            arr.remove(index);
                number1= (Integer.parseInt(str1.substring(str1.length() - 2)));
                number2=(Integer.parseInt(str2.substring(str2.length() - 2)));
                if ((number1/4 + 1) > (number2/4+1)) {
                    increaseCounter1();
                } else if((number1/4 + 1) < (number2/4+1))
                    increaseCounter2();
                else if((number1/4 + 1) == (number2/4+1)){
                    increaseCounter1();
                    increaseCounter2();
                }
        }else {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            if (counter2 < counter1) {
                intent.putExtra("EXTRA_SESSION_ID", "PLAYER 1 WIN");
            } else if ( counter1 < counter2) {
                intent.putExtra("EXTRA_SESSION_ID", "PLAYER 2 WIN");
            } else if ( counter1 == counter2) {
                intent.putExtra("EXTRA_SESSION_ID", "TIE");
            }
            startActivity(intent);
            finish();
        }

    }

    private void increaseCounter1() {
        counter1++;
        main_LBL_counter1.setText("" + counter1);
        Log.d("pttt", "" + counter1);
    }
    private void increaseCounter2() {
        counter2++;
        main_LBL_counter2.setText("" + counter2);
        Log.d("pttt", "" + counter2);
        Log.d("pttt", "" + counter1);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt("MY_INT1", counter1);
        outState.putInt("MY_INT2", counter2);
    }
    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }
}