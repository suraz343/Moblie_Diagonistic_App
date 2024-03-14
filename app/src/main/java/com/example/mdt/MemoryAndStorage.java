package com.example.mdt;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class MemoryAndStorage extends AppCompatActivity {
    private TextView memoryInfoTextView;
    private TextView storageInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_and_storage);

        memoryInfoTextView = findViewById(R.id.memoryInfoTextView);
        storageInfoTextView = findViewById(R.id.storageInfoTextView);

        // Fetch and display memory info
        displayMemoryInfo();

        // Fetch and display storage info
        displayStorageInfo();
    }

    private void displayMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        String memoryInfo = "Total Memory: " + bytesToMegabytes(totalMemory) + " MB\n"
                + "Free Memory: " + bytesToMegabytes(freeMemory) + " MB\n"
                + "Used Memory: " + bytesToMegabytes(usedMemory) + " MB\n";

        memoryInfoTextView.setText(memoryInfo);
    }

    private void displayStorageInfo() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        long totalStorage = totalBlocks * blockSize;
        long availableStorage = availableBlocks * blockSize;
        long usedStorage = totalStorage - availableStorage;

        String storageInfo = "Total Storage: " + bytesToGigabytes(totalStorage) + " GB\n"
                + "Available Storage: " + bytesToGigabytes(availableStorage) + " GB\n"
                + "Used Storage: " + bytesToGigabytes(usedStorage) + " GB\n";

        storageInfoTextView.setText(storageInfo);
    }

    private double bytesToMegabytes(long bytes) {
        return bytes / (1024.0 * 1024.0);
    }

    private double bytesToGigabytes(long bytes) {
        return bytes / (1024.0 * 1024.0 * 1024.0);
    }
}
