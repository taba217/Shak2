package com.example.harith.shak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.harith.shak.MainActivity.h;
import static com.example.harith.shak.MainActivity.locat;
import static com.example.harith.shak.MainActivity.v;

public class ShProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sh_profile);
    }

    public void maploc() {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("v", v);
        i.putExtra("h", h);
        i.putExtra("title", locat);
        startActivity(i);
    }
}
