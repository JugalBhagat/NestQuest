package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class T_Enter_OTP extends AppCompatActivity {
    EditText ed;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenter_otp);

        ed=findViewById(R.id.ed_otp);
        btn=findViewById(R.id.btn_submit);
        Intent i=getIntent();
        String OTP=i.getStringExtra("pass_for_otp");
        Toast.makeText(this, OTP, Toast.LENGTH_LONG).show();
        String email=i.getStringExtra("pass_for_email");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed.getText().toString().equals(OTP))
                {
                    Toast.makeText(getApplicationContext(),"Correct OTP",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),T_Reset_password.class);
                    i.putExtra("email_reset",email.toLowerCase());
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid OTP",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}