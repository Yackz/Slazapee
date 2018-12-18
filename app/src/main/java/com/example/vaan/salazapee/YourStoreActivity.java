package com.example.vaan.salazapee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class YourStoreActivity extends AppCompatActivity {
    YourStoreAdapter yourItemAdapter;
    ListView yourItemlistview;
    static List<Item> list ;
    ArrayList<String> types = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> costs = new ArrayList<>();
    ArrayList<String> descs = new ArrayList<>();
    int yourstoresize;
    Intent intent;
    Item items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yourstore_activity);
        list = new ArrayList<>();
        yourItemlistview = (ListView) findViewById(R.id.yourlistview);
        intent = getIntent();
        try{
            ArrayList<String> arrayListtype = intent.getStringArrayListExtra("yourstoretype");
            ArrayList<String> arraylistname = intent.getStringArrayListExtra ("yourstorename");
            ArrayList<Integer> arraylistcost = intent.getIntegerArrayListExtra ("yourstoreprice");
            ArrayList<String> arraylistcostperproduct = intent.getStringArrayListExtra ("yourstoredesc");
//            String si = intent.getStringExtra("yoursize");
//            yourstoresize = Integer.parseInt(si);
            types = new ArrayList<>(arrayListtype);
            names = new ArrayList<>(arraylistname);
            costs = new ArrayList<>(arraylistcost);
            descs = new ArrayList<>(arraylistcostperproduct);
            yourstoresize = types.size();
            for (int i=0 ; i<yourstoresize ; i++){
                list.add(new Item(arrayListtype.get(i) ,arraylistname.get(i), arraylistcost.get(i) ,arraylistcostperproduct.get(i)));
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), types+"", Toast.LENGTH_LONG).show();

        }




//        list.add(new Item("FRUIT","Red Apple",10000,20,"Yummy and beautiful!"));
        yourItemAdapter = new YourStoreAdapter(this,0,list);
        try {
            yourItemlistview.setAdapter(yourItemAdapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Adapter Error!", Toast.LENGTH_SHORT).show();
        }
        yourItemlistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final View dlgs = getLayoutInflater().inflate(R.layout.add_dialog,null);
                AlertDialog.Builder b = new AlertDialog.Builder(YourStoreActivity.this);
                items = (Item) yourItemAdapter.getItem(position);
                final EditText editType = (EditText) dlgs.findViewById(R.id.editTextType);
                final EditText editName = (EditText) dlgs.findViewById(R.id.editTextName);
                final EditText editPrice = (EditText) dlgs.findViewById(R.id.editTextPrice);
                final EditText editdesc = (EditText) dlgs.findViewById(R.id.editTextdescribtion);
                editType.setText(items.getType()+"");
                editName.setText(items.getName()+"");
                editPrice.setText(items.getCost()+"");
                editdesc.setText(items.getDescription()+"");
                b.setView(dlgs);
                b.setMessage("Edit Product");
                b.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String type = editType.getText()+"";
                        String name = editName.getText()+"";
                        String price = editPrice.getText()+"";
                        String desc = editdesc.getText()+"";

                        if(!TextUtils.isEmpty(type) && !TextUtils.isEmpty(price)&& !TextUtils.isEmpty(desc)&& !TextUtils.isEmpty(name)){
                            list.set(position, new Item(type,name,0, Integer.parseInt(price),desc));
                            types.set(position,type+"");
                            names.set(position,name+"");
                            costs.set(position,Integer.parseInt(price));
                            descs.set(position,desc);
                            yourItemlistview.setAdapter(yourItemAdapter);
                        }else{
                            Toast.makeText(getApplicationContext(), "Pls fill more info", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "DELETE: "+items.getName(), Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        types.remove(position);
                        names.remove(position);
                        costs.remove(position);
                        descs.remove(position);
                        yourItemlistview.setAdapter(yourItemAdapter);
                        yourItemlistview.setAdapter(yourItemAdapter);

                    }
                });
                b.create();
                b.show();

                    return false;
            }
        });

    }
    public void addYourProductBtn(View view){
        final View dlgs = getLayoutInflater().inflate(R.layout.add_dialog,null);
        AlertDialog.Builder b = new AlertDialog.Builder(YourStoreActivity.this);
        b.setView(dlgs);
        b.setMessage("ADD PRODUCT");
        b.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editType = (EditText) dlgs.findViewById(R.id.editTextType);
                EditText editName = (EditText) dlgs.findViewById(R.id.editTextName);
                EditText editPrice = (EditText) dlgs.findViewById(R.id.editTextPrice);
                EditText editdesc = (EditText) dlgs.findViewById(R.id.editTextdescribtion);
                String type = editType.getText().toString();
                String name = editName.getText().toString();
                String price = editPrice.getText().toString();
                String desc = editdesc.getText().toString();
                String typess = type+"";
                String namess = name+"";
                String pricess = price+"";
                String descss = desc+"";

                if(!TextUtils.isEmpty(type) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(name)){
                    try{
                        items = new Item(typess,namess,0, Integer.parseInt(pricess),descss);
                        yourItemAdapter.add(items);
                        types.add(items.getType()+"");
                        names.add(items.getName()+"");
                        costs.add(items.getCost());
                        descs.add(items.getDescription()+"");
                        yourItemAdapter.notifyDataSetChanged();
                    }
                    catch (Exception e){

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Pls fill more info", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b.create();
        b.show();
    }
    public void BackfromYoureStoreBtn(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putStringArrayListExtra("homearraytype", (ArrayList<String>) types);
        intent.putStringArrayListExtra("homearrayname", (ArrayList<String>) names);
        intent.putIntegerArrayListExtra("homearraycost", (ArrayList<Integer>) costs);
        intent.putStringArrayListExtra("homearraydesc", (ArrayList<String>) descs);
        intent.putExtra("sizestring",yourstoresize);
        startActivity(intent);
    }

}
