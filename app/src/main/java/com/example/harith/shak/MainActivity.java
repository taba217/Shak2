package com.example.harith.shak;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.harith.shak.Fragment.FragmentOne;
import com.example.harith.shak.Fragment.PagerAdapter;
import com.example.harith.shak.db.DataBaseHelper;
import com.example.harith.shak.service.myservice;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper helper;
    public static String locat,topic,sh_id,time,st,id;
    public static double v =0, h = 0;
    public static int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //taba217
        //==================================================================
        startService(new Intent(this, myservice.class));
        jsonParse1();
        //==================================================================
         helper = new DataBaseHelper(this);
         helper.insertUser();
         displayDataBaseInfo();


        LoadFragment(new FragmentOne());

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
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
//taba217 work::::::
//=====================================================================================
public void jsonParse1() {
    String url1 = "http://zad.epizy.com/getlec.php";
    String url = "http://192.168.43.128/zad/getlec.php";
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("جاري التحديث...");
    progressDialog.show();
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("emp");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject lectures = jsonArray.getJSONObject(i);
                            locat = lectures.getString("locat");
                            v = lectures.getDouble("v");
                            h = lectures.getDouble("h");
                            sh_id = lectures.getString("sh_name");
                            topic = lectures.getString("topic");
                            locat = lectures.getString("locat");
                            time = lectures.getString("time");
                            status = lectures.getInt("status");
                           }
                         progressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                       progressDialog.dismiss();
                    }
                }

            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            progressDialog.dismiss();




        }
    });

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(request);
}
    public void maploc() {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("v", v);
        i.putExtra("h", h);
        i.putExtra("title", locat);
        startActivity(i);
    }

    protected void onDestroy() {

        Intent restartService = new Intent("RestartService");
        sendBroadcast(restartService);
        super.onDestroy();
    }

  //  ====================================================================================



    private void LoadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag, fragment)
                .commit();
    }

    public void displayDataBaseInfo() {
        DataBaseHelper helper = new DataBaseHelper(this);


        try {

            SQLiteDatabase database = helper.getReadableDatabase();

            Cursor c = database.rawQuery("SELECT * FROM users",null);


            Toast.makeText(MainActivity.this,"NUM " + c.getCount() ,Toast.LENGTH_LONG).show();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
