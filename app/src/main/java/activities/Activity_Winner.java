package activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oz_tal_application_project.R;
import com.example.oz_tal_application_project.utils.Constans;
import com.example.oz_tal_application_project.utils.MySP;
import com.example.oz_tal_application_project.utils.getLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import callbacks.CallBack_Location;
import objects.Record;
import objects.TopTen;


public class Activity_Winner extends Activity_Base {
    private TextView LBL_theWinner;
    private Button BTN_top_ten_list;
    private TextView LBL_scoreWinner;
    private FusedLocationProviderClient client;
    private Record record;
    private int scoreWinner;
    private String theWinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        findByView();
        initView();
        setNameAndScoreWinner();
        getLocation();
    }


    private void getLocation() {
        client = LocationServices.getFusedLocationProviderClient(this);
        getLocation.getInstance().getCurrentLocation(new CallBack_Location() {
            @Override
            public void onLocationSuccess(double latitude, double longitude) {
                record.setLongitude(longitude);
                record.setLatitude(latitude);
                updateTopTen(record);
            }

            @Override
            public void onLocationFailure(String msg) {
                Toast.makeText(Activity_Winner.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        BTN_top_ten_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Winner.this, Activity_TopTen.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void findByView() {
        LBL_theWinner = findViewById(R.id.LBL_theWinner);
        BTN_top_ten_list = findViewById(R.id.BTN_top_ten_list);
        LBL_scoreWinner = findViewById(R.id.LBL_scoreWinner);
    }

    private void setNameAndScoreWinner() {
        record = new Record();
        theWinner = getIntent().getStringExtra(Constans.WINNER);
        scoreWinner = getIntent().getIntExtra(Constans.SCORE, 0);
        LBL_scoreWinner.setText(scoreWinner + "");
        if (!theWinner.equals(Constans.TIE)) {
            record.setName(theWinner);
            record.setScore(scoreWinner);
            LBL_theWinner.setText(theWinner + " WIN");
        } else {
            LBL_theWinner.setText(theWinner);
        }
    }

    private void updateTopTen(Record record) {
        TopTen topTen;
        String str = MySP.getInstance().getString(MySP.KEYS.KEY_TOP_TEN, "");
        if (str.isEmpty())
            topTen = new TopTen();
        else
            topTen = new Gson().fromJson(str, TopTen.class);
        topTen.topTenRecords(record);
        MySP.getInstance().putString(MySP.KEYS.KEY_TOP_TEN, new Gson().toJson(topTen));
    }

}