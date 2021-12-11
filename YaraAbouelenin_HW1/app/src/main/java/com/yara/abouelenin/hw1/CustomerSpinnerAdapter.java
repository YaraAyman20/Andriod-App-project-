package com.yara.abouelenin.hw1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class CustomerSpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> spinnerItemValues;
    int [] foodimgIds = null;

    public CustomerSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<String> spinnerItems, int [] foodimgIds) {
        super(context, R.layout.food_spinner, spinnerItems);
        this.context = context;
        spinnerItemValues = spinnerItems;
        this.foodimgIds = foodimgIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView (int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.food_spinner, parent, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.itemConstraintLayout);
        ImageView imgItem = view.findViewById(R.id.imgItem);
        TextView tvItem = view.findViewById(R.id.tvItem);

        tvItem.setText(spinnerItemValues.get(position));
        imgItem.setImageResource(foodimgIds[position]);


        return view;

    }

}
