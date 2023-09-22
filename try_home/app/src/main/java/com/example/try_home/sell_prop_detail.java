package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class sell_prop_detail extends AppCompatActivity {
    int column_index=0;
    byte[] by,by2,by3,by4;
    byte[][] ls;
    String oid;
  //  ImageView iv;
    ImageSwitcher is;
    ImageButton ib_next,ib_prev;
    Button btn_contact,btn_loan;
    TextView tv_bhk_add_proptype_area,tv_furn,tv_price,tv_nearby,tv_count,tv_postdate,tv_bedroom,tv_proptype,tv_desc,tv_price2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_prop_detail);

        //iv = findViewById(R.id.iv);
        is=findViewById(R.id.imgsw);
        ib_next=findViewById(R.id.ib_next);
        ib_prev=findViewById(R.id.ib_pr);

        tv_bedroom=findViewById(R.id.txt_bedroom);
        tv_furn=findViewById(R.id.txt_furniture);
        tv_count=findViewById(R.id.txt_views);
        tv_price=findViewById(R.id.txt_price);
        tv_price2=findViewById(R.id.txt_price2);
        tv_desc=findViewById(R.id.txt_description);
        tv_nearby=findViewById(R.id.txt_nearby);
        tv_postdate=findViewById(R.id.txt_postdate);
        tv_proptype=findViewById(R.id.txt_proptype);
        tv_bhk_add_proptype_area=findViewById(R.id.txt_bhk_prop_add);

        btn_loan=findViewById(R.id.btn_loan);
        btn_contact=findViewById(R.id.btn_owner_contact);



        Intent i=getIntent();
        String propid=i.getStringExtra("prop_detail_variable_prop_id");
        String what=i.getStringExtra("prop_detail_variable_what");
        SqlOp x=new SqlOp(getApplicationContext());
        Cursor cs=x.sell_prop_details(Integer.parseInt(propid));

        Cursor c=null;
        if(what.equals("Sell")) {
         //   Toast.makeText(this, "hello there", Toast.LENGTH_SHORT).show();
            c = x.get_sell_prop_data_img(propid);
            c.moveToFirst();
            column_index=0;
            by=c.getBlob(0);
            by2=c.getBlob(1);
            by3=c.getBlob(2);
            by4=c.getBlob(3);
            ls= new byte[][]{by, by2, by3, by4};
            c=x.sell_prop_details(Integer.parseInt(propid));
            c.moveToFirst();
            oid=c.getString(1).toString();
            tv_bhk_add_proptype_area.setText(c.getString(11)+" "+c.getString(10)+" in "+c.getString(5)+" , "+c.getString(4)+" , "+c.getString(3));
            tv_nearby.setText("Near by : "+c.getString(7));
            tv_price.setText(c.getString(9)+" ₹ ");
            tv_price2.setText("Price "+c.getString(9)+"₹ ");
            tv_count.setText("20 people viewd this property");
            tv_postdate.setText(c.getString(13));
            tv_proptype.setText(c.getString(10));
            tv_bedroom.setText(c.getString(11));
            tv_furn.setText(c.getString(8));
            tv_desc.setText(c.getString(12)+"                                                         ");
        }
        else {
            c = x.get_rent_prop_data_img(propid);
            c.moveToFirst();
            column_index=0;
            by=c.getBlob(0);
            by2=c.getBlob(1);
            by3=c.getBlob(2);
            by4=c.getBlob(3);
            ls= new byte[][]{by, by2, by3, by4};
            c=x.rent_prop_details(Integer.parseInt(propid));
            c.moveToFirst();
            oid=c.getString(1).toString();
            tv_bhk_add_proptype_area.setText(c.getString(12)+" "+c.getString(11)+" in "+c.getString(5)+" , "+c.getString(4)+" , "+c.getString(3));
            tv_nearby.setText("Near by : "+c.getString(7));
            tv_price.setText("Rent : "+c.getString(9)+" ₹ ");
            tv_price2.setText("Deposit "+c.getString(10)+"₹ ");
            tv_count.setText("20 people viewd this property");
            tv_postdate.setText(c.getString(14));
            tv_proptype.setText(c.getString(11));
            tv_bedroom.setText(c.getString(12));
            tv_furn.setText(c.getString(8));
            tv_desc.setText(c.getString(13)+"                                                         ");
        }

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getSharedPreferences("tenent_login", MODE_PRIVATE);
                String s1 = sh.getString("login_data_email", "");

                if(s1.length()!=0) {

                    Intent i = new Intent(getApplicationContext(), show_owner_details.class);
                    i.putExtra("owner_contact_oid", oid);
                    i.putExtra("owner_contact_what", what);
                    startActivity(i);
                }else {
                    Toast.makeText(sell_prop_detail.this, "Login to view Owner Details", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),T_Login.class));
                }
            }
        });

        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv=new ImageView(getApplicationContext());
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setImageBitmap(byte_to_bitmap(ls[column_index]));
                return iv;
            }
        });

        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is.setImageResource(R.drawable.ic_launcher_foreground);
                if(column_index<3 && column_index >=0)
                {
                    column_index+=1;
                    Drawable drawable =new BitmapDrawable(byte_to_bitmap(ls[column_index]));
                    is.setImageDrawable(drawable);
                }
            }
        });

        ib_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv=new ImageView(getApplicationContext());
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //is.setImageResource(R.drawable.ic_launcher_foreground);
                if(column_index>0 && column_index<4)
                {
                    column_index-=1;
                    Drawable drawable =new BitmapDrawable(byte_to_bitmap(ls[column_index]));
                    is.setImageDrawable(drawable);
                }
            }
        });
        btn_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/search?q=home+loan&oq=home+loan&aqs=chrome..69i57j0i67i650l2j0i67i433i650j0i512j0i67i433i650j0i512l2j0i67i650j0i512.1319j0j7&sourceid=chrome&ie=UTF-8");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    private Bitmap byte_to_bitmap(byte [] by)
    {
        return BitmapFactory.decodeByteArray(by,0,by.length);
    }
}