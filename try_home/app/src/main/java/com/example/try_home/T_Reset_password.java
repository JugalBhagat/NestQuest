package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class T_Reset_password extends AppCompatActivity {
    TextView tv_email;
    EditText ed_1,ed_2;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treset_password);
        tv_email=findViewById(R.id.tv_email);
        btn_submit=findViewById(R.id.btn_submit);
        ed_1=findViewById(R.id.ed_pass);
        ed_2=findViewById(R.id.ed_pass2);
        Intent i=getIntent();
        tv_email.setText(i.getStringExtra("email_reset"));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SqlOp obj=new SqlOp(getApplicationContext());
                boolean res_cp=check_pass();
                boolean res_val=validate();
                if(res_cp && res_val)
                {
                    boolean res=obj.Tenent_password_reset(tv_email.getText().toString(),ed_1.getText().toString());
                    //Toast.makeText(O_Reset_password.this, String.valueOf(res), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Password changed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),T_Login.class));
                }
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
        return true;
    }
}