package com.example.try_home.Frags;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.try_home.R;
import com.example.try_home.SqlOp;
import com.example.try_home.premium_plans;
import com.example.try_home.search_view;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Tenent_home extends Fragment {
    Spinner sp_stat,sp_city;
    EditText ed_area;
    VideoView vv;
    Button btn_search;
    RadioGroup rg;
    ImageView iv;
    RadioButton rb_sell,rb_rent;
    ConstraintLayout con_lay;
    private AdView mAdView;
    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_tenent_home, container, false);
        sp_stat=v.findViewById(R.id.spin_state);
        sp_city=v.findViewById(R.id.spin_city);
        ed_area=v.findViewById(R.id.ed_area);
        btn_search=v.findViewById(R.id.search);
        rg=v.findViewById(R.id.rg_what);
        rb_rent=v.findViewById(R.id.rb_rent);
        rb_sell=v.findViewById(R.id.rb_sell);
        con_lay=v.findViewById(R.id.primium_plan_lay_ad);
        iv=v.findViewById(R.id.imageView11);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.baseline_workspace_premium_24));
        //video viewer
        /*vv=v.findViewById(R.id.videoView);
        String vurl="android.resource://raw/"+R.raw.home_loan;
        Uri uri=Uri.parse(vurl);
        vv.setVideoURI(uri);
        MediaController mc=new MediaController(getContext());
        vv.setMediaController(mc);
        mc.setAnchorView(vv);
        vv.requestFocus();
        vv.start();*/
        con_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), premium_plans.class));
            }
        });

        ed_area.setText("remove this");  //comment this line

        // google ads

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String l_state[]={"Maharashtra","Gujarat","Karnataka","West-bengal","Tamilnadu","Delhi","Andhra-Pradesh"};
        ArrayAdapter state_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_state);
        sp_stat.setAdapter(state_ad);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove below line of code
               // startActivity(new Intent(getContext(), prop_detail.class));

                String what="";
                int count=0;
                if(rb_rent.isChecked()) {
                    what = "Rent";
                    count+=1;
                }
                else if(rb_sell.isChecked()) {
                    what = "Sell";
                    count+=1;
                }
                else {
                    Toast.makeText(getContext(), "Enter select Sell/Rent", Toast.LENGTH_SHORT).show();
                    count=0;
                }
                String area=ed_area.getText().toString();
                String state = sp_stat.getSelectedItem().toString();
                String city = sp_city.getSelectedItem().toString();
                if(!ed_area.getText().toString().isEmpty())
                   count+=1;
                else {
                    Toast.makeText(getContext(), "Please enter Area name", Toast.LENGTH_SHORT).show();
                    count=0;
                }
                if(count==2) {
                    Intent i = new Intent(getContext(), search_view.class);
                    i.putExtra("search_state", state);
                    i.putExtra("search_city", city);
                    i.putExtra("search_area", area);
                    i.putExtra("search_what", what);
                    SqlOp x = new SqlOp(getContext());
                    startActivity(i);
                }
                count=0;
            }
        });
        sp_stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_city_spinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;

    }

    public void get_city_spinner() {
        if(sp_stat.getSelectedItem().toString().equals("Maharashtra"))
        {
            String l_city[]={"Mumbai","Pune"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_stat.getSelectedItem().toString().equals("Gujarat"))
        {
            String l_city[]={"Ahemdabad"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_stat.getSelectedItem().toString().equals("Karnataka"))
        {
            String l_city[]={"Bangalore"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_stat.getSelectedItem().toString().equals("West-bengal"))
        {
            String l_city[]={"Kolkata"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_stat.getSelectedItem().toString().equals("Tamilnadu"))
        {
            String l_city[]={"Chennai"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_stat.getSelectedItem().toString().equals("Delhi"))
        {
            String l_city[]={"Delhi"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }
        if(sp_stat.getSelectedItem().toString().equals("Andhra-Pradesh"))
        {
            String l_city[]={"Hyderabad"};
            ArrayAdapter city_ad=new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,l_city);
            sp_city.setAdapter(city_ad);
        }

    }

}