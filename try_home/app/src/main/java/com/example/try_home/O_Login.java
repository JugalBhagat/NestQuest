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

public class O_Login extends AppCompatActivity {
    TextView hyp_link,hyp_forgot_pass;
    EditText ed_email,ed_pass;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ologin);

        //check login or not
        if(check_login_ornot())
            startActivity(new Intent(getApplicationContext(),own_menu_2.class));


        hyp_link=findViewById(R.id.lnkLogin);
        hyp_forgot_pass=findViewById(R.id.forgot_pass);
        ed_email=findViewById(R.id.txtEmail);
        ed_pass=findViewById(R.id.txtPwd);
        btn_login=findViewById(R.id.btnLogin);
        String text="Not Registered? Sign up";
        SpannableString ss=new SpannableString(text);
        ClickableSpan cs=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(O_Login.this,O_Register.class));
                //Toast.makeText(T_Login.this,"hello ccc",Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(cs,0,23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hyp_link.setText(ss);
        hyp_link.setMovementMethod(LinkMovementMethod.getInstance());


        String text2="Forgot Password ?";
        SpannableString ss2=new SpannableString(text2);
        ClickableSpan cs2=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(O_Login.this,Owner_Forgot_password.class));
                //Toast.makeText(T_Login.this,"hello ccc",Toast.LENGTH_SHORT).show();
            }
        };
        ss2.setSpan(cs2,0,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hyp_forgot_pass.setText(ss2);
        hyp_forgot_pass.setMovementMethod(LinkMovementMethod.getInstance());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate();
                if(b==true) {
                    try {
                        SqlOp obj = new SqlOp(O_Login.this);
                        //Toast.makeText(O_Login.this, ed_email.getText().toString(),Toast.LENGTH_SHORT).show();
                        Cursor c = obj.O_login_data(ed_email.getText().toString(), ed_pass.getText().toString());
                        c.moveToFirst();
                        Intent i = new Intent(O_Login.this, own_menu_2.class);
                        SharedPreferences sp = getSharedPreferences("owner_login", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        String oid = c.getString(0);
                        String fname = c.getString(1);
                        String lname = c.getString(2);
                        String email = c.getString(3);
                        String phone = c.getString(5);
                        ed.putString("own_login_oid", oid);
                        ed.putString("own_login_f_name", fname);
                        ed.putString("own_login_l_name", lname);
                        ed.putString("own_login_email", email);
                        ed.putString("own_login_phone", phone);
                        c.close();
                        ed.commit();
                        Toast.makeText(O_Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(O_Login.this, "Account not Exits", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public boolean check_login_ornot()
    {
        SharedPreferences sp=getSharedPreferences("owner_login",MODE_PRIVATE);
        String oid=sp.getString("own_login_oid",null);
        String fname=sp.getString("own_login_f_name",null);
        String lname=sp.getString("own_login_l_name",null);
        String email=sp.getString("own_login_email",null);
        String phone = sp.getString("own_login_phone",null);
        if(oid!=null && fname!=null && lname!=null && email!=null && phone!=null)
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Choice.class));
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

