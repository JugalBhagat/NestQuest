package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Choice extends AppCompatActivity {
    Button btn_tenant,btn_owner;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        btn_owner=findViewById(R.id.owner);
        btn_tenant=findViewById(R.id.tenant);
        iv=findViewById(R.id.imageView);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.nest2));

        SqlOp x=new SqlOp(getApplicationContext());
      //  x.temp();
      //  x.temp2();

        btn_tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,MainActivity.class));
            }
        });
        btn_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,O_Login.class));
            }
        });

        btn_tenant.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                startActivity(new Intent(getApplicationContext(),Show_data.class));
                return false;
            }
        });
    }
}