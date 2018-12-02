package com.example.harith.shak;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.harith.shak.Fragment.FragmentOne;
import com.example.harith.shak.Fragment.PagerAdapter;
import com.example.harith.shak.db.DataBaseHelper;
import com.example.harith.shak.db.LecAdapter;
import com.example.harith.shak.db.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper helper;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         helper = new DataBaseHelper(this);


         volleyreguest();

         LoadFragment(new FragmentOne());

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            volleyreguest();
//            }
//        },1000);

        TabLayout tabLayout =  findViewById(R.id.tab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager =  findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Toast.makeText(MainActivity.this, "" + tab.getPosition(), Toast.LENGTH_SHORT).show();
                if (tab.getPosition() == 2) {
                    volleyreguest();
                }
        }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    private void LoadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag, fragment)
                .commit();
    }



    public void volleyreguest(){


        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject root = new JSONObject(response);

                    JSONArray jsonArray = root.getJSONArray("users");

                    for (int i =0; i<jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(0);

                        name =  object.getString("name");
                        int password = object.getInt("id");

                        helper.insertUser(name,password,password,password);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Name: " + name, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(mStringRequest);
    }


}
