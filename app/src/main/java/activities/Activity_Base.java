package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.example.oz_tal_application_project.utils.MySP;
import com.example.oz_tal_application_project.utils.MyScreenUtils;

import com.example.oz_tal_application_project.R;

public class Activity_Base extends AppCompatActivity {
    protected boolean isDoubleBackPressToClose = false;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected MediaPlayer mp;

    protected void playSound(int rawSound) {
        if (!MySP.getInstance().getBoolean(MySP.KEYS.SWITCH, true)) {
            return;
        }
        if (mp != null) {
            try {
                mp.reset();
                mp.release();
            } catch (Exception ex) {
            }
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            MyScreenUtils.hideSystemUI2(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (isDoubleBackPressToClose) {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(this, "Tap back button again to exit", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}

