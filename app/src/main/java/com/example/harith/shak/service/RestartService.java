package com.example.harith.shak.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by taba on 7/30/2018.
 */

public class RestartService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        context.startService(new Intent(context,myservice.class));
    }
}
