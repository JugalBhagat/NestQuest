package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Show_data extends AppCompatActivity {
    Button user;
    Button owner;
    Button user_del;
    Button owner_del;
    Button sell,rent,del_rent,del_sell;
    ListView lv;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        user=findViewById(R.id.btn_user);
        owner=findViewById(R.id.btn_owner);
        user_del=findViewById(R.id.btn_user_del);
        owner_del=findViewById(R.id.btn_owner_del);
        sell=findViewById(R.id.btn_sell);
        rent=findViewById(R.id.btn_rent);
        del_sell=findViewById(R.id.btn_sell_del);
        del_rent=findViewById(R.id.btn_rent_del);

        lv=findViewById(R.id.lv);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqlOp db=new SqlOp(Show_data.this);
                List<String> l =db.get_data();
                ArrayAdapter adp=new ArrayAdapter(Show_data.this,android.R.layout.simple_expandable_list_item_1,l);
                lv.setAdapter(adp);
            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqlOp db=new SqlOp(Show_data.this);
                List<String> l =db.get_data_owner();
                ArrayAdapter adp=new ArrayAdapter(Show_data.this,android.R.layout.simple_expandable_list_item_1,l);
                lv.setAdapter(adp);
            }
        });
        user_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlOp db=new SqlOp(getApplicationContext());
                db.del_ten();
                List<String> l =db.get_data();
                ArrayAdapter adp=new ArrayAdapter(Show_data.this,android.R.layout.simple_expandable_list_item_1,l);
                lv.setAdapter(adp);
            }
        });
        owner_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlOp db=new SqlOp(getApplicationContext());
                db.del_owner();
                List<String> l =db.get_data_owner();
                ArrayAdapter adp=new ArrayAdapter(Show_data.this,android.R.layout.simple_expandable_list_item_1,l);
                lv.setAdapter(adp);
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlOp db=new SqlOp(Show_data.this);
                List<String> l =db.view_sell_prop(null,null,null);
                ArrayAdapter adp=new ArrayAdapter(Show_data.this,android.R.layout.simple_expandable_list_item_1,l);
                lv.setAdapter(adp);
            }
        });
        del_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlOp db=new SqlOp(Show_data.this);
                List<String> l =db.view_rent_prop(null,null,null);
                ArrayAdapter adp=new ArrayAdapter(Show_data.this,android.R.layout.simple_expandable_list_item_1,l);
                lv.setAdapter(adp);
            }
        });
        del_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}