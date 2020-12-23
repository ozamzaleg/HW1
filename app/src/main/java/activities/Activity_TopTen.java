package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.oz_tal_application_project.R;

import callbacks.CallBack_Top;
import fragment.fragment_List;
import fragment.fragment_Map;

public class Activity_TopTen extends AppCompatActivity {
    private fragment_List fragment_list;
    private fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__top_ten);

        fragment_list = new fragment_List();
        fragment_list.setCallBack_top(callback_Top);
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_list, fragment_list).commit();

        fragment_map = new fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_map, fragment_map).commit();
    }


    private CallBack_Top callback_Top = new CallBack_Top() {
        @Override
        public void addMarkerToMap(double lat, double lon) {
            fragment_map.addMarkerToMap(lat, lon);
        }
    };
}