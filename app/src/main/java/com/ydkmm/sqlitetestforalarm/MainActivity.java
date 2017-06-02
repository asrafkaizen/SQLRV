package com.ydkmm.sqlitetestforalarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_name, tv_num;
    EditText ed_name, ed_num;
    RecyclerView.Adapter mAdapter;
    List<AlarmPOJO> alarmPOJOs;
    Button btnAdd;
    int i;
    String dets = "dets ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rv);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_num = (TextView) findViewById(R.id.tv_num);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_num = (EditText) findViewById(R.id.ed_num);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        alarmPOJOs = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new AlarmAdapter(getApplicationContext(), alarmPOJOs);
        rv.setAdapter(mAdapter);

        for (i = 0; i < 5; i++) {
            AlarmPOJO ap = new AlarmPOJO(i, System.currentTimeMillis(), dets);
            alarmPOJOs.add(ap);
            dets += "dets ";
        }

    }
    public static boolean saveArray(Context context, List<Long> arr)    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    /* sKey is an array */
        editor.putInt("arr_size", arr.size());

        for(int i=0;i<arr.size();i++)
        {
            editor.putLong("alarm_" + i, arr.get(i));
        }
        return editor.commit();
    }

    public static List<Long> loadArray(Context context)
    {
        List<Long> longList = new
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("arr_size", 0);

        for(int i=0;i<size;i++)
        {
            arr.add(sharedPreferences.getLong("alarm_" + i, new Long(System.currentTimeMillis())));
        }
    }

    public static String getArray(int id){
        arr.
    }
}
