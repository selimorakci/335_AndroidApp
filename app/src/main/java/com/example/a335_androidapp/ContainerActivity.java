package com.example.a335_androidapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection(this);
    }

    public void checkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // checks if internet isn't available
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() != NetworkInfo.State.CONNECTED && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() != NetworkInfo.State.CONNECTED) {
            // if internet isn't available it shows the error view
            Intent intent = new Intent(context, OfflineActivity.class);
            context.startActivity(intent);
        }
    }
}
