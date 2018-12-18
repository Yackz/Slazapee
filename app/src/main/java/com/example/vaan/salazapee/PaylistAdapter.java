package com.example.vaan.salazapee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class PaylistAdapter extends ArrayAdapter {
    List<Integer> listimageId = new ArrayList<>();

    public PaylistAdapter(Context context, int resource,List objects) {
        super(context, R.layout.item_row, objects);
    }
    @Override
    public void remove(Object object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.payment_row,null);
            Payment item = (Payment) getItem(position);
            TextView textView = (TextView) convertView.findViewById(R.id.paymentname);
            textView.setText("  Product Name: "+item.getName()+"");
            TextView textView2 = (TextView) convertView.findViewById(R.id.paymentcost);
            textView2.setText("  Price: "+ String.format("%,d", item.getCost()) +" Baht");
            TextView textView3 = (TextView) convertView.findViewById(R.id.paymentotal);
            textView3.setText("  Number of Buy: "+ String.format("%,d", item.getSumnum() ) +" Piece");
            TextView textView4 = (TextView) convertView.findViewById(R.id.paymentsum);
            textView4.setText("  Total Price: "+ String.format("%,d", item.getSumcost()) +" Bath");

        }
        return convertView;
    }
}
