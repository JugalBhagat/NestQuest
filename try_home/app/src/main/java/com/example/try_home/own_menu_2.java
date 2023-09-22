package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class own_menu_2 extends AppCompatActivity {
    TextView tv_name,tv_email;
    TabLayout tab_lay;
    ViewPager2 vp2;
    TabItem tab_dash,tab_prof;
    My_view_pager_AD my_vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_menu2);
        tab_lay=findViewById(R.id.tabb);
        vp2=findViewById(R.id.view_pager);
        tv_email=findViewById(R.id.txt_email);
        tv_name=findViewById(R.id.txt_name);
        tab_dash=findViewById(R.id.dash_if);
        tab_prof=findViewById(R.id.prof_if);

        my_vp=new My_view_pager_AD(this);
        vp2.setAdapter(my_vp);

        SharedPreferences sp=getSharedPreferences("owner_login",MODE_PRIVATE);
        tv_name.setText(sp.getString("own_login_f_name",null));
        tv_email.setText(sp.getString("own_login_email",null));
        Toast.makeText(this, sp.getString("own_login_oid",null), Toast.LENGTH_SHORT).show();

        if(tv_name.equals(null) && tv_email.equals(null))
            startActivity(new Intent(getApplicationContext(),O_Login.class));

        tab_lay.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp2.setCurrentItem(tab.getPosition());
                vp2.setAdapter(my_vp);
                //Toast.makeText(own_menu_2.this, String.valueOf(tab.getText()), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Choice.class));
    }
}