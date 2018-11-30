package com.example.harith.shak.db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.harith.shak.R;

import java.util.ArrayList;


public class LecAdapter extends ArrayAdapter<Users> {


    public LecAdapter(Context fragmentOne, ArrayList<Users> users) {
        super(fragmentOne,0,users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listview = convertView;
        if (listview == null) {
            listview = LayoutInflater.from(getContext()).inflate(R.layout.lec_list,parent,false);
        }


        Users users = getItem(position);

        TextView username = listview.findViewById(R.id.lec_name);
        username.setText(users.getName());

        TextView pass = listview.findViewById(R.id.lec_sub);
        pass.setText(String.valueOf(users.getPassword()));

        TextView phone = listview.findViewById(R.id.lec_date);
        phone.setText(String.valueOf(users.getID()));

        return listview;
    }
}
