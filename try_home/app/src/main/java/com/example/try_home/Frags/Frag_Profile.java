package com.example.try_home.Frags;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.try_home.MainActivity;
import com.example.try_home.O_Login;
import com.example.try_home.R;
import com.example.try_home.Show_data;
import com.example.try_home.SqlOp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Frag_Profile extends Fragment {
    TextView tv;
    EditText ed_fname,ed_lname,ed_mobile;
    Button btn_apply,btn_logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_frag__profile, container, false);


        // Inflate the layout for this fragment
        tv= v.findViewById(R.id.txt_email);
        ed_fname=v.findViewById(R.id.ed_fname);
        ed_lname=v.findViewById(R.id.ed_lname);
        ed_mobile=v.findViewById(R.id.ed_mobile);
        ed_fname=v.findViewById(R.id.ed_fname);
        btn_apply=v.findViewById(R.id.btn_apply);
        btn_logout=v.findViewById(R.id.btn_logout);
        SharedPreferences sp=getActivity().getSharedPreferences("owner_login", MODE_PRIVATE);
        tv.setText(sp.getString("own_login_email",null));
        ed_fname.setText(sp.getString("own_login_f_name",null));
        ed_lname.setText(sp.getString("own_login_l_name",null));
        ed_mobile.setText(sp.getString("own_login_phone",null));

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate();
                if(b==true) {
                    SqlOp o = new SqlOp(getContext());
                    o.owner_update_profile(ed_fname.getText().toString(), ed_lname.getText().toString(), ed_mobile.getText().toString(), tv.getText().toString());
                    Toast.makeText(getContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getContext(), Show_data.class));
                }else
                    Toast.makeText(getContext(), " Fill data appropriately ", Toast.LENGTH_SHORT).show();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp= getActivity().getSharedPreferences("owner_login", MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("own_login_oid",null);
                ed.putString("own_login_f_name",null);
                ed.putString("own_login_l_name",null);
                ed.putString("own_login_email",null);
                ed.putString("own_login_phone",null);
                ed.commit();
                Toast.makeText(getContext(), "LOGGED OUT", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), O_Login.class));
            }
        });


        return v;

    }
    public boolean validate()
    {
        Drawable draw_warn = ContextCompat.getDrawable(getContext(), R.drawable.warning_border);
        Drawable draw_normal = ContextCompat.getDrawable(getContext(), R.drawable.spinner_item);
        int count=0;

        if(ed_mobile.getText().toString().length() == 10)
        {
            count+=1;
            ed_mobile.setBackground(draw_normal);
        }
        else {
            ed_mobile.setBackground(draw_warn);
            count=0;
        }

        String regexPattern = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(ed_fname.getText().toString());

        if(matcher.matches())
        {
            count+=1;
            ed_fname.setBackground(draw_normal);
        }
        else {
            ed_fname.setBackground(draw_warn);
            count=0;
        }

        matcher = pattern.matcher(ed_lname.getText().toString());
        if(matcher.matches())
        {
            count+=1;
            ed_lname.setBackground(draw_normal);
        }
        else {
            ed_lname.setBackground(draw_warn);
            count=0;
        }

        if(count>6)
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