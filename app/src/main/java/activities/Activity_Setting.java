package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.oz_tal_application_project.R;
import com.example.oz_tal_application_project.utils.Constans;
import com.example.oz_tal_application_project.utils.MySP;

public class Activity_Setting extends AppCompatActivity {
    private EditText name_Number1;
    private EditText name_Number2;
    private String name1;
    private String name2;
    private Button btn_Apply;
    private Switch sw_Switch;
    private Boolean switchResult = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findByView();
        btn_Apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setting();

            }
        });
        setModeSwitch();
    }

    private void setModeSwitch() {
        sw_Switch.setChecked(MySP.getInstance().getBoolean(MySP.KEYS.SWITCH, true));
    }

    private void setting() {
        Intent intent = new Intent(Activity_Setting.this, Activity_StartGame.class);
        if (name_Number1.getText().toString().length() > 10 || name_Number2.getText().toString().length() > 10) {
            Toast.makeText(Activity_Setting.this, Constans.MSG, Toast.LENGTH_SHORT).show();
        } else {
            switchResult = sw_Switch.isChecked();
            MySP.getInstance().putBoolean(MySP.KEYS.SWITCH, switchResult);
            if (name_Number1.getText().toString().isEmpty() || name_Number1.getText().toString().trim().length() == 0) {
                MySP.getInstance().putString(MySP.KEYS.NAME1, Constans.PLAYER1);
            } else {
                name1 = name_Number1.getText().toString();
                MySP.getInstance().putString(MySP.KEYS.NAME1, name1);
            }
            if (name_Number2.getText().toString().isEmpty() || name_Number2.getText().toString().trim().length() == 0) {
                MySP.getInstance().putString(MySP.KEYS.NAME2, Constans.PLAYER2);
            } else {
                name2 = name_Number2.getText().toString();
                MySP.getInstance().putString(MySP.KEYS.NAME2, name2);
            }
            startActivity(intent);
            finish();
        }
    }

    private void findByView() {
        name_Number1 = findViewById(R.id.EDT_Player_Number1);
        name_Number2 = findViewById(R.id.EDT_Player_Number2);
        btn_Apply = findViewById(R.id.BTN_APPLY);
        sw_Switch = findViewById(R.id.SW_switch);
    }
}