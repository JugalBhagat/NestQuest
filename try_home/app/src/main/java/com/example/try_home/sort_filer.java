package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class sort_filer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_filer);
        Intent i=getIntent();
        String state=i.getStringExtra("search_state");
        String city=i.getStringExtra("search_city");
        String area=i.getStringExtra("search_area");
        String what=i.getStringExtra("search_what");

/*
        Intent i = new Intent(getApplicationContext(), search_view.class);
        i.putExtra("search_state", state);
        i.putExtra("search_city", city);
        i.putExtra("search_area", area);
        i.putExtra("search_what", what);
        SqlOp x = new SqlOp(getApplicationContext());
        startActivity(i);*/
    }
}