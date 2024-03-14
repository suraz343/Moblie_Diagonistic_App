package com.example.mdt;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv1, tv2, tv3;
    private Button btn;
    private ImageView im1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textView);
        btn = findViewById(R.id.button2);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        im1 = findViewById(R.id.imageView3);

        btn.setOnClickListener(this); // Set the OnClickListener for the button
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Services.class);
        startActivity(intent);
    }
}
