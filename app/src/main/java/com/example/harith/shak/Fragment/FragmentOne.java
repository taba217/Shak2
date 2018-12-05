package com.example.harith.shak.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harith.shak.MainActivity;
import com.example.harith.shak.MapsActivity;
import com.example.harith.shak.R;
import com.example.harith.shak.ShProfileActivity;
import com.example.harith.shak.db.DataBaseHelper;
import com.example.harith.shak.db.LecAdapter;
import com.example.harith.shak.db.Users;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;
import static com.example.harith.shak.MainActivity.v;


public class FragmentOne extends Fragment {
    ListView listView;


    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        DataBaseHelper helper = new DataBaseHelper(getContext());

       final ArrayList<Users> num = helper.getAllUser();

        LecAdapter adapter = new LecAdapter(getContext(), num);

        listView = view.findViewById(R.id.list);

        listView.setAdapter(adapter);



listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(getContext(),ShProfileActivity.class);
        Users rowdata=num.get(position);
        String f=rowdata.getName();
        i.putExtra(rowdata.getName(),"name");
        Toast.makeText(getContext(),f, Toast.LENGTH_SHORT).show();
         startActivity(i);
    }
});

        return view;}


}