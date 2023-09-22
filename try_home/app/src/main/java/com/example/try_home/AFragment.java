package com.example.try_home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AFragment extends Fragment {
    ListView lv;
    List<String> ls;
    Button btn_user;
       public AFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View v = inflater.inflate(R.layout.fragment_a, container, false);
       // btn_user = (Button) v.findViewById(R.id.user);
      //  ListView lv=(ListView) v.findViewById(R.id.lv);
        //SqlOp db=new SqlOp(getContext());
      //  return v;
        return null;
    }

    public void btn_onclick(View view) {
      //  Toast.makeText(getContext(),"hello ",Toast.LENGTH_SHORT).show();
        /*SqlOp db=new SqlOp(getContext());
        List<String> l =db.get_data();
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,l);
        lv.setAdapter(adp);*/
    }
}