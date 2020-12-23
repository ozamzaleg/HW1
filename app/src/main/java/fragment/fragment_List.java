package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oz_tal_application_project.R;
import com.example.oz_tal_application_project.utils.MySP;
import com.google.gson.Gson;

import callbacks.CallBack_Top;
import objects.Record;
import objects.TopTen;

public class fragment_List extends Fragment {
    private ListView list;
    private ArrayAdapter<Record> adapter;
    private CallBack_Top callBack_top;

    public void setCallBack_top(CallBack_Top _callBack_top) {
        this.callBack_top = _callBack_top;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        initViews();
        displayList();
        return view;
    }

    private void displayList() {
        String topRecords = MySP.getInstance().getString(MySP.KEYS.KEY_TOP_TEN, "");
        TopTen topten;
        if (topRecords.isEmpty())
            topten = new TopTen();
        else
            topten = new Gson().fromJson(topRecords, TopTen.class);
        adapter = new ArrayAdapter<Record>(getContext(), android.R.layout.simple_list_item_1, topten.getTopTenPlayer());
        list.setAdapter(adapter);
    }

    private void findViews(View view) {
        list = view.findViewById(R.id.list_LST);
    }

    private void initViews() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                Record record = (Record) parent.getItemAtPosition(position);
                if (callBack_top != null)
                    callBack_top.addMarkerToMap(record.getLatitude(), record.getLongitude());
            }
        });
    }
}
