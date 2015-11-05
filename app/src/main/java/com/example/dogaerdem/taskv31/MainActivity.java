package com.example.dogaerdem.taskv31;


import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver1, filter1);
        filter1.addAction("android.net.conn.");
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mBroadcastReceiver2, filter2);
    }

    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Log.d("BroadcastActions", "Action " + action + "received");
            int state;

            switch (action) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                    if (state == BluetoothAdapter.STATE_OFF) {
                        button.setBackgroundColor(Color.RED);
                        button.setText("Bluetooth Kapalı");
                        Log.d("BroadcastActions", "Bluetooth Kapandı");
                    } else if (state == BluetoothAdapter.STATE_ON) {
                        button.setBackgroundColor(Color.GREEN);
                        button.setText("Bluetooth Acık");
                        Log.d("BroadcastActions", "Bluetooth Açık");
                    }
                    break;


                    }
                }
            };

    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("BroadcastActions", "Internet");
            if(isInternetAvailable()){
                if(button2 != null) {
                    button2.setBackgroundColor(Color.GREEN);
                    button2.setText("Internet Var");
                }
                Log.e("BroadcastActions", "Internet acik");

            }
            else{
                if (button2 != null) {
                    button2.setBackgroundColor(Color.RED);
                    button2.setText("Internet Yok");
                }
                Log.e("BroadcastActions", "Internet Kapali");
            }
        }
    };


        @Override
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(mBroadcastReceiver1);
            unregisterReceiver(mBroadcastReceiver2);
        }

    private boolean isInternetAvailable() {
        boolean result=true;
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                result= true;
            }else {
                result= false;
            }
        }
        catch (Exception e){
            result=false;
        }
        return result;
    }

}


