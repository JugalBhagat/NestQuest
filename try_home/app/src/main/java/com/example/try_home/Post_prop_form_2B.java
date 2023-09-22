package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post_prop_form_2B extends AppCompatActivity {
    EditText ed_rent,ed_deposite,ed_bhk,ed_descrip;
    RadioGroup rg_prop_type,rg_bhk_type;
    RadioButton rb_rk,rb_bhk,rb_flat,rb_ind,rb_rowh;
    String bhk_type="";
    Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_prop_form2_b);
        ed_rent=findViewById(R.id.ed_rent);
        ed_bhk=findViewById(R.id.ed_bhk_type);
        ed_deposite=findViewById(R.id.ed_depo);
        ed_descrip=findViewById(R.id.ed_description);
        rg_prop_type=findViewById(R.id.rg_prop_type);
        rg_bhk_type=findViewById(R.id.rg_bhk_type);
        rb_bhk=findViewById(R.id.rb_bhk);
        rb_rk=findViewById(R.id.rb_rk);
        btn_next=findViewById(R.id.btn_next);
        rb_flat=findViewById(R.id.rb_flat);
        rb_ind=findViewById(R.id.rb_indep);
        rb_rowh=findViewById(R.id.rb_rowh);

        rb_bhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_bhk.setEnabled(true);

            }
        });
        rb_rk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_bhk.setEnabled(false);
                bhk_type="1 RK";
                ed_bhk.setText("");


            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate();
                if(b==true) {
                    send_data();
                }
                else
                    Toast.makeText(getApplicationContext(), "Fill all data appropriately", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void send_data()
    {
        int rg_ptype_id=rg_prop_type.getCheckedRadioButtonId();
        RadioButton rb_prop=findViewById(rg_ptype_id);

        int rg_bhktype_id=rg_bhk_type.getCheckedRadioButtonId();
        RadioButton rb_bhktype=findViewById(rg_bhktype_id);

        bhk_type="nulllll";
        //Toast.makeText(Post_prop_form_2B.this,String.valueOf(ed_bhk.isEnabled()),Toast.LENGTH_SHORT).show();

        if(ed_bhk.isEnabled()==true)
            bhk_type=ed_bhk.getText().toString()+" BHK";
        else
            bhk_type="1 RK";

        SharedPreferences sp=getSharedPreferences("sell_rent", Context.MODE_PRIVATE);
        String what=sp.getString("what",null);

        if(what.equals("Rent")) {
            sp = getSharedPreferences("sell_rent", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("rent_rent", ed_rent.getText().toString());
            ed.putString("rent_depo", ed_deposite.getText().toString());
            ed.putString("rent_ptype", rb_prop.getText().toString());
            ed.putString("rent_bhk_type", bhk_type);
            ed.putString("rent_description", ed_descrip.getText().toString());
            ed.commit();
            startActivity(new Intent(getApplicationContext(), Post_prop_form_3.class));
        }
    }
    public boolean validate()
    {
        int count =0;
        Drawable draw_warn = ContextCompat.getDrawable(getApplicationContext(), R.drawable.warning_border);
        Drawable draw_normal = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spinner_item);

        String regex_desc = "^[a-zA-Z0-9\\s,'-./#()]*$";
        String regex_bhk = "^(0?[1-9]|[1-9][0-9])$";
        String regex_price = "^(?:0|[1-9]\\d{0,2})(?:,\\d{3})*(?:\\.\\d{2})?$";


        Pattern pattern_desc = Pattern.compile(regex_desc);
        Matcher matcher_desc = pattern_desc.matcher(ed_descrip.getText().toString());

        Pattern pattern_bhk = Pattern.compile(regex_bhk);
        Matcher matcher_bhk = pattern_bhk.matcher(ed_bhk.getText().toString());

        Pattern pattern_price = Pattern.compile(regex_price);
        Matcher matcher_rent = pattern_price.matcher(ed_rent.getText().toString());
        Matcher matcher_depo = pattern_price.matcher(ed_deposite.getText().toString());

    //    if(matcher_rent.matches())
    //    {
            count+=1;
            //    ed_address.setBackground(draw_normal);
   //     }
    //    else {
            //    ed_address.setBackground(draw_warn);

     //   }

    //    if(matcher_depo.matches())
    //    {
            count+=1;
            //    ed_address.setBackground(draw_normal);
     //   }
      //  else {
            //    ed_address.setBackground(draw_warn);

     //   }

        if(matcher_desc.matches() && ed_descrip.getText().toString().length() > 0)
        {
            count+=1;
            //   ed_nearby.setBackground(draw_normal);
        }
        else {
            //   ed_nearby.setBackground(draw_warn);

        }


        if(rb_rk.isChecked() || rb_bhk.isChecked())
        {
            if(rb_bhk.isChecked() && matcher_bhk.matches())
            {
                count+=1;
            }
            else if(rb_rk.isChecked())
            {
                count+=1;
            }
        }
        else {
            //    rg_what.setBackground(draw_warn);

        }

        if(rb_flat.isChecked() || rb_rowh.isChecked() || rb_ind.isChecked())
        {
            count+=1;
            //      rg_furn.setBackground(draw_normal);
        }
        else {
            //   rg_furn.setBackground(draw_warn);

        }

        if(count>4)
        {
            //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}