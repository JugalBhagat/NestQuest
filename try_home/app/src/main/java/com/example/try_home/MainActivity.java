package com.example.try_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.try_home.Frags.Tenent_home;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dr;
    NavigationView nv;
    TextView txt_Fname,txt_Email;
    Toolbar tb;
    LinearLayout ll;
    VideoView vv;
    SqlOp db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dr=findViewById(R.id.drawer_lay);
        tb=findViewById(R.id.toolbar);
        nv=findViewById(R.id.nav_view);

        Tenent_home th=new Tenent_home();
        FragmentTransaction t=getSupportFragmentManager().beginTransaction();


        // t.replace(R.id.linear,th);
/*

        vp2=findViewById(R.id.view_pager);
        my_vp=new My_view_pager_AD(this);
        vp2.setAdapter(my_vp);
*/

        /*vv=findViewById(R.id.vv);
        String vurl="android.resource://"+getPackageName()+"/"+R.raw.home_loan;
        Uri uri=Uri.parse(vurl);
        vv.setVideoURI(uri);
        MediaController mc=new MediaController(this);
        vv.setMediaController(mc);
        mc.setAnchorView(vv);*/

        //after login successfully display name of user

        View headerContainer = nv.getHeaderView(0);
        txt_Fname = (TextView)headerContainer.findViewById(R.id.txt_firstname_login);
        txt_Email = (TextView)headerContainer.findViewById(R.id.txt_email_login);
        //Intent i=getIntent();
        SharedPreferences sp=getSharedPreferences("tenent_login", Context.MODE_PRIVATE);
        String firstname=sp.getString("login_data_firstname",null);
        String email=sp.getString("login_data_email",null);
        if(firstname!=null) {
            txt_Fname.setText(firstname);
            txt_Email.setText(email);
        }
        else
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();


        //step -1
            setSupportActionBar(tb);
            // Actionbar drawer toggle requires 5 parameters
            ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,dr,tb,R.string.open_drawer,R.string.close_drawer);
            dr.addDrawerListener(toggle);
            toggle.syncState();


            load_frag(new Tenent_home());
            nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id=item.getItemId();

                    if (id==R.id.about) {
                        Intent i=new Intent(MainActivity.this,about_us.class);
                        startActivity(i);
                    }
                    else if (id==R.id.buy_premium) {
                        Intent i=new Intent(MainActivity.this,premium_plans.class);
                        startActivity(i);
                    }
                    else if(id==R.id.choice) {
                        Intent i=new Intent(MainActivity.this,Choice.class);
                        startActivity(i);
                        //Toast.makeText(getApplicationContext(),"Selected item is Liked Properties",Toast.LENGTH_SHORT).show();
                    }
                    else if (id==R.id.logout) {

                        SharedPreferences sp=getSharedPreferences("tenent_login", Context.MODE_PRIVATE);
                        String firstname=sp.getString("login_data_firstname",null);
                        String email=sp.getString("login_data_email",null);
                        if(email==null && firstname==null)
                        {
                            Intent i = new Intent(MainActivity.this, T_Login.class);
                            startActivity(i);
                        }
                        else if(email!=null && firstname!=null) {
                            SharedPreferences sp2 = getSharedPreferences("tenent_login", MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp2.edit();
                            ed.putString("login_data_firstname", null);
                            ed.putString("login_data_email", null);
                            txt_Email.setText(null);
                            txt_Fname.setText(null);
                            ed.commit();
                            Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, T_Login.class);
                            startActivity(i);
                        }

                    }
                    dr.closeDrawer(GravityCompat.START);

                    return true;
                }
            });
        //step-2

    }

    private void load_frag(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(dr.isDrawerOpen(GravityCompat.START))
        {
            dr.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}