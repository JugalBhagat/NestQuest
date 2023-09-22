package com.example.try_home.Frags;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.try_home.Post_prop_form;
import com.example.try_home.R;
import com.example.try_home.adapter.rcv_owner_rent_prop;
import com.example.try_home.adapter.rcv_owner_sell_prop;

public class Frag_Dashboard extends Fragment {
    private RecyclerView rcv;
    private rcv_owner_sell_prop rvas;
    private rcv_owner_rent_prop rvar;
    private Object obj;

    RadioGroup rg;
    RadioButton rb_sell,rb_rent;
    View fa;
    ImageButton ib_refresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //t1.findViewById(0);

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frag__dashboard, container, false);
        fa=v.findViewById(R.id.fa);
        rcv=v.findViewById(R.id.recyclerView);
        rb_rent=v.findViewById(R.id.rb_rent);
        rb_sell=v.findViewById(R.id.rb_sell);
        ib_refresh=v.findViewById(R.id.ib_refresh);


        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        rb_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvas = new rcv_owner_sell_prop(getContext());
                rcv.setAdapter(rvas);
                obj=rvas;
            }
        });
        rb_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvar = new rcv_owner_rent_prop(getContext());
                rcv.setAdapter(rvar);
                obj=rvar;
            }
        });
        ib_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcv.setAdapter((RecyclerView.Adapter) obj);
            }
        });

        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), Post_prop_form.class));
                //Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}