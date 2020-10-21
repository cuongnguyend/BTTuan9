package com.example.bttuan9;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<food> food_data;
    LayoutInflater layoutInflater;
    food foods;

    public CustomAdapter(Context context, ArrayList<food> foods) {
        this.context = context;
        this.food_data = foods;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return food_data.size();
    }

    @Override
    public Object getItem(int i) {
        return food_data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return food_data.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = view;
        if (rowView==null) {
            rowView = layoutInflater.inflate(R.layout.row_food, null, true);
        }
        //link views
        ImageView imageView = rowView.findViewById(R.id.image);
        TextView name = rowView.findViewById(R.id.name);
        TextView saleAmount = rowView.findViewById(R.id.saledAmount);
        TextView price = rowView.findViewById(R.id.price);
        Button add = rowView.findViewById(R.id.add);

        foods = food_data.get(position);

        imageView.setImageResource(foods.getImg());
        name.setText(foods.getName());
        saleAmount.setText("Amount: " + foods.getAmount());
        price.setText("Price: "+foods.getPrice()+" vnđ");
        return rowView;
    }
    public void removeItems(ArrayList<food> items){
        for(food item : items){
            food_data.remove(item);
        }
        notifyDataSetChanged();
    }
}
