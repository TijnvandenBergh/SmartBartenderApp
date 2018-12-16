package com.example.tijn.bartenderapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PumpAdapter extends ArrayAdapter<Pump> {
    private Activity activity;
    private ArrayList<Pump> pumpArrayList;
    private static LayoutInflater inflater = null;

    public PumpAdapter(Activity activity, int textViewResourceId, ArrayList<Pump> _pumpArrayList) {
        super(activity, textViewResourceId, _pumpArrayList);
        try {
            this.activity = activity;
            this.pumpArrayList = _pumpArrayList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception ex) {

        }
    }

    @Override
    public int getCount() {
        return pumpArrayList.size();
    }

    public Pump getItem(Pump position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.row, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.PumpName);
                holder.display_number = (TextView) vi.findViewById(R.id.DrinkName);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }
            holder.display_name.setText(pumpArrayList.get(position).name);
            holder.display_number.setText(pumpArrayList.get(position).value);

        } catch (Exception e) {
        }
        return vi;
    }
}
