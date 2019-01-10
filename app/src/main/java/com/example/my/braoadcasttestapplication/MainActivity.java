package com.example.my.braoadcasttestapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter mIntentFilter;
    private NetWorkChangeReceiver mNetWorkChangeReceiver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntentFilter= new IntentFilter();
        mIntentFilter.addAction("android.net.com.CONNECTIVITY_CHANGE");
        mNetWorkChangeReceiver=new NetWorkChangeReceiver();
        registerReceiver(mNetWorkChangeReceiver,mIntentFilter);
    }
    protected  void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mNetWorkChangeReceiver);
    }
    class NetWorkChangeReceiver extends BroadcastReceiver{
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable())
            {
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }

           // Toast.makeText(context,"network changs",Toast.LENGTH_SHORT).show();
        }


    }
}
