package com.example.vaan.salazapee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    List<String> list = new ArrayList<>();
    ItemAdapter itemAdapter;
    ListView listView;
    List<String> paylists = new ArrayList<>();
    List<Integer> paylistsnum = new ArrayList<>();
    List<Integer> costperproduct = new ArrayList<>();
    List<Integer> paylistscost = new ArrayList<>();
    int cartsize = 0;
    Intent intent ;
    private static int sumnum  = 0;
    private static int sumcost = 0;
    TextView sumprice ;
    TextView totalinCart ;
    private static int cost = 0;
    private static int num  = 0;
    private static String productsName;

    ItemDatabase itemDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity);
        try {
            intent = getIntent();
            String pay = intent.getStringExtra("pay");
            String home = intent.getStringExtra("fromhome");
            if(!TextUtils.isEmpty(pay)||!TextUtils.isEmpty(home)){
                sumcost=0;
                sumnum=0;
                totalinCart.setText("Total in Cart: "+String.format("%,d", sumnum)+" Piece");
                sumprice.setText("Total Price: "+String.format("%,d", sumcost)+" Baht");
                Toast.makeText(getApplicationContext(), pay, Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Let's shopping!", Toast.LENGTH_LONG).show();
        }



        final List<Item> list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        list.add(new Item("Food","Red Apple",10000,20,"Yummy and beautiful!"));
        list.add(new Item("Food","Kiwi",10000,30,"Good taste!"));
        list.add(new Item("Kitchenware","Gorea Queen Pan",10000,999,"Expansive pan will make your good dish!"));
        list.add(new Item("Gadget","Iphone S9+",10000,27999,"Dual Sims!"));
        list.add(new Item("Toy","Barbie",10000,399,"Little doll is best friends!"));
        list.add(new Item("Pet","Cats",10000,5999,"Sweets cat!"));

        sumprice = (TextView) findViewById(R.id.textViewSumprice);
        totalinCart = (TextView) findViewById(R.id.textViewtotoalinCart);
        totalinCart.setText("Total in Cart: "+String.format("%,d", sumnum)+" Piece");
        sumprice.setText("Total Price: "+String.format("%,d", sumcost)+" Baht");
        itemAdapter = new ItemAdapter(this,0,list);
        try {
            listView.setAdapter(itemAdapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Adapter Error!", Toast.LENGTH_SHORT).show();
        }
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final View dlgs = getLayoutInflater().inflate(R.layout.buy_dialog,null);
                final AlertDialog.Builder b = new AlertDialog.Builder(StoreActivity.this);
                final EditText editBuy = (EditText) dlgs.findViewById(R.id.editTextBuy);
                final Item p = (Item) itemAdapter.getItem(position);
                b.setView(dlgs);
                b.setMessage("  "+p.getName());
                b.setPositiveButton("BUY IT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sumnum  += Integer.parseInt(editBuy.getText().toString());
                        sumcost += p.getCost()*Integer.parseInt(editBuy.getText().toString());
                        paylists.add(p.getName());
                        costperproduct.add(p.getCost());
                        paylistsnum.add(Integer.parseInt(editBuy.getText().toString()));
                        paylistscost.add(p.getCost()*Integer.parseInt(editBuy.getText().toString()));
                        cartsize+=1;

                        sumprice.setText("Total Price: "+ String.format("%,d", sumcost)+" Baht");
                        totalinCart.setText("Total in Cart: "+String.format("%,d", sumnum)+" Piece");
                    }
                });
                b.create();
                b.show();
                return false;
            }
        });

    }
    public void checkOutBtn(View view){
        if (cartsize != 0){
            Intent intent = new Intent(this,CheckoutActivity.class);
            intent.putExtra("sumnum", sumnum+"");
            intent.putExtra("sumcost", sumcost+"");
            intent.putExtra("cartsize",cartsize+"");
            intent.putStringArrayListExtra("arraylistaynum", (ArrayList<String>) paylists);
            intent.putIntegerArrayListExtra ("arraylistcostperproduct", (ArrayList<Integer>) costperproduct);
            intent.putIntegerArrayListExtra ("arraylistsum", (ArrayList<Integer>) paylistsnum);
            intent.putIntegerArrayListExtra ("arraylistcost", (ArrayList<Integer>) paylistscost);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Cart is Empty!", Toast.LENGTH_LONG).show();

        }

    }
    public void backBtn(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("reset", "s");
        startActivity(intent);
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public static int getSumnum() {
        return sumnum;
    }

    public static void setSumnum(int sumnum) {
        StoreActivity.sumnum = sumnum;
    }

    public static int getSumcost() {
        return sumcost;
    }

    public static void setSumcost(int sumcost) {
        StoreActivity.sumcost = sumcost;
    }
}
