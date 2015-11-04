package com.example.dogaerdem.taskv31;


import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver1, filter1);
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
        @Override
        protected void onDestroy() {
            super.onDestroy();

            unregisterReceiver(mBroadcastReceiver1);

            }
        }


