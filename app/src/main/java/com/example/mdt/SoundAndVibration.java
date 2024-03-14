package com.example.mdt;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SoundAndVibration extends AppCompatActivity {

    private AudioManager audioManager;
    private Vibrator vibrator;
    private SeekBar soundVolumeSeekBar;
    private SeekBar vibrationIntensitySeekBar;
    private Button checkSoundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_and_vibration);

        // Initialize AudioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Initialize Vibrator
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Initialize UI components
        soundVolumeSeekBar = findViewById(R.id.soundVolumeSeekBar);
        vibrationIntensitySeekBar = findViewById(R.id.vibrationIntensitySeekBar);
        checkSoundButton = findViewById(R.id.checkSoundButton);

        // Set max volume for sound volume SeekBar
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        soundVolumeSeekBar.setMax(maxVolume);

        // Set initial progress for sound volume SeekBar
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundVolumeSeekBar.setProgress(currentVolume);

        // Set max intensity for vibration intensity SeekBar
        vibrationIntensitySeekBar.setMax(100);

        // Set initial progress for vibration intensity SeekBar
        vibrationIntensitySeekBar.setProgress(50);

        // Set listeners
        soundVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Adjust sound volume when SeekBar progress changes
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        vibrationIntensitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Adjust vibration intensity when SeekBar progress changes
                int intensity = seekBar.getProgress();
                vibrator.vibrate(intensity);
            }
        });

        checkSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform sound checking functionality
                if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
                    Toast.makeText(SoundAndVibration.this, "Sound is ON", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SoundAndVibration.this, "Sound is OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
