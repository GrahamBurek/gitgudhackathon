package com.gitgud.hackathon;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class Connect extends IntentService {
    public Connect() {
        super("Connect");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startld) {

    }
}
