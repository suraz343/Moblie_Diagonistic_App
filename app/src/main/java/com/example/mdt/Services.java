package com.example.mdt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Services extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        btn1 = findViewById(R.id.button20);
        btn2 = findViewById(R.id.button21);
        btn3 = findViewById(R.id.button22);
        btn4 = findViewById(R.id.button23);
        btn5 = findViewById(R.id.button24);
        btn6 = findViewById(R.id.button25);
        btn7 = findViewById(R.id.button26);
        btn8 = findViewById(R.id.button27);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMobileInfoActivity();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSoftwareDetailsActivity();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNetworkSignalActivity();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMemoryAndStorageActivity();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBatteryStatusActivity();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSoundAndVibrationActivity();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCameraTestActivity();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSensorDetailsActivity();
            }
        });
    }

    private void startMobileInfoActivity() {
        Intent mobile_info = new Intent(getApplicationContext(), MobileInfo.class);
        startActivity(mobile_info);
    }

    private void startSoftwareDetailsActivity() {
        Intent software_details = new Intent(getApplicationContext(), SoftwareDetails.class);
        startActivity(software_details);
    }

    private void startNetworkSignalActivity() {
        Intent network_signal = new Intent(getApplicationContext(), NetworkSignal.class);
        startActivity(network_signal);
    }

    private void startMemoryAndStorageActivity() {
        Intent battery_status = new Intent(getApplicationContext(), MemoryAndStorage.class);
        startActivity(battery_status);
    }

    private void startBatteryStatusActivity() {
        Intent memory_and_storage = new Intent(getApplicationContext(), BatteryStatus.class);
        startActivity(memory_and_storage);
    }

    private void startSoundAndVibrationActivity() {
        Intent sound_and_vibration = new Intent(getApplicationContext(), SoundAndVibration.class);
        startActivity(sound_and_vibration);
    }

    private void startCameraTestActivity() {
        Intent camera_test = new Intent(getApplicationContext(), CameraTest.class);
        startActivity(camera_test);
    }

    private void startSensorDetailsActivity() {
        Intent sensor_details = new Intent(getApplicationContext(), SensorDetails.class);
        startActivity(sensor_details);
    }
}
