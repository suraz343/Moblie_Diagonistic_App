package com.example.mdt;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NetworkSignal extends AppCompatActivity {

    private TextView networkStatusTextView;
    private TextView simOperatorTextView;
    private TextView simCountryTextView;
    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_signal);

        networkStatusTextView = findViewById(R.id.networkStatusTextView);
        simOperatorTextView = findViewById(R.id.simOperatorTextView);
        simCountryTextView = findViewById(R.id.simCountryTextView);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        // Display SIM Operator and Country information
        String simOperator = "SIM Operator: " + telephonyManager.getSimOperatorName();
        simOperatorTextView.setText(simOperator);

        String simCountry = "SIM Country: " + telephonyManager.getSimCountryIso();
        simCountryTextView.setText(simCountry);

        // Create a PhoneStateListener to listen for signal strength changes
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                // Get the signal strength in dBm
                int signalStrengthValue = getSignalStrength(signalStrength);
                // Update the network status TextView with the signal strength
                updateNetworkStatus(signalStrengthValue);
            }
        };

        // Register the PhoneStateListener to listen for signal strength changes
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the PhoneStateListener when the activity is destroyed
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    private int getSignalStrength(SignalStrength signalStrength) {
        if (signalStrength == null) return 0;
        if (telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE)
            return signalStrength.getLevel(); // LTE signal strength is already in dBm
        else {
            // For other network types, calculate the approximate signal strength in dBm
            int cdmaDbm = signalStrength.getCdmaDbm();
            int evdoDbm = signalStrength.getEvdoDbm();
            return (cdmaDbm > 0 && evdoDbm > 0) ? Math.max(cdmaDbm, evdoDbm) : Math.max(cdmaDbm, evdoDbm);
        }
    }

    private void updateNetworkStatus(int signalStrengthValue) {
        String networkStatus;
        if (signalStrengthValue == 0) {
            networkStatus = "No Signal";
        } else {
            networkStatus = "Signal Strength: " + signalStrengthValue + " dBm";
        }
        networkStatusTextView.setText(networkStatus);
    }
}
