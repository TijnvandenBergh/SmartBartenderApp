package com.example.tijn.bartenderapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class DrinksAdapter extends ArrayAdapter<Drink> {
    private Activity activity;
    private ArrayList<Drink> drinksArrayList;
    private static LayoutInflater inflater = null;

    public DrinksAdapter(Activity activity, int textViewResourceId, ArrayList<Drink> _drinksArrayList) {
        super(activity, textViewResourceId, _drinksArrayList);
        try {
            this.activity = activity;
            this.drinksArrayList = _drinksArrayList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception ex) {

        }
    }

    @Override
    public int getCount() {
        return drinksArrayList.size();
    }

    public Drink getItem(Drink position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView ingredient0;
        public TextView ingredient1;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final DrinksAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.drinksrow, null);
                holder = new DrinksAdapter.ViewHolder();
                holder.ingredient0 = (TextView) vi.findViewById(R.id.ingredientName0);
                holder.ingredient1 = (TextView) vi.findViewById(R.id.ingredientName1);


                vi.setTag(holder);
            } else {
                holder = (DrinksAdapter.ViewHolder) vi.getTag();
            }
            Drink d = drinksArrayList.get(position);
            holder.ingredient0.setText(d.getName());
            StringBuilder ingredients = new StringBuilder();
            for (Map.Entry<String, Integer> ingredient : d.getIngredients().entrySet()) {
                ingredients.append(" ");
                ingredients.append(ingredient.getKey());
                ingredients.append(", ");
                ingredients.append(Integer.toString(ingredient.getValue()));
                ingredients.append("ML \n");
            }
            holder.ingredient1.setText(ingredients.toString().trim());

        } catch (Exception e) {
        }
        return vi;
    }
}
