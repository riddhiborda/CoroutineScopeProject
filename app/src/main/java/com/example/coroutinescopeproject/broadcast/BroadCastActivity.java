package com.example.coroutinescopeproject.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import com.example.coroutinescopeproject.R;
import com.example.coroutinescopeproject.databinding.ActivityBroadCastBinding;

public class BroadCastActivity extends AppCompatActivity {
    ActivityBroadCastBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityBroadCastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerReceiver(broadcastReceiver, new IntentFilter("broadCastName"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
    BroadcastReceiver broadcastReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle b = intent.getExtras();

            String message = b.getString("message");

            Log.e("newmesage", "" + message);
        }
    };







}