package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class show_owner_details extends AppCompatActivity {

    TextView tv_phone,tv_email,tv_name;
   // ImageButton ib_call;
    TextView tv_over;
    Button btn_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_owner_details);
        tv_name=findViewById(R.id.txt_owner_name);
        tv_email=findViewById(R.id.txt_owner_email);
        tv_phone=findViewById(R.id.txt_owner_phone);
        btn_buy=findViewById(R.id.btn_buy);
        tv_over=findViewById(R.id.txt_over);


        Intent i=getIntent();
        String oid=i.getStringExtra("owner_contact_oid");
        String what=i.getStringExtra("owner_contact_what");

        SqlOp x=new SqlOp(getApplicationContext());
        Cursor c1=x.get_owner_contact(oid);
        c1.moveToFirst();


        SharedPreferences sp=getSharedPreferences("tenent_login", Context.MODE_PRIVATE);
        String ten_email=sp.getString("login_data_email",null);
        Cursor c=x.get_sub_r_conts(ten_email);
        c.moveToFirst();
        String sub_type=c.getString(8);
        int r_conts=c.getInt(9);
        if(r_conts>0)
        {
            r_conts-=1;
            Toast.makeText(show_owner_details.this, String.valueOf(r_conts), Toast.LENGTH_SHORT).show();
            tv_name.setText("Name : "+c1.getString(1)+" "+c1.getString(2));
            tv_email.setText("Email : "+c1.getString(3));
            tv_phone.setText("Phone : "+c1.getString(5));
            x=new SqlOp(getApplicationContext());
            x.set_conts_one_less(ten_email,r_conts);
        }
        else {
            tv_over.setVisibility(View.VISIBLE);
            tv_email.setVisibility(View.INVISIBLE);
            tv_name.setVisibility(View.INVISIBLE);
            tv_phone.setVisibility(View.INVISIBLE);
//            ib_call.setVisibility(View.INVISIBLE);
            Toast.makeText(show_owner_details.this, "You ran out of free contacts", Toast.LENGTH_SHORT).show();

        }
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),premium_plans.class));
            }
        });



    }
}