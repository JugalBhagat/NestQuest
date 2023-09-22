package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class payment_redirect extends AppCompatActivity {
    int i=0;
    ConstraintLayout conlay_succ;
    Button btn_try,btn_cancel,btn_home;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_redirect);
        tv=findViewById(R.id.textView17);
        conlay_succ=findViewById(R.id.constraintLayout4);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_home=findViewById(R.id.btn_home);
        btn_try=findViewById(R.id.btn_try);

        Intent i=getIntent();
        String res=i.getStringExtra("response");
      //  Toast.makeText(this, res, Toast.LENGTH_SHORT).show();

        SharedPreferences sp=getSharedPreferences("tenent_login", Context.MODE_PRIVATE);
        String firstname=sp.getString("login_data_firstname",null);
        String email=sp.getString("login_data_email",null);

        if(res.equals("success"))
        {
            tv.setText("You bought our Premium plan successfully , now enjoy benefits");
            btn_cancel.setVisibility(View.INVISIBLE);
            btn_try.setVisibility(View.INVISIBLE);
            btn_home.setVisibility(View.VISIBLE);
            SqlOp x=new SqlOp(getApplicationContext());

            int no=x.set_conts_get_r_conts(email);
            x.set_conts_add_20(email,no);
            x.up_sub_type(email);

        } else if (res.equals("failed")) {
            tv.setText("Payment failed, Try again after sometime");
            btn_home.setVisibility(View.VISIBLE);
            btn_cancel.setVisibility(View.INVISIBLE);
            btn_try.setVisibility(View.VISIBLE);
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        btn_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),premium_plans.class));
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

}