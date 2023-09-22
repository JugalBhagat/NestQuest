package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class O_Reset_password extends AppCompatActivity {
    TextView tv_email;
    EditText ed_1,ed_2;
    Button btn_submit;
    //SqlOp db=new SqlOp(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreset_password);
        tv_email=findViewById(R.id.tv_email);
        btn_submit=findViewById(R.id.btn_submit);
        ed_1=findViewById(R.id.ed_pass);
        ed_2=findViewById(R.id.ed_pass2);
        Intent i=getIntent();
        tv_email.setText(i.getStringExtra("email_reset"));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate();
                if(b==true) {
                    SqlOp obj = new SqlOp(O_Reset_password.this);
                    boolean res_cp = check_pass();
                    boolean res_val = validate();
                    if (res_cp && res_val) {
                        boolean res = obj.Owner_password_reset(tv_email.getText().toString(), ed_1.getText().toString());
                        //Toast.makeText(O_Reset_password.this, String.valueOf(res), Toast.LENGTH_SHORT).show();
                        Toast.makeText(O_Reset_password.this, "Password changed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), O_Login.class));
                    }
                }
                else
                    Toast.makeText(O_Reset_password.this, "inappropriate password", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean check_pass()
    {
        if(ed_1.getText().toString().equals(ed_2.getText().toString()))
        {
            return true;
        }
        else
            return false;
    }
    public boolean validate()
    {
        Drawable draw_warn = ContextCompat.getDrawable(getApplicationContext(), R.drawable.warning_border);
        Drawable draw_normal = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spinner_item);

        int count=0;

        if(ed_1.getText().toString().length() >= 8)
        {
            count+=1;
            ed_1.setBackground(draw_normal);
        }
        else {
            ed_1.setBackground(draw_warn);
            count=0;
        }

        if(ed_2.getText().toString().length() >= 8)
        {
            count+=1;
            ed_2.setBackground(draw_normal);
        }
        else {
            ed_2.setBackground(draw_warn);
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