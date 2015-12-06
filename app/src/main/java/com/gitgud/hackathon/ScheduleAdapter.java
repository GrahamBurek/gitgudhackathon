package com.gitgud.hackathon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitgud.hackathon.R;
import com.gitgud.hackathon.Schedule;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {

    Context context;
    int layoutResourceId;
    Schedule data[] = null;

    public ScheduleAdapter(Context context, int layoutResourceId, Schedule[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ScheduleHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ScheduleHolder();
            holder.eventName = (TextView) row.findViewById(R.id.schedule_row_name);
            holder.eventTime = (TextView) row.findViewById(R.id.schedule_row_time);

            row.setTag(holder);
        }
        else
        {
            holder = (ScheduleHolder)row.getTag();
        }

        Schedule Schedule = data[position];
        holder.eventName.setText(Schedule.name);
        holder.eventTime.setText(Schedule.time);

        return row;
    }

    static class ScheduleHolder
    {
        TextView eventName;
        TextView eventTime;
    }
}