package com.example.harith.shak.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.example.harith.shak.R;
import com.example.harith.shak.db.DataBaseHelper;
import com.example.harith.shak.db.LecAdapter;
import com.example.harith.shak.db.Users;

import java.util.ArrayList;


public class FragmentOne extends Fragment {



    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        DataBaseHelper helper = new DataBaseHelper(getContext());

        ArrayList<Users> num = helper.getAllUser();

        LecAdapter adapter = new LecAdapter(getContext(),num);

        ListView listView = view.findViewById(R.id.list);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }




}
