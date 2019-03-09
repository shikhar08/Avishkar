package com.example.srishtishikhar.appointmentmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class homepageCustomAdapter extends ArrayAdapter<Appointments>{
    Context context;
    ArrayList<Appointments> objects;
    float c,d;

    public homepageCustomAdapter(@NonNull Context context, ArrayList<Appointments>objects) {

            super(context, 0, objects);
            this.context=context;
            this.objects=objects;

        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.doctorcustomlayout, parent, false);
         }
        Appointments appt = getItem(position);
        String pname = appt.PatientName;

        int hrs = appt.Hour;
        int mins = appt.Minute;

        TextView textView = (TextView) view.findViewById(R.id.homepage_patname);
        textView.setText(pname);

        TextView textView1 = (TextView) view.findViewById(R.id.homepage_hrs);
        textView1.setText(hrs+"");

        TextView textView2 = (TextView) view.findViewById(R.id.homepage_mins);
        textView2.setText(mins+"");

        return view;
    }
}
