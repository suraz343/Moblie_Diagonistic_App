package com.example.mdt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MobileInfo extends AppCompatActivity {

    private static final int REQUEST_READ_PHONE_STATE = 101;

    private TextView textViewDeviceName;
    private TextView textViewIMEINumber;
    private TextView textViewModelNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_info);

        textViewDeviceName = findViewById(R.id.textViewDeviceName);
        textViewIMEINumber = findViewById(R.id.textViewIMEINumber);
        textViewModelNumber = findViewById(R.id.textViewModelNumber);

        if (checkPermission()) {
            // Permission granted, fetch device information
            fetchMobileInfo();
        } else {
            // Permission not granted, request permission
            requestPermission();
        }
    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                REQUEST_READ_PHONE_STATE);
    }

    private void fetchMobileInfo() {
        // Get device name
        String deviceName = Build.MANUFACTURER + " " + Build.MODEL;
        textViewDeviceName.setText("Device Name: " + deviceName);

        // Get IMEI number
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                String imeiNumber = telephonyManager.getDeviceId();
                textViewIMEINumber.setText("IMEI Number: " + imeiNumber);
            } else {
                textViewIMEINumber.setText("IMEI Number: Permission Denied");
            }
        } else {
            textViewIMEINumber.setText("IMEI Number: Not Available");
        }

        // Get model number
        String modelNumber = Build.MODEL;
        textViewModelNumber.setText("Model Number: " + modelNumber);

        // You can add more code to fetch and display other device information as needed
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch device information
                fetchMobileInfo();
            } else {
                // Permission denied, show a message or handle it gracefully
                Toast.makeText(this, "Permission denied. Some information may not be available.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
