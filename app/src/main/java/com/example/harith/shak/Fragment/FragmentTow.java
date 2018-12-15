package com.example.harith.shak.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harith.shak.LecDetials;
import com.example.harith.shak.R;


public class FragmentTow extends Fragment {


    public FragmentTow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_tow, container, false);

        FloatingActionButton button = view.findViewById(R.id.openMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),LecDetials.class);
                startActivity(i);
            }
        });
        return view;
    }

}
