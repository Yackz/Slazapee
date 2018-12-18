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

public class ItemAdapter extends ArrayAdapter {
    List<Integer> listimageId = new ArrayList<>();

    public ItemAdapter(Context context, int resource,List objects) {
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
            listimageId.add(R.drawable.pexelsappple);
            listimageId.add(R.drawable.kiwi);
            listimageId.add(R.drawable.pan);
            listimageId.add(R.drawable.phone);
            listimageId.add(R.drawable.babiesdoll);
            listimageId.add(R.drawable.cat);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_row,null);
            Item item = (Item) getItem(position);
            TextView textView = (TextView) convertView.findViewById(R.id.textViewType);
            textView.setText("  Type: "+item.getType()+"");
            TextView textView2 = (TextView) convertView.findViewById(R.id.textViewName);
            textView2.setText("  Product Name: "+item.getName()+"");
//            TextView textView3 = (TextView) convertView.findViewById(R.id.textViewQuantity);
//            textView3.setText(item.getQuantity()+"");
            TextView textView4 = (TextView) convertView.findViewById(R.id.textViewCost);
            textView4.setText("  Price: "+String.format("%,d", item.getCost()) +" Bath");
            TextView textView5 = (TextView) convertView.findViewById(R.id.textViewDescription);
            textView5.setText("  Details: "+item.getDescription()+"");
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            imageView.setImageResource(listimageId.get(position));
        }
        return convertView;
    }
}
