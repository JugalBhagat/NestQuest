package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post_prop_form extends AppCompatActivity {
    Button btn_next;
    RadioGroup rg_what,rg_furn;
    Spinner sp_state,sp_city;
    EditText ed_area,ed_address,ed_nearby;

    RadioButton rb_rent,rb_sell,rb_un,rb_full,rb_semi;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_prop_form);
         //getSupportActionBar().setDisplayShowTitleEnabled(true);
         //getSupportActionBar().setTitle("Sign up");
        sp_state=findViewById(R.id.spin_state);
        sp_city=findViewById(R.id.spin_city);
        ed_nearby=findViewById(R.id.ed_nearby);
        ed_area=findViewById(R.id.ed_area);
        ed_address=findViewById(R.id.ed_address);
        rg_what=findViewById(R.id.rg_what);
        rg_furn=findViewById(R.id.rg_furn_type);
        btn_next=findViewById(R.id.btn_next);
        rb_full=findViewById(R.id.rb_Furnished);
        rb_semi=findViewById(R.id.rb_semi_furn);
        rb_un=findViewById(R.id.rb_unfurn);
        rb_rent=findViewById(R.id.rb_rent);
        rb_sell=findViewById(R.id.rb_sell);

        String l_state[]={"Maharashtra","Gujarat","Karnataka","West-bengal","Tamilnadu","Delhi","Andhra-Pradesh"};
        ArrayAdapter state_ad=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,l_state);
        sp_state.setAdapter(state_ad);

        /*
        ArrayList<String> al_city=new ArrayList<String>();
        ArrayAdapter ad_city=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,al_city);
        sp_city.setAdapter(ad_city);*/

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Post_prop_form.this,sp_state.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                get_city_spinner();
             }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayList<String> al_city=new ArrayList<String>();
                ArrayAdapter ad_city=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,al_city);
                sp_city.setAdapter(ad_city);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate();
                if(b==true) {
                        //Toast.makeText(Post_prop_form.this, "Validation passed", Toast.LENGTH_SHORT).show();
                        int rg_what_id = rg_what.getCheckedRadioButtonId();
                        RadioButton rb_what = findViewById(rg_what_id);
                        //get();
                        int rg_furn_id = rg_furn.getCheckedRadioButtonId();
                        RadioButton rb_furn = findViewById(rg_furn_id);
                        if (rb_what.getText().toString().equals("Rent")) {
                            Intent i = new Intent(Post_prop_form.this, Post_prop_form_2B.class);

                            SharedPreferences sp = getSharedPreferences("sell_rent", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("what", rb_what.getText().toString());
                            ed.putString("rent_furn", rb_furn.getText().toString());
                            ed.putString("rent_state", sp_state.getSelectedItem().toString());
                            ed.putString("rent_city", sp_city.getSelectedItem().toString());
                            ed.putString("rent_area", ed_area.getText().toString());
                            ed.putString("rent_address", ed_address.getText().toString());
                            ed.putString("rent_nearby", ed_nearby.getText().toString());
                            ed.commit();

                            startActivity(i);
                        } else if (rb_what.getText().toString().equals("Sell")) {
                            Intent i = new Intent(Post_prop_form.this, Post_prop_form_2.class);

                            SharedPreferences sp = getSharedPreferences("sell_rent", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("what", rb_what.getText().toString());
                            ed.putString("sell_furn", rb_furn.getText().toString());
                            ed.putString("sell_state", sp_state.getSelectedItem().toString());
                            ed.putString("sell_city", sp_city.getSelectedItem().toString());
                            ed.putString("sell_area", ed_area.getText().toString());
                            ed.putString("sell_address", ed_address.getText().toString());
                            ed.putString("sell_nearby", ed_nearby.getText().toString());
                            ed.commit();
                            startActivity(i);
                        }
                }
                else
                    Toast.makeText(Post_prop_form.this, "Fill all data appropriately", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void get_city_spinner() {
        if(sp_state.getSelectedItem().toString().equals("Maharashtra"))
        {
            String l_city[]={"Mumbai","Pune"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_state.getSelectedItem().toString().equals("Gujarat"))
        {
            String l_city[]={"Ahemdabad"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_state.getSelectedItem().toString().equals("Karnataka"))
        {
            String l_city[]={"Bangalore"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_state.getSelectedItem().toString().equals("West-bengal"))
        {
            String l_city[]={"Kolkata"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_state.getSelectedItem().toString().equals("Tamilnadu"))
        {
            String l_city[]={"Chennai"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_state.getSelectedItem().toString().equals("Delhi"))
        {
            String l_city[]={"Delhi"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_state.getSelectedItem().toString().equals("Andhra-Pradesh"))
        {
            String l_city[]={"Hyderabad"};
            ArrayAdapter city_ad=new ArrayAdapter(Post_prop_form.this, android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }

    }

    public boolean validate()
    {
        int count =0;
        Drawable draw_warn = ContextCompat.getDrawable(getApplicationContext(), R.drawable.warning_border);
        Drawable draw_normal = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spinner_item);

        String regex_address = "^[a-zA-Z0-9\\s,'-./#()]*$";
        String regex_area_nearby = "^[a-zA-Z\\s]+$";

        Pattern pattern_add = Pattern.compile(regex_address);
        Matcher matcher_add = pattern_add.matcher(ed_address.getText().toString());

        Pattern pattern_area_nearby = Pattern.compile(regex_area_nearby);
        Matcher matcher_area = pattern_area_nearby.matcher(ed_area.getText().toString());
        Matcher matcher_nearby = pattern_area_nearby.matcher(ed_nearby.getText().toString());

        if(matcher_add.matches() && ed_address.getText().toString().length() > 0)
        {
            count+=1;
        //    ed_address.setBackground(draw_normal);
        }
        else {
        //    ed_address.setBackground(draw_warn);
            count=0;
        }

        if(matcher_area.matches())
        {
            count+=1;
         //   ed_area.setBackground(draw_normal);
        }
        else {
         //   ed_area.setBackground(draw_warn);
            count=0;
        }

        if(matcher_nearby.matches())
        {
            count+=1;
         //   ed_nearby.setBackground(draw_normal);
        }
        else {
         //   ed_nearby.setBackground(draw_warn);
            count=0;
        }


        if(rb_rent.isChecked() || rb_sell.isChecked())
        {
            count+=1;
         //   rg_what.setBackground(draw_normal);
        }
        else {
        //    rg_what.setBackground(draw_warn);
            count=0;
        }

        if(rb_full.isChecked() || rb_un.isChecked() || rb_semi.isChecked())
        {
            count+=1;
      //      rg_furn.setBackground(draw_normal);
        }
        else {
         //   rg_furn.setBackground(draw_warn);
            count=0;
        }

        if(count>4)
        {
            //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}