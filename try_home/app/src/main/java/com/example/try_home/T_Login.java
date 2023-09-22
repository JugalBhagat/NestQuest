package com.example.try_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class T_Login extends AppCompatActivity {
    SqlOp db;
    EditText ed_email,ed_pass;
    TextView hyp_link,hyp_link_2;
    Button btn_login;
    //EditText ed_firstname,ed_lastname,ed_age,ed_prof,ed_gender,ed_email,ed_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlogin);
        btn_login=findViewById(R.id.btnLogin);
        hyp_link=findViewById(R.id.lnkLogin);
        hyp_link_2=findViewById(R.id.forgot_pass);
        ed_email=findViewById(R.id.txtEmail);
        ed_pass=findViewById(R.id.txtPwd);
        String text="Not Registered? Sign up";
        SpannableString ss=new SpannableString(text);
        ClickableSpan cs=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(T_Login.this,T_Registration.class));
                //Toast.makeText(T_Login.this,"hello ccc",Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(cs,0,23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hyp_link.setText(ss);
        hyp_link.setMovementMethod(LinkMovementMethod.getInstance());


        String text2="Forgot password ?";
        SpannableString ss2=new SpannableString(text2);
        ClickableSpan cs2=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(T_Login.this,tenent_forgot_pass.class));
                //Toast.makeText(T_Login.this,"hello ccc",Toast.LENGTH_SHORT).show();
            }
        };
        ss2.setSpan(cs2,0,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hyp_link_2.setText(ss2);
        hyp_link_2.setMovementMethod(LinkMovementMethod.getInstance());


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate();
                if(b==true) {
                   // Toast.makeText(T_Login.this, String.valueOf(b), Toast.LENGTH_SHORT).show();
                    try {

                        SqlOp obj = new SqlOp(T_Login.this);
                        //Toast.makeText(O_Login.this, ed_email.getText().toString(),Toast.LENGTH_SHORT).show();
                        Cursor c = obj.T_login_data(ed_email.getText().toString(), ed_pass.getText().toString());
                        c.moveToFirst();
                        // Toast.makeText(T_Login.this,c.getString(0),Toast.LENGTH_SHORT).show();
                        String fname = c.getString(1);
                        String email = c.getString(3);
                        c.close();
                        Toast.makeText(T_Login.this, "Welcome " + fname, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(T_Login.this, MainActivity.class);
                        SharedPreferences sp = getSharedPreferences("tenent_login", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("login_data_firstname", fname);
                        ed.putString("login_data_email", email);
                        ed.commit();
                        startActivity(i);

                    } catch (Exception e) {
                        Toast.makeText(T_Login.this, "Account not Exits", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public boolean validate()
    {
        Drawable draw_warn = ContextCompat.getDrawable(getApplicationContext(), R.drawable.warning_border);
        Drawable draw_normal = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spinner_item);

        int count=0;
        if(Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches())
        {
            count+=1;
            ed_email.setBackground(draw_normal);
        }
        else
        {
            ed_email.setBackground(draw_warn);
            count=0;
        }

        if(ed_pass.getText().toString().length() >= 8)
        {
            count+=1;
            ed_pass.setBackground(draw_normal);
        }
        else {
            ed_pass.setBackground(draw_warn);
            count=0;
        }

        if(count>1)
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