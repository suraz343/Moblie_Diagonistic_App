package com.example.mdt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BatteryStatus extends AppCompatActivity {

    private TextView batteryStatusTextView;
    private BroadcastReceiver batteryInfoReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_status);

        batteryStatusTextView = findViewById(R.id.batteryStatusTextView);

        // Register BroadcastReceiver to listen for battery status updates
        registerBatteryInfoReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister BroadcastReceiver when activity is destroyed to avoid memory leaks
        unregisterBatteryInfoReceiver();
    }

    private void registerBatteryInfoReceiver() {
        batteryInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int batteryScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryPercentage = (int) ((batteryLevel / (float) batteryScale) * 100);

                int batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                String batteryStatusString = "";
                if (batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
                    batteryStatusString = "Charging";
                } else if (batteryStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    batteryStatusString = "Discharging";
                } else if (batteryStatus == BatteryManager.BATTERY_STATUS_FULL) {
                    batteryStatusString = "Full";
                } else if (batteryStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    batteryStatusString = "Not Charging";
                }

                int batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
                String batteryHealthString = "";
                if (batteryHealth == BatteryManager.BATTERY_HEALTH_GOOD) {
                    batteryHealthString = "Good";
                } else if (batteryHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                    batteryHealthString = "Overheat";
                } else if (batteryHealth == BatteryManager.BATTERY_HEALTH_DEAD) {
                    batteryHealthString = "Dead";
                } else if (batteryHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                    batteryHealthString = "Over Voltage";
                }

                int batteryPlugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                String batteryPluggedString = "";
                if (batteryPlugged == BatteryManager.BATTERY_PLUGGED_AC) {
                    batteryPluggedString = "AC";
                } else if (batteryPlugged == BatteryManager.BATTERY_PLUGGED_USB) {
                    batteryPluggedString = "USB";
                } else if (batteryPlugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                    batteryPluggedString = "Wireless";
                }

                int batteryTemperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                float batteryTempCelsius = batteryTemperature / 10.0f;

                int batteryVoltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

                batteryStatusTextView.setText("Battery Level: " + batteryPercentage + "%\n" +
                        "Battery Status: " + batteryStatusString + "\n" +
                        "Battery Health: " + batteryHealthString + "\n" +
                        "Battery Plugged: " + batteryPluggedString + "\n" +
                        "Battery Temperature: " + batteryTempCelsius + "°C\n" +
                        "Battery Voltage: " + batteryVoltage + "mV");
            }
        };

        // Register the receiver to listen for battery status changes
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryInfoReceiver, filter);
    }

    private void unregisterBatteryInfoReceiver() {
        // Unregister the receiver to avoid memory leaks
        if (batteryInfoReceiver != null) {
            unregisterReceiver(batteryInfoReceiver);
            batteryInfoReceiver = null;
        }
    }
}

