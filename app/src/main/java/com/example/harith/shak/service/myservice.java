package com.example.harith.shak.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harith.shak.MainActivity;
import com.example.harith.shak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by taba on 6/14/2018.
 */

public class myservice extends Service {
   public static  int notify=0,s0=0,s1;
    String temp="";
    boolean isrunning=false;
    ArrayList<Integer> statusSum=new ArrayList<>();
    private RequestQueue mQueue;
    public NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this);

    @Override
    public void onCreate() {
      //  Toast.makeText(this,"service created"+notify+":",Toast.LENGTH_LONG).show();
        mQueue = Volley.newRequestQueue(this);
        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        s0 = sharedPref.getInt("mySetting", s0);
        super.onCreate();
    }
    Handler mHandler = new Handler();
    Runnable mHandlerTask = new Runnable(){
        @Override
        public void run() {
            jsonParse();
            if (!isrunning) {
                noti();
            }
            mHandler.postDelayed(mHandlerTask,5000);
        }
    };
    void noti(){
        if(s1!=s0)
            Notify(jsonParse());
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        isrunning=false;
        mHandlerTask.run();
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandlerTask.run();

        Toast.makeText(this,"service startedComm"+notify+":",Toast.LENGTH_LONG).show();
        /*mQueue = Volley.newRequestQueue(this);
        //
        Log.i("","KEEPALIVE onCreate start command");
        Intent notificationIntent = new Intent(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_HOME);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        final Notification notification = new Notification.Builder(this)
                .setContentTitle(getText(R.string.app_name))
                .setContentText("Service is running")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        Uri notify= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final Ringtone r= RingtoneManager.getRingtone(getApplicationContext(),notify);
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
*/

        // startForeground(1337, notification);
        return START_STICKY;
        // return START_STICKY;
    }

    public String jsonParse() {

        String url1 = "http://taba217.000webhostapp.com/notifytest.php";
        String url = "http://192.168.43.128/notifytest.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("emp");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                temp = employee.getString("n");
                                s1 = employee.getInt("s1");

                                // textView.append(firstName + ", " + String.valueOf(age) + ", " + mail + String.valueOf(notify) + "\n\n");
                            }
                         //   if (s0==0)
                                s0=s1;
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

        return temp;
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this,"stoped",Toast.LENGTH_LONG).show();
        isrunning=false;
        mHandler.removeCallbacks(mHandlerTask);
        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("mySetting", s1);
        editor.apply();

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void Notify(String s) {
        //Create the intent that’ll fire when the user taps the notification//
        s0=s1;
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mBuilder.setContentTitle("دروس");
        switch (s) {
            case "add":
                mBuilder.setContentText("تمت اضافة درس جديد" + notify + ":" + temp);

                Intent intent1 = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pendingIntent1);
                break;
            case "c":
                mBuilder.setContentText("تم إلغاء محاضرة الشيخ" + notify);

                Intent intent2 = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pendingIntent2);
                break;
            case "ex":
                mBuilder.setContentText("بقي 10 ايام لإنتهاء النسخة التجريبية" + notify);
                mBuilder.setContentIntent(pendingIntent);
                break;
            case "ano":
                mBuilder.setContentText("إعلان محاضرة" +s0+"::"+s1);
                Intent intent4 = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent4 = PendingIntent.getActivity(this, 0, intent4, PendingIntent.FLAG_UPDATE_CURRENT);


                mBuilder.setContentIntent(pendingIntent4);
                break;
        }
        // mBuilder.setSound();
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri notify= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r= RingtoneManager.getRingtone(getApplicationContext(),notify);
        r.play();
        assert mNotificationManager != null;
        mNotificationManager.notify(001, mBuilder.build());
    }
}


