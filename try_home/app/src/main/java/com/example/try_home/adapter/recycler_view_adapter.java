package com.example.try_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.try_home.MainActivity;
import com.example.try_home.R;
import com.example.try_home.SqlOp;
import com.example.try_home.sell_prop_detail;

import java.util.ArrayList;
import java.util.List;
public class recycler_view_adapter extends RecyclerView.Adapter<recycler_view_adapter.ViewHolder> {
    private Context context;
    private List list;
    private String state,city,area;
    private String furn="*",ptype="*",bhk="*";
    private long min_price=0,max_price=999999999;

    public recycler_view_adapter(Context context, List list,String state,String city,String area) {
        this.context = context;
        this.list = list;
        this.state=state;
        this.city=city;
    }
    public recycler_view_adapter(Context context, List list,String state,String city,String furn,String ptype,String bhk,int min_price,long max_price) {
        this.context = context;
        this.list = list;
        this.state=state;
        this.city=city;
        this.furn=furn;
        this.bhk=bhk;
        this.max_price=max_price;
        this.min_price=min_price;
        this.ptype=ptype;
    }
    @NonNull
    @Override
    //what to show(card)
    public recycler_view_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(v);

    }

    @Override
    //what to do after i get viewholder obj
    public void onBindViewHolder(@NonNull recycler_view_adapter.ViewHolder holder, int position) {
      //  sell_properties x= (sell_properties) list.get(position);
        SqlOp x = new SqlOp(context.getApplicationContext());
        //ArrayList al=x.get_sell_prop_data(position);
        //x.del_acc_owner();
        ArrayList al=x.get_sell_prop_data(position,state,city,area);

        holder.tv_bhk_type.setText(String.valueOf(al.get(6)));
        holder.tv_address.setText(String.valueOf(al.get(1)));
        holder.tv_nearby.setText(String.valueOf(al.get(2)));
        holder.tv_price.setText(String.valueOf(al.get(4))+" ₹");
        holder.tv_prop_furn.setText(String.valueOf(al.get(3)));
        holder.tv_prop_type.setText(String.valueOf(al.get(5)));

        Cursor c=x.get_sell_prop_data_img(String.valueOf(al.get(0)));
        c.moveToFirst();
        holder.img_pic1.setImageBitmap(byte_to_bitmap(c.getBlob(0)));

        holder.con_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(new Intent(context.getApplicationContext(), sell_prop_detail.class));
                i.putExtra("prop_detail_variable_what","Sell");
                i.putExtra("prop_detail_variable_prop_id",String.valueOf(al.get(0)));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.img_btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(new Intent(context.getApplicationContext(), sell_prop_detail.class));
                i.putExtra("prop_detail_variable_what","Sell");
                i.putExtra("prop_detail_variable_prop_id",String.valueOf(al.get(0)));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    // how many items
    public int getItemCount() {
        SqlOp x=new SqlOp(context.getApplicationContext());
        //int count=2;
        Cursor c=x.get_prop_sell_data_count(state,city,area);
        return c.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tv_bhk_type,tv_address,tv_price,tv_prop_type,tv_prop_furn,tv_nearby;
        ImageView img_pic1;
        ImageButton img_btn_contact;
        View con_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_bhk_type=itemView.findViewById(R.id.txt_bhk_type);
            tv_address=itemView.findViewById(R.id.txt_address);
            tv_nearby=itemView.findViewById(R.id.txt_nearby);
            img_pic1=itemView.findViewById(R.id.img);
            tv_price=itemView.findViewById(R.id.txt_price);
            tv_prop_furn=itemView.findViewById(R.id.txt_furniture);
            tv_prop_type=itemView.findViewById(R.id.txt_prop_type);
            con_layout=itemView.findViewById(R.id.constraint_lay);
            img_btn_contact=itemView.findViewById(R.id.contact_own);
        }

        @Override
        public void onClick(View v) {

        }

    }
    public Bitmap byte_to_bitmap(byte [] by)
    {
        return BitmapFactory.decodeByteArray(by,0,by.length);
    }
}
