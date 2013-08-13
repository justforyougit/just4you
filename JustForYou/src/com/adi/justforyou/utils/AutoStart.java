package com.adi.justforyou.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoStart extends BroadcastReceiver
{   
    Alarm alarm = new Alarm();
    @Override
    public void onReceive(Context context, Intent intent)
    {   
    	Intent startServiceIntent = new Intent(context, AlarmService.class);
        context.startService(startServiceIntent);
    }
}
