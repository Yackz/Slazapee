package com.example.vaan.salazapee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> list = new ArrayList<>();
    Intent intent;
    ArrayList<String> types = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> costs = new ArrayList<>();
    ArrayList<String> descs = new ArrayList<>();
    int sizeint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        try {
            String reset = intent.getStringExtra("reset");
            if(!TextUtils.isEmpty(reset)){
                Toast.makeText(getApplicationContext(), "Cart is reset!", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
        }
        try {
            ArrayList<String> arrayListtype = intent.getStringArrayListExtra("homearraytype");
            ArrayList<String> arraylistname = intent.getStringArrayListExtra ("homearrayname");
            ArrayList<Integer> arraylistcost = intent.getIntegerArrayListExtra ("homearraycost");
            ArrayList<String> arraylistcostperproduct = intent.getStringArrayListExtra ("homearraydesc");
            types = new ArrayList<>(arrayListtype);
            names = new ArrayList<>(arraylistname);
            costs = new ArrayList<>(arraylistcost);
            descs = new ArrayList<>(arraylistcostperproduct);
        }catch (Exception e){

        }
    }
    public void gotostoreBtn(View view ){
        Intent intent = new Intent(this,StoreActivity.class);
        intent.putExtra("fromhome", "s");
        startActivity(intent);
    }
    public void editYoutstoreBtn(View view ){
        Intent intent = new Intent(this,YourStoreActivity.class);
        intent.putStringArrayListExtra("yourstoretype", (ArrayList<String>) types);
        intent.putStringArrayListExtra("yourstorename", (ArrayList<String>) names);
        intent.putIntegerArrayListExtra("yourstoreprice", (ArrayList<Integer>) costs);
        intent.putStringArrayListExtra("yourstoredesc", (ArrayList<String>) descs);
        intent.putExtra("yoursize", sizeint);
        startActivity(intent);
    }
}
