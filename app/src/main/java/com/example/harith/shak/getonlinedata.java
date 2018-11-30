package com.example.harith.shak;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harith.shak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class getonlinedata extends AppCompatActivity {
  public double v =0, h = 0;
    public String s;
    TextView ttopic,tpalcetime,tsh_name;
    private RequestQueue mQueue;
public String locat,topic,sh_id,time,st,id;
String msg;
public int status;
RadioButton stat;
    ArrayList<String> lec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // setContentView(R.layout.getdata);
     //   ttopic=findViewById(R.id.topic);
       // tpalcetime=findViewById(R.id.placetime);
        //tsh_name=findViewById(R.id.sh_name);

    /*    locat=findViewById(R.id.locat);
        topic=findViewById(R.id.topic);
        date=findViewById(R.id.date);
        st=findViewById(R.id.msg);
        sh_name=findViewById(R.id.sh_name);
        stat=findViewById(R.id.stat);
       */// stat.setClickable(false);
       mQueue = Volley.newRequestQueue(this);
      // jsonParse();
     //   listitem();
/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "جاري جلب الموقع...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                maploc();
            }
        });
    */}

    public void maploc() {
        // jsonParse();
        //   if(status==1)
//        stat.setChecked(true);
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("v", v);
        i.putExtra("h", h);
        i.putExtra("title", locat);
        startActivity(i);
        // st.setText(msg+" "+v);
    }
  /*  void listitem(){
       ListView listView=findViewById(R.id);
        lec=new ArrayList<String>();
        lec.add("motaba");
        lec.add("motaba");
        lec.add("motaba");
        lec.add("motaba");
       // jsonParse();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,lec);
        listView.setAdapter(arrayAdapter);

}
*/
    public void jsonParse() {

        String url1 = "http://taba217.000webhostapp.com/artest1.php";
        String url= "http://taba217.000webhostapp.com/artest1.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("emp");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                               String firstName = employee.getString("fname");
                               int age = employee.getInt("sheakh_id");
                               String mail = employee.getString("lname");
                               int notify = employee.getInt("new");

                                lec.add(firstName);
                                //data1.add(age);

                                // textView.append(firstName + ", " + String.valueOf(age) + ", " + mail + String.valueOf(notify) + "\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
       // return firstName;
    }


    public void jsonParse1() {
        // mQueue = Volley.newRequestQueue(this);
        String url1 = "http://zad.epizy.com/getlec.php";
        String url = "http://192.168.43.128/zad/getlec.php";
        msg = "in json";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {msg=response.toString();
                            JSONArray jsonArray = response.getJSONArray("emp");
                            msg = "in onrespond";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject lectures = jsonArray.getJSONObject(i);

                               locat = lectures.getString("locat");
                                v = lectures.getDouble("v");
                                h = lectures.getDouble("h");
                                sh_id = lectures.getString("sh_name");
                                topic = lectures.getString("topic");
                               // msg = "in reseving";
                                locat = lectures.getString("locat");
                                time = lectures.getString("time");
                               status = lectures.getInt("status");
                                // notify = employee.getInt("new");
                              // textView.append(sh_id+" ,"+topic + ", " + String.valueOf(time) + ", " + locat +"\n\n");
                            }
                           // ttopic.setText(topic);
                            //tpalcetime.append(locat+"\n"+time);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            msg = ("error :" + e);
                            progressDialog.dismiss();
                          //ttopic.setText(response.toString());
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
               // msg = ("error :" + error);
                progressDialog.dismiss();
             //   ttopic.setText(v+msg);



            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}


