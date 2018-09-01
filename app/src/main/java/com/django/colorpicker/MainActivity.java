package com.django.colorpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View viewCanvas = findViewById(R.id.viewCanvas);
        Button buttonMain = findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(view -> {

        });
    }
}
