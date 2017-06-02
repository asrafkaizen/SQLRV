package com.ydkmm.sqlitetestforalarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_name, tv_num;
    EditText ed_name, ed_num;
    RecyclerView.Adapter mAdapter;
    List<AlarmPOJO> alarmPOJOs;
    Button btnAdd;
    String dets = "dets ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_num = (TextView) findViewById(R.id.tv_num);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_num = (EditText) findViewById(R.id.ed_num);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        alarmPOJOs = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new AlarmAdapter(getApplicationContext(), alarmPOJOs);
        rv.setAdapter(mAdapter);

        for (int i = 0; i < 5; i++) {
            AlarmPOJO ap = new AlarmPOJO(i, System.currentTimeMillis(), dets);
            alarmPOJOs.add(ap);
            dets += "dets ";
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmPOJO ap = new AlarmPOJO(alarmPOJOs.size() + 1, System.currentTimeMillis(), ed_name.getText().toString());
                alarmPOJOs.add(ap);
            }
        });

    }

    public static boolean saveArray(Context context, List<Long> arr) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    /* sKey is an array */
        editor.putInt("arr_size", arr.size());

        for (int i = 0; i < arr.size(); i++) {
            editor.putLong("alarm_" + i, arr.get(i));
        }
        return editor.commit();
    }

    public static boolean loadAlarm(Context context, int time) {
        boolean success = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        for (int i = 0; i < 10; i++) {
            if (0 == sharedPreferences.getInt("alarm_" + i, 0)) {
                if (time != sharedPreferences.getInt("alarm_" + i, 0)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("alarm_" + i, time);
                    editor.commit();
                    success = true;
                    i = 10; //break from loop
                }
            }
        }
        return success;
    }

    public static int unloadNearestAlarm(Context context) {
        //5000 is the same as "not_set"
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        int smallest = 2359;
        for (int i = 0; i < 10; i++) {
            int gotTime = sharedPreferences.getInt("alarm_" + i, 5000);
            Date d = new Date(System.currentTimeMillis());
            int currentTime = (d.getHours() * 100) + d.getMinutes();
            if (gotTime <= currentTime && gotTime != 5000) {
                gotTime += 2400;
            }
            if (5000 != gotTime) {
                if (gotTime < smallest) {
                    smallest = sharedPreferences.getInt("alarm_" + i, 0);
                }
            }
        }
        return smallest;
    }

    public static boolean deleteAlarm(Context context, int time){
        boolean success = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        for (int i = 0; i < 10; i++){
            if (time == sharedPreferences.getInt("alarm_" + i, 5000)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("alarm_" + i, 5000);
                editor.commit();
                success = true;
                i = 10;
                //todo add delete alarm
            }
        }
        return success;
    }

    public static int longToIntTime(long l){
        Date d = new Date(l);
        return ((d.getHours() * 100) + d.getMinutes());
    }
}


