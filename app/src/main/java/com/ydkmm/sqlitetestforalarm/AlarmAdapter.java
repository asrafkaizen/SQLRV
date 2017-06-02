package com.ydkmm.sqlitetestforalarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asrafkaizen on 6/1/2017.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private static final String TAG = "adapter";
    Context context;
    List<AlarmPOJO> alarmPOJOList;
    int i = 0;

    public AlarmAdapter(Context contex, List<AlarmPOJO> list) {
        alarmPOJOList = list;
        context = contex;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmAdapter.AlarmViewHolder holder, int position) {
        AlarmPOJO current = alarmPOJOList.get(position);

        holder.tv_num.setText(Integer.toString(current.getId()));
        holder.tv_name.setText(Long.toString(current.getTimeLong()));
        holder.tv_details.setText(current.getDetails());
        holder.itemView.setTag(position);
        Log.e(TAG, holder.tv_name.getText().toString().substring(0,5));
    }

    @Override
    public int getItemCount() {
        return alarmPOJOList.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_num, tv_details;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_details = (TextView) itemView.findViewById(R.id.tv_details);

            tv_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "clicked");
                    tv_details.setText("I Have Been Clicked!");
                }
            });
        }
    }

}
