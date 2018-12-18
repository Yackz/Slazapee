package com.example.vaan.salazapee;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    List<Payment> list = new ArrayList<Payment>();
    PaylistAdapter paylistAdapter;
    ListView listView;
    Intent intent ;
    String sumnum ;
    String sumcost ;
    int num ;
    int cost;
    String cartsize ;
    int cart ;
    TextView sumpricecheckout ;
    TextView totalinCartcheckout ;
    boolean state = false;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        intent = getIntent();
        sumnum = intent.getStringExtra("sumnum");
        sumcost = intent.getStringExtra("sumcost");
        num = Integer.parseInt(sumnum);
        cost = Integer.parseInt(sumcost);
        cartsize = intent.getStringExtra("cartsize");
        cart = Integer.parseInt(cartsize);
        btn = (Button) findViewById(R.id.checkoutBtn);


        ArrayList<String> arrayList = intent.getStringArrayListExtra("arraylistaynum");
        ArrayList<Integer> arraylistsum = intent.getIntegerArrayListExtra ("arraylistsum");
        ArrayList<Integer> arraylistcost = intent.getIntegerArrayListExtra ("arraylistcost");
        ArrayList<Integer> arraylistcostperproduct = intent.getIntegerArrayListExtra ("arraylistcostperproduct");


        listView = (ListView) findViewById(R.id.checkoutlist);
        for (int i=0 ; i<cart ; i++){
            list.add(new Payment(arrayList.get(i) ,arraylistcostperproduct.get(i), arraylistsum.get(i) ,arraylistcost.get(i)));

        }
//        list.add(new Payment(arrayList.get(0) , arraylistsum.get(0) ,arraylistcost.get(0)));
//        list.add(new Payment(arrayList.get(1) , arraylistsum.get(1) ,arraylistcost.get(1)));
        paylistAdapter = new PaylistAdapter(this,0,list);
        try {
            listView.setAdapter(paylistAdapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Adapter Error!", Toast.LENGTH_SHORT).show();
        }



        sumpricecheckout = (TextView) findViewById(R.id.pricecheckout);
        totalinCartcheckout = (TextView) findViewById(R.id.cartCheckout);
        totalinCartcheckout.setText("Total in Cart: "+String.format("%,d", num) +" Piece");
        sumpricecheckout.setText("Total Price: "+ String.format("%,d", cost) +" Baht");


    }
    public void payBtn(View view){
        if (state==false){
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(CheckoutActivity.this);
            builder.setMessage("Pay for it!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    Toast.makeText(getApplicationContext(),
                            "Thanks", Toast.LENGTH_SHORT).show();
                    paylistAdapter.clear();
                    num = 0;
                    cost = 0;
                    totalinCartcheckout.setText("Total in Cart: "+String.format("%,d", num) +" Piece");
                    sumpricecheckout.setText("Total Price: "+ String.format("%,d", cost) +" Baht");
                    state = true;
                    btn.setText("Go back");

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dialog.dismiss();
                }
            });
            builder.show();

        }else{
            Intent intent = new Intent(this,StoreActivity.class);
            intent.putExtra("pay","S");
            startActivity(intent);

        }




    }
}
