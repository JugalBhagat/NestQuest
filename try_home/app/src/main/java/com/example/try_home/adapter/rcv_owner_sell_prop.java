package com.example.try_home.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.try_home.R;
import com.example.try_home.SqlOp;

import java.util.ArrayList;


public class rcv_owner_sell_prop extends RecyclerView.Adapter<rcv_owner_sell_prop.ViewHolder> {
    private Context context;
    private int O_ID;

   // private int oid=sp.getString("own_login_oid",null);

    public rcv_owner_sell_prop(Context context) {
        this.context = context;
        SharedPreferences sp=context.getSharedPreferences("owner_login",MODE_PRIVATE);
        O_ID=Integer.parseInt(sp.getString("own_login_oid",null));
    }

    @NonNull
    @Override
    public rcv_owner_sell_prop.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_up_card_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull rcv_owner_sell_prop.ViewHolder holder, int position) {
        SqlOp x = new SqlOp(context.getApplicationContext());
        ArrayList al=x.rcv_O_sell_personal(O_ID,position);

        holder.tv_Prod_ID.setText("Property_Id : "+String.valueOf(al.get(0)));
        holder.tv_bhk.setText(String.valueOf(al.get(2)));
        holder.tv_city.setText(String.valueOf(al.get(3)));
        holder.tv_price.setText(String.valueOf(al.get(4))+" ₹");
        holder.tv_area.setText("Area : "+String.valueOf(al.get(5)));
        holder.tv_address.setText(String.valueOf(al.get(6)));
        holder.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlOp obj=new SqlOp(context.getApplicationContext());
                boolean r=obj.rcv_O_sell_personal_delete(String.valueOf(al.get(0)));
                Toast.makeText(context,"Data deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        SqlOp x=new SqlOp(context.getApplicationContext());
        Cursor c=x.rcv_O_sell_personal_count(O_ID);
        return c.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_Prod_ID,tv_city,tv_area,tv_bhk,tv_price,tv_address;
        ImageButton ib;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_Prod_ID=itemView.findViewById(R.id.txt_prop_id);
            tv_city=itemView.findViewById(R.id.txt_city);
            tv_area=itemView.findViewById(R.id.txt_area);
            tv_price=itemView.findViewById(R.id.txt_price);
            tv_bhk=itemView.findViewById(R.id.txt_bhk_type);
            tv_address=itemView.findViewById(R.id.txt_address);
            ib=itemView.findViewById(R.id.img_del);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
