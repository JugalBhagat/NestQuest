package com.example.try_home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("ALL")
public class Post_prop_form_3 extends AppCompatActivity {
    ImageView iv1,iv2,iv3,iv4;
    Button btn_img_select_1,btn_img_select_2,btn_img_select_3,btn_img_select_4,btn_submit;
    ImageButton ib1,ib2,ib3,ib4;
    String what,state,city,area,nearby,price,address,furniture,property,bhk_type,descrip;
    String rent,deposite,oid,cur_date;
    byte[] by1;
    byte[] by2;
    byte[] by3;
    byte[] by4;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_prop_form3);
        iv1=findViewById(R.id.imageView_1);
        iv2=findViewById(R.id.imageView_2);
        iv3=findViewById(R.id.imageView_3);
        iv4=findViewById(R.id.imageView_4);
        btn_img_select_1=findViewById(R.id.btn_image);
        btn_img_select_2=findViewById(R.id.btn_image2);
        btn_img_select_3=findViewById(R.id.btn_image3);
        btn_img_select_4=findViewById(R.id.btn_image4);
        ib1=findViewById(R.id.imageButton);
        ib2=findViewById(R.id.imageButton2);
        ib3=findViewById(R.id.imageButton3);
        ib4=findViewById(R.id.imageButton4);
        btn_submit=findViewById(R.id.btn_submit);


        btn_img_select_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig=new Intent(Intent.ACTION_PICK);
                ig.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(ig,100);
            }
        });
        btn_img_select_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig=new Intent(Intent.ACTION_PICK);
                ig.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(ig,200);
            }
        });
        btn_img_select_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig=new Intent(Intent.ACTION_PICK);
                ig.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(ig,300);
            }
        });
        btn_img_select_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig=new Intent(Intent.ACTION_PICK);
                ig.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(ig,400);
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setImageURI(null);
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv2.setImageURI(null);
            }
        });
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv3.setImageURI(null);
            }
        });
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv4.setImageURI(null);
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=validate_img();
                if(b==true) {
                    get_img_data();
                    other_data();
                    get_data_form();
                    if (what.equals("Sell"))
                        sell_insert_data();
                    else if (what.equals("Rent"))
                        rent_insert_data();
                }
                else
                    Toast.makeText(Post_prop_form_3.this, "", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            if(requestCode==100)
            {
                iv1.setImageURI(data.getData());
            }
            if(requestCode==200)
            {
                iv2.setImageURI(data.getData());
            }
            if(requestCode==300)
            {
                iv3.setImageURI(data.getData());
            }
            if(requestCode==400)
            {
                iv4.setImageURI(data.getData());
            }

        }
    }
    private Bitmap byte_bitmap(byte[] by)
    {
        return BitmapFactory.decodeByteArray(by,0,by.length);
    }
    private byte[] img_byte(ImageView iv)
    {
        Bitmap bm=((BitmapDrawable) iv.getDrawable()).getBitmap();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,30,baos);
        return baos.toByteArray();
    }
    public boolean validate_img()
    {
        if(iv1.getDrawable()!=null && iv2.getDrawable()!=null && iv3.getDrawable()!=null && iv4.getDrawable()!=null)
        //if(by1!=null && by2!=null && by3!=null && by4!=null)
        {
            return true;
        }else
            Toast.makeText(this, "Select approppriate pictures", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void sell_insert_data()
    {
        SqlOp obj=new SqlOp(getApplicationContext());
        Boolean res=obj.prop_sell_insert(oid,state,city,area,address,what,nearby,furniture,price,property,bhk_type,descrip,cur_date,cur_date,"0","active",by1,by2,by3,by4);
        if(res) {
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),own_menu_2.class));
        }
    }
    public void rent_insert_data()
    {
        SqlOp obj=new SqlOp(getApplicationContext());
        Boolean res=obj.prop_rent_insert(oid,state,city,area,address,what,nearby,furniture,rent,deposite,property,bhk_type,descrip,cur_date,cur_date,"0","active",by1,by2,by3,by4);
        if(res) {
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),own_menu_2.class));
        }
    }
    public void get_img_data()
    {
        by1 = img_byte(iv1);
        by2 = img_byte(iv2);
        by3 = img_byte(iv3);
        by4 = img_byte(iv4);
    }

    public void get_data_form()
    {
        SharedPreferences sp=getSharedPreferences("sell_rent", Context.MODE_PRIVATE);
        what=sp.getString("what",null);
        //Toast.makeText(this, what.toString(), Toast.LENGTH_SHORT).show();
        if(what.equals("Rent")) {
            state = sp.getString("rent_state", null);
            city = sp.getString("rent_city", null);
            area = sp.getString("rent_area", null);
            address = sp.getString("rent_address", null);
            nearby = sp.getString("rent_nearby", null);
            furniture = sp.getString("rent_furn", null);
            rent = sp.getString("rent_rent", null);
            deposite = sp.getString("rent_depo", null);
            property = sp.getString("rent_ptype", null);
            bhk_type = sp.getString("rent_bhk_type", null);
            descrip = sp.getString("rent_description", null);
           /* Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, area, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, nearby, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, furniture, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, rent, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, deposite, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, property, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, bhk_type, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, descrip, Toast.LENGTH_SHORT).show();*/
        } else if (what.equals("Sell")) {
            state=sp.getString("sell_state",null);
            city=sp.getString("sell_city",null);
            area=sp.getString("sell_area",null);
            address=sp.getString("sell_address",null);
            nearby=sp.getString("sell_nearby",null);
            furniture=sp.getString("sell_furn",null);
            price=sp.getString("sell_price",null);
            property=sp.getString("sell_ptype",null);
            bhk_type=sp.getString("sell_bhk_type",null);
            descrip=sp.getString("sell_descrip",null);/*
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_state",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_city",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_area",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_address",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_nearby",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_furn",null),Toast.LENGTH_SHORT).show();
            //Toast.makeText(Post_prop_form_3.this,sp.getString("what",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_price",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_ptype",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_bhk_type",null),Toast.LENGTH_SHORT).show();
            Toast.makeText(Post_prop_form_3.this,sp.getString("sell_descrip",null),Toast.LENGTH_SHORT).show();*/

        }

    }
    public void other_data()
    {
        SharedPreferences sp=getSharedPreferences("owner_login", Context.MODE_PRIVATE);
        oid=sp.getString("own_login_oid",null);
        cur_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

}