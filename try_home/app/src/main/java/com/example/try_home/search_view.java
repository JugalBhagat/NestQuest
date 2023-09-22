package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.try_home.adapter.desc_rcv_tenent_rent;
import com.example.try_home.adapter.desc_rcv_tenent_sell;
import com.example.try_home.adapter.rcv_tenent_rent_prop;
import com.example.try_home.adapter.recycler_view_adapter;

import java.util.ArrayList;
import java.util.List;

public class search_view extends AppCompatActivity {
    Spinner sp_sort;
    ImageView iv;
    Button btn_filter;
    private RecyclerView rv;
    private recycler_view_adapter rvas;
    private rcv_tenent_rent_prop rvar;
    private desc_rcv_tenent_rent drvcr;
    private desc_rcv_tenent_sell drvcs;
    private ArrayAdapter<String> ad;
    private ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        SqlOp obj=new SqlOp(getApplicationContext());
      //  btn_filter=findViewById(R.id.btn_filter);
        sp_sort=findViewById(R.id.spinner);
        rv=findViewById(R.id.recyclerView);
        List<String> list = new ArrayList<>();

        SqlOp x = new SqlOp(getApplicationContext());
       // x.del_acc_owner();
        Intent i=getIntent();
        String what=i.getStringExtra("search_what");
        String state=i.getStringExtra("search_state");
        String city=i.getStringExtra("search_city");
        String area=i.getStringExtra("search_area");

        if(what.equals("Sell")) {
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rvas = new recycler_view_adapter(getApplicationContext(),list,state,city,area);
            rv.setAdapter(rvas);
        }
        else if(what.equals("Rent"))
        {
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rvar = new rcv_tenent_rent_prop(getApplicationContext(),list,state,city,area);
            rv.setAdapter(rvar);
        }

        sp_sort.setTag("SORTING");

        String s[]={"Price : Low to high","Price : High to Low"};
        ArrayAdapter ad=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,s);
        sp_sort.setAdapter(ad);

        sp_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                if(sp_sort.getSelectedItem().toString()=="Price : High to Low")
                {
                    if(what.equals("Sell")) {
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        drvcs = new desc_rcv_tenent_sell(getApplicationContext(),list,state,city,area);
                        rv.setAdapter(drvcs);
                    }
                    else if(what.equals("Rent"))
                    {
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        drvcr = new desc_rcv_tenent_rent(getApplicationContext(),list,state,city,area);
                        rv.setAdapter(drvcr);
                    }
                    Toast.makeText(search_view.this, "High to Low", Toast.LENGTH_SHORT).show();

                } else if (sp_sort.getSelectedItem().toString()=="Price : Low to high") {

                    if(what.equals("Sell")) {
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rvas = new recycler_view_adapter(getApplicationContext(),list,state,city,area);
                        rv.setAdapter(rvas);
                    }
                    else if(what.equals("Rent"))
                    {
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rvar = new rcv_tenent_rent_prop(getApplicationContext(),list,state,city,area);
                        rv.setAdapter(rvar);
                    }
                    Toast.makeText(search_view.this, "low to high", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), sort_filer.class);
                i.putExtra("search_state", state);
                i.putExtra("search_city", city);
                i.putExtra("search_area", area);
                i.putExtra("search_what", what);
                SqlOp x = new SqlOp(getApplicationContext());
                startActivity(i);
            }
        });*/
    }
}