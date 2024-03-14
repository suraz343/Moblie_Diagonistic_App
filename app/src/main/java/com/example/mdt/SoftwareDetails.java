package com.example.mdt;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SoftwareDetails extends AppCompatActivity {
    private TextView androidVersionTextView, kernelVersionTextView, ramTextView, manufacturerTextView, modelTextView,
            internalStorageTextView, externalStorageTextView, buildIdTextView, buildTypeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_details);

        // Initialize TextViews
        androidVersionTextView = findViewById(R.id.textView13);
        kernelVersionTextView = findViewById(R.id.textView14);
        ramTextView = findViewById(R.id.textView15);
        manufacturerTextView = findViewById(R.id.textView16);
        modelTextView = findViewById(R.id.textView17);
        internalStorageTextView = findViewById(R.id.textView18);
        externalStorageTextView = findViewById(R.id.textView19);
        buildIdTextView = findViewById(R.id.textView20);
        buildTypeTextView = findViewById(R.id.textView21);

        // Fetch and display software details
        displaySoftwareDetails();
    }

    private void displaySoftwareDetails() {
        // Fetch Android version
        String androidVersion = "Android Version: " + Build.VERSION.RELEASE;
        androidVersionTextView.setText(androidVersion);

        // Fetch Kernel version
        String kernelVersion = "Kernel Version: " + System.getProperty("os.version");
        kernelVersionTextView.setText(kernelVersion);

        // Fetch RAM size
        Runtime runtime = Runtime.getRuntime();
        long ramSize = runtime.totalMemory();
        String ram = "RAM: " + Formatter.formatFileSize(this, ramSize);
        ramTextView.setText(ram);

        // Fetch manufacturer
        String manufacturer = "Manufacturer: " + Build.MANUFACTURER;
        manufacturerTextView.setText(manufacturer);

        // Fetch model
        String model = "Model: " + Build.MODEL;
        modelTextView.setText(model);

        // Fetch Internal Storage
        File internalStorage = Environment.getDataDirectory();
        StatFs stat = new StatFs(internalStorage.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        String internalStorageSize = "Internal Storage: " + Formatter.formatFileSize(this, totalBlocks * blockSize);
        internalStorageTextView.setText(internalStorageSize);

        // Fetch External Storage
        File externalStorage = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(externalStorage.getPath());
        long blockSizeExternal = statFs.getBlockSizeLong();
        long totalBlocksExternal = statFs.getBlockCountLong();
        String externalStorageSize = "External Storage: " + Formatter.formatFileSize(this, totalBlocksExternal * blockSizeExternal);
        externalStorageTextView.setText(externalStorageSize);

        // Fetch Build ID
        String buildId = "Build ID: " + Build.ID;
        buildIdTextView.setText(buildId);

        // Fetch Build Type
        String buildType = "Build Type: " + Build.TYPE;
        buildTypeTextView.setText(buildType);
    }
}
