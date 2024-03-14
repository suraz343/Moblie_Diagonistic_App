package com.example.mdt;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SensorDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        LinearLayout sensorContainer = findViewById(R.id.sensorContainer);

        // Get the SensorManager
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            // Get the list of sensors
            List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

            for (Sensor sensor : sensorList) {
                // Create TextView to display sensor information
                TextView sensorTextView = new TextView(this);
                sensorTextView.setText("\n  Name: " + sensor.getName() + "\n" +
                        "  Type: " + sensor.getType() + "\n" +
                        "  Vendor: " + sensor.getVendor() + "\n" +
                        "  Version: " + sensor.getVersion() + "\n" +
                        "  Power: " + sensor.getPower() + " mA\n" +
                        "  Maximum Range: " + sensor.getMaximumRange() + "\n" +
                        "  Resolution: " + sensor.getResolution() + "\n" +
                        "  Minimum Delay: " + sensor.getMinDelay() + " µs\n" +
                        "  Is Wakeup Sensor: " + (sensor.isWakeUpSensor() ? "Yes" : "No"));

                // Add TextView to the LinearLayout
                sensorContainer.addView(sensorTextView);
            }
        }
    }
}
