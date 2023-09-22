package com.example.try_home;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SqlOp extends SQLiteOpenHelper {

    public  static final String db_name="Db_HomeSweet";
    public  static final String tbl_tenant="tbl_user";
    public  static final String tbl_owner="tbl_owner";
    public static final String tbl_city_state="tbl_city";
    public static final String tbl_sell_insert="tbl_sell_prop";
    public static final String tbl_rent_insert="tbl_rent_prop";

    public SqlOp(@Nullable Context context) {
        super(context,db_name,null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE "+tbl_tenant+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,firstname VARCHAR(20),lastname VARCHAR(20),email VARCHAR(20),password VARCHAR(20),phone VARCHAR(12),age VARCHAR(20),gender VARCHAR(10),sub_type VARCHAR(20))");
        db.execSQL("CREATE TABLE "+tbl_tenant+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,firstname VARCHAR(20),lastname VARCHAR(20),email VARCHAR(20),password VARCHAR(20),phone VARCHAR(12),age VARCHAR(20),gender VARCHAR(10),sub_type VARCHAR(20),r_conts INTEGER)");
        db.execSQL("CREATE TABLE "+tbl_owner+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,firstname VARCHAR(20),lastname VARCHAR(20),email VARCHAR(20),password VARCHAR(20),phone VARCHAR(12))");
        db.execSQL("CREATE TABLE "+tbl_city_state+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,state VARCHAR(20),city VARCHAR(20))");
        //                                                      0                                 1               2                 3                4                5                   6                7                  8                          9                 10                    11                   12                      13                      14                      15                16                 17       18        19        20
        db.execSQL("CREATE TABLE "+tbl_sell_insert+" (Prop_ID INTEGER PRIMARY KEY AUTOINCREMENT,O_ID VARCHAR(10),state VARCHAR(20),city VARCHAR(20),area VARCHAR(20),address VARCHAR(20),what VARCHAR(20),nearby VARCHAR(20),type_prop_furn VARCHAR(20),price VARCHAR(20),prop_type VARCHAR(20),bhk_type VARCHAR(20),description VARCHAR(20),upload_date VARCHAR(20),last_update VARCHAR(20),views VARCHAR(20),status VARCHAR(20),img1 BLOB,img2 BLOB,img3 BLOB,img4 BLOB)");
        //                                                      0                                 1               2                 3                4                5                   6                7                  8                          9                 10                    11                   12                      13                      14                      15                16                 17       18        19        20
        db.execSQL("CREATE TABLE "+tbl_rent_insert+" (Prop_ID INTEGER PRIMARY KEY AUTOINCREMENT,O_ID VARCHAR(10),state VARCHAR(20),city VARCHAR(20),area VARCHAR(20),address VARCHAR(20),what VARCHAR(20),nearby VARCHAR(20),type_prop_furn VARCHAR(20),rent VARCHAR(20),deposite VARCHAR(20),prop_type VARCHAR(20),bhk_type VARCHAR(20),description VARCHAR(20),upload_date VARCHAR(20),last_update VARCHAR(20),views VARCHAR(20),status VARCHAR(20),img1 BLOB,img2 BLOB,img3 BLOB,img4 BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
/*
        String tbl_owner="tbl_owner";
        db.execSQL("DROP TABLE IF EXISTS "+tbl_owner);
        onCreate(db);
    */
    }
    public boolean reg_insert(String fnm,String lnm,String email,String pass,String phone,String age,String gen)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("firstname",fnm);
        cv.put("lastname",lnm);
        cv.put("email",email);
        cv.put("password",pass);
        cv.put("phone",phone);
        cv.put("age",age);
        cv.put("gender",gen);
        cv.put("sub_type","free");
        cv.put("r_conts",5);

        long res=db.insert(tbl_tenant,null,cv);
        if(res==-1)
            return false;
        else
            return true;
    }
    public boolean own_insert(String fnm,String lnm,String email,String pass,String phone)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("CREATE TABLE "+tbl_owner+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,firstname VARCHAR(20),lastname VARCHAR(20),email VARCHAR(20),password VARCHAR(20),phone VARCHAR(12))");
        ContentValues cv1=new ContentValues();
        cv1.put("firstname",fnm);
        cv1.put("lastname",lnm);
        cv1.put("email",email);
        cv1.put("password",pass);
        cv1.put("phone",phone);

        long res=db.insert(tbl_owner,null,cv1);
        if(res==-1)
            return false;
        else
            return true;
    }
    public boolean prop_sell_insert(String OID,String state,String city,String area,String address,String what,String nearby,String type_prop_furn,String price,String prop_type,String bhk_type,String desc,String up_date,String last_up,String view,String status,byte[] img1,byte[] img2,byte[] img3,byte[] img4)
    {
        SQLiteDatabase db;
    //    db.rawQuery("CREATE TABLE "+tbl_sell_insert+" (Prop_ID INTEGER PRIMARY KEY AUTOINCREMENT,O_ID VARCHAR(10),state VARCHAR(20),city VARCHAR(20),area VARCHAR(20),address VARCHAR(20),what VARCHAR(20),nearby VARCHAR(20),type_prop_furn VARCHAR(20),price VARCHAR(20),prop_type VARCHAR(20),bhk_type VARCHAR(20),description VARCHAR(20),upload_date VARCHAR(20),last_update VARCHAR(20),views VARCHAR(20),status VARCHAR(20),img1 BLOB,img2 BLOB,img3 BLOB,img4 BLOB)",null);
        try {
            db=this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("O_ID",OID);
            cv.put("state",state);
            cv.put("city",city);
            cv.put("area",area);
            cv.put("address",address);
            cv.put("what",what);
            cv.put("nearby",nearby);
            cv.put("type_prop_furn",type_prop_furn);
            cv.put("price",price);
            cv.put("prop_type",prop_type);
            cv.put("bhk_type",bhk_type);
            cv.put("description",desc);
            cv.put("upload_date",up_date);
            cv.put("last_update",last_up);
            cv.put("views",view);
            cv.put("status",status);
            cv.put("img1",img1);
            cv.put("img2",img2);
            cv.put("img3",img3);
            cv.put("img4",img4);
            db.insert("tbl_sell_prop",null,cv);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public boolean prop_rent_insert(String OID,String state,String city,String area,String address,String what,String nearby,String type_prop_furn,String rent,String deposite,String prop_type,String bhk_type,String desc,String up_date,String last_up,String view,String status,byte[] img1,byte[] img2,byte[] img3,byte[] img4)
    {
        SQLiteDatabase db;
        try {
            db=this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("O_ID",OID);
            cv.put("state",state);
            cv.put("city",city);
            cv.put("area",area);
            cv.put("address",address);
            cv.put("what",what);
            cv.put("nearby",nearby);
            cv.put("type_prop_furn",type_prop_furn);
            cv.put("rent",rent);
            cv.put("deposite",deposite);
            cv.put("prop_type",prop_type);
            cv.put("bhk_type",bhk_type);
            cv.put("description",desc);
            cv.put("upload_date",up_date);
            cv.put("last_update",last_up);
            cv.put("views",view);
            cv.put("status",status);
            cv.put("img1",img1);
            cv.put("img2",img2);
            cv.put("img3",img3);
            cv.put("img4",img4);
            db.insert("tbl_rent_prop",null,cv);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public List<String> get_data() // for to see info
    {
        SQLiteDatabase db = getReadableDatabase();
        String select_query = "select * from "+tbl_tenant;
        Cursor c = db.rawQuery(select_query,null);
        List<String> list = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount();i++)
        {
            list.add(c.getString(0) + c.getString(1) + c.getString(2)+ c.getString(3)+ c.getString(4)+ c.getString(5)+ c.getString(6)+ c.getString(7)+ c.getString(8));
            c.moveToNext();
        }
        c.close();
        return list;
    }
    public List<String> get_data_owner() // for to see info
    {
        SQLiteDatabase db = getReadableDatabase();
        String select_query = "select * from "+tbl_owner;
        Cursor c = db.rawQuery(select_query,null);
        List<String> list = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount();i++)
        {
            list.add(c.getString(0) + c.getString(1) + c.getString(2)+ c.getString(3)+ c.getString(4)+ c.getString(5));
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public Cursor O_login_data(String email,String password) // for to see info
    {
        int i=0;
        SQLiteDatabase db = getReadableDatabase();
        //String query_2="select * from tbl_owner where email='xyz@gmail.com' and password='12345'";
        String query = "select * from "+tbl_owner+" where email= '"+email+"' and "+" password= '"+password+"';";
        Cursor c;
        c=db.rawQuery(query,null);
        return c;
    }
    public Cursor T_login_data(String email,String password)// for to see info
    {
        int i=0;
        SQLiteDatabase db = getReadableDatabase();
        //String query_2="select * from tbl_owner where email='xyz@gmail.com' and password='12345'";
        String query = "select * from "+tbl_tenant+" where email= '"+email+"' and "+" password= '"+password+"';";
        Cursor c;
        c=db.rawQuery(query,null);
        return c;
    }
    public Boolean Owner_Check_email(String email) // check not repeat email
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_owner+" where email='"+email+"'",null);
        if(c.getCount()>=1)
            return true;
        else
            return false;
    }
    public Boolean tenent_Check_email(String email) // check not repeat email
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_tenant+" where email='"+email+"'",null);
        if(c.getCount()>=1)
            return true;
        else
            return false;
    }
    public boolean Owner_password_reset(String email,String pass)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("update "+tbl_owner+" set password='"+pass+"' where email='"+email+"'",null);
        //db.execSQL("update "+tbl_owner+" set password='"+pass+"' where email='"+email+"'");
        //db.execSQL("delete from "+tbl_owner+" where ID=10" );
        if(c.getCount()>=1)
            return true;
        else
            return false;
    }

    public boolean Tenent_password_reset(String email,String pass)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("update "+tbl_tenant+" set password='"+pass+"' where email='"+email+"'",null);
        //db.execSQL("update "+tbl_owner+" set password='"+pass+"' where email='"+email+"'");
        //db.execSQL("delete from "+tbl_owner+" where ID=10" );
        if(c.getCount()>=1)
            return true;
        else
            return false;
    }
    public void del_owner() // for to delete info
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from "+tbl_owner);

    }
    public void del_ten() // for to delete info
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from "+tbl_tenant);

    }
    public void del_prop()
    {
        SQLiteDatabase db=getWritableDatabase();
       // db.execSQL("delete from "+tbl_owner);

    }
    public void owner_update_profile(String first,String last,String mobile,String email)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update tbl_owner set firstname='"+first+"',lastname='"+last+"',phone='"+mobile+"' where email='"+email+"'");
    }

    public List<String> view_sell_prop(String state, String city, String area)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_sell_insert,null);
        List<String> list = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount();i++)
        {
            list.add(c.getString(0) + c.getString(1) + c.getString(2)+ c.getString(3)+ c.getString(4)+ c.getString(5)+c.getString(6) + c.getString(7) + c.getString(8)+ c.getString(9)+ c.getString(10)+ c.getString(11)+c.getString(12) + c.getString(13) + c.getString(14)+ c.getString(15)+ c.getString(16)+ c.getString(17));
            c.moveToNext();
        }
        c.close();
        return list;
    }
    public List<String> view_rent_prop(String state, String city, String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_rent_insert,null);
        List<String> list = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount();i++)
        {
            list.add(c.getString(0) + c.getString(1) + c.getString(2)+ c.getString(3)+ c.getString(4)+ c.getString(5)+c.getString(6) + c.getString(7) + c.getString(8)+ c.getString(9)+ c.getString(10)+ c.getString(11)+c.getString(12) + c.getString(13) + c.getString(14)+ c.getString(15)+ c.getString(16)+ c.getString(17)+c.getString(18));
            c.moveToNext();
        }
        return list;
    }
    //---------------------------------------------Sell Tenent Recycler-----------------------------------------------------------------------
    public Cursor get_prop_sell_data_count(String state,String city,String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_sell_insert+" where state='"+state+"' and city='"+city+"' order by price + 0 ",null);
     //   Cursor c1=db.rawQuery("select * from "+tbl_sell_insert+" where state="+sta"    ",null);
        return  c;
    }
    public ArrayList get_sell_prop_data(int pos,String state,String city,String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_sell_insert+" where state='"+state+"' and city='"+city+"'  order by price + 0 ",null);
        c.moveToFirst();
        ArrayList al=new ArrayList<String>();
        for(int i=0;i<c.getCount();i++)
        {
            if(i==pos) {
                al.add(c.getString(0));  //prop_id
                al.add(c.getString(5));  // address
                al.add(c.getString(7));  // nearby
                al.add(c.getString(8));  //funrniture_type
                al.add(c.getString(9)); // price
                al.add(c.getString(10));  // prop type ,indepe,row,flat
                al.add(c.getString(11));  // bhk type
                al.add(c.getBlob(17));   // image 1
                break;
            }c.moveToNext();
        }
        return al;
    }

    public ArrayList desc_get_sell_prop_data(int pos,String state,String city,String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_sell_insert+" where state='"+state+"' and city='"+city+"'  order by price + 0 DESC",null);
        c.moveToFirst();
        ArrayList al=new ArrayList<String>();
        for(int i=0;i<c.getCount();i++)
        {
            if(i==pos) {
                al.add(c.getString(0));  //prop_id
                al.add(c.getString(5));  // address
                al.add(c.getString(7));  // nearby
                al.add(c.getString(8));  //funrniture_type
                al.add(c.getString(9)); // price
                al.add(c.getString(10));  // prop type ,indepe,row,flat
                al.add(c.getString(11));  // bhk type
                al.add(c.getBlob(17));   // image 1
                break;
            }c.moveToNext();
        }
        return al;
    }
    public Cursor get_sell_prop_data_img(String propid) // add (   String State,String city,String area  )
    {
            SQLiteDatabase db = getReadableDatabase();
            String q="select img1,img2,img3,img4 from tbl_sell_prop where Prop_ID="+propid;
            Cursor c = db.rawQuery(q,null);
            return c;
    }
    //---------------------------------------------Rent Tenent Recycler-----------------------------------------------------------------------
    public Cursor get_prop_rent_data_count(String state,String city,String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_rent_insert+" where state='"+state+"' and city='"+city+"' order by rent + 0 ",null);
        //   Cursor c1=db.rawQuery("select * from "+tbl_sell_insert+" where state="+sta"    ",null);
        return  c;
    }
    public ArrayList get_rent_prop_data(int pos,String state,String city,String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_rent_insert+"  where state='"+state+"' and city='"+city+"' order by rent + 0 ",null);
        c.moveToFirst();
        ArrayList al=new ArrayList<String>();
        for(int i=0;i<c.getCount();i++)
        {
            if(i==pos) {
                al.add(c.getString(0));  //prop_id      0
                al.add(c.getString(5));  // address     1
                al.add(c.getString(7));  // nearby      2
                al.add(c.getString(9)); // rent         3
                al.add(c.getString(10));  // deposit   4
                al.add(c.getString(11));  // prop type  5
                al.add(c.getString(12));  // bhk type   6
                al.add(c.getBlob(17));   // image 1     7
                break;
            }c.moveToNext();
        }
        return al;
    }
    public ArrayList desc_get_rent_prop_data(int pos,String state,String city,String area)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_rent_insert+"  where state='"+state+"' and city='"+city+"' order by rent + 0 DESC",null);
        c.moveToFirst();
        ArrayList al=new ArrayList<String>();
        for(int i=0;i<c.getCount();i++)
        {
            if(i==pos) {
                al.add(c.getString(0));  //prop_id      0
                al.add(c.getString(5));  // address     1
                al.add(c.getString(7));  // nearby      2
                al.add(c.getString(9)); // rent         3
                al.add(c.getString(10));  // deposit   4
                al.add(c.getString(11));  // prop type  5
                al.add(c.getString(12));  // bhk type   6
                al.add(c.getBlob(17));   // image 1     7
                break;
            }c.moveToNext();
        }
        return al;
    }
    public Cursor get_rent_prop_data_img(String propid) // add (   String State,String city,String area  )
    {
        SQLiteDatabase db = getReadableDatabase();
        String q="select img1,img2,img3,img4 from tbl_rent_prop where Prop_ID="+propid;
        Cursor c = db.rawQuery(q,null);
        return c;
    }

    //-----------------------------------------------Sell Owner Recycler-------------------------------------------------------------------

    public Cursor rcv_O_sell_personal_count(int O_ID)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_sell_insert+" where O_ID="+O_ID,null);
        return  c;
    }
    public ArrayList rcv_O_sell_personal(int O_ID,int pos)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_sell_insert+" where O_ID="+O_ID,null);
        c.moveToFirst();
        ArrayList al=new ArrayList<String>();
        for(int i=0;i<c.getCount();i++)
        {
            if(i==pos) {
                al.add(c.getString(0));  //  Prop_id
                al.add(c.getString(1));  //  o_id
                al.add(c.getString(11));  //  bhk
                al.add(c.getString(3));  //  city
                al.add(c.getString(9)); //  price
                al.add(c.getString(4)); //  area
                al.add(c.getString(5));   //  address
                break;
            }c.moveToNext();
        }
        return al;
    }
    public Boolean rcv_O_sell_personal_delete(String prod_id)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("delete from "+tbl_sell_insert+" where Prop_ID='"+prod_id+"'",null);
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    //-----------------------------------------------Rent Owner Recycler-------------------------------------------------------------------

    public Cursor rcv_O_rent_personal_count(int O_ID)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_rent_insert+" where O_ID="+O_ID,null);
        return  c;
    }
    public ArrayList rcv_O_rent_personal(int O_ID,int pos)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_rent_insert+" where O_ID="+O_ID,null);
        c.moveToFirst();
        ArrayList al=new ArrayList<String>();
        for(int i=0;i<c.getCount();i++)
        {
            if(i==pos) {
                al.add(c.getString(0));  //  Prop_id
                al.add(c.getString(1));  //  o_id
                al.add(c.getString(12));  //  bhk
                al.add(c.getString(3));  //  city
                al.add(c.getString(9)); //  price
                al.add(c.getString(4)); //  area
                al.add(c.getString(5));   //  address
                break;
            }c.moveToNext();
        }
        return al;
    }
    public Boolean rcv_O_rent_personal_delete(String prod_id)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("delete from "+tbl_rent_insert+" where Prop_ID='"+prod_id+"'",null);
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    //--------------------------------------------------------property SELL deatils-------------------------------------------------------------------------------------

    public Cursor sell_prop_details(int prop_id)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("Select *from "+tbl_sell_insert+" where Prop_ID="+prop_id,null);
        return c;
    }

    //--------------------------------------------------------property RENT deatils-------------------------------------------------------------------------------------

    public Cursor rent_prop_details(int prop_id)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("Select * from "+tbl_rent_insert+" where Prop_ID="+prop_id,null);
        return c;
    }
    //---------------------------------------------------Owner_Contact--------------------------------------------------------------
    public Cursor get_owner_contact(String oid)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_owner+" where ID="+oid,null);
        return c;
    }
    //---------------------------------------------------For Contacts-------------------------------------------------------------------
    public Cursor get_sub_r_conts(String email)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tbl_tenant+" where email='"+email+"'",null);
        c.moveToFirst();
        return c;
    }
    //---------------------------------------------------Edit no of contact--------------------------------------------------------------
    public void up_sub_type(String email)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+tbl_tenant+" SET sub_type='premium' WHERE email='"+email+"'");
    }
    public void set_conts_one_less(String email,int conts)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+tbl_tenant+" SET r_conts="+conts+" WHERE email='"+email+"'");
    }
    public int set_conts_get_r_conts(String email)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("Select r_conts from tbl_user where email='"+email+"'",null);
        c.moveToFirst();
        int i=c.getInt(0);
        i+=20;
        return i;
    }
    public void set_conts_add_20(String email,int i)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+tbl_tenant+" SET r_conts="+i+" WHERE email='"+email+"'");
    }
    //------------------------------------------------------TEMP--------------------------------------------------------------------------
    public void temp()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+tbl_tenant+" SET sub_type='premium' WHERE email='jugalbhagat17.2@gmail.com'");
        System.out.println("---------------done------------------");
    }
    public void temp2()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE from "+tbl_tenant+" where email='jugalbhagat17.3@gmail.com'");
        System.out.println("---------------done------------------");
    }
}
