package activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.oz_tal_application_project.R;
import com.example.oz_tal_application_project.utils.Constans;

public class Activity_StartGame extends Activity_Base {
    private Button BTN_newGame;
    private Button BTN_Records;
    private Button BTN_Setting;
    private String name1 = null;
    private String name2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        findByView();
        if (ActivityCompat.checkSelfPermission(Activity_StartGame.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constans.ACCESS);
        }
        BTN_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_StartGame.this, Activity_Setting.class);
                startActivity(intent);
            }
        });
        BTN_Records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_StartGame.this, Activity_TopTen.class);
                startActivity(intent);
            }
        });
        BTN_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_StartGame.this, Activity_Game.class);
                startActivity(intent);
            }
        });
    }

    private void findByView() {
        BTN_newGame = findViewById(R.id.BTN_newGame);
        BTN_Records = findViewById(R.id.BTN_Records);
        BTN_Setting = findViewById(R.id.BTN_Setting);
    }
}
