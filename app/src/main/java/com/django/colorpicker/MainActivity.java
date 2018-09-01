package com.django.colorpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.django.colorpickerdialog.ColorPickerDialog;
import com.django.colorpickerdialog.IColorPickerReceivable;

public class MainActivity extends AppCompatActivity implements IColorPickerReceivable {
    private View viewCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewCanvas = findViewById(R.id.viewCanvas);
        Button buttonMain = findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(view -> new ColorPickerDialog(this, this).show());
    }

    @Override
    public void onColorChosen(int color) {
        viewCanvas.setBackgroundColor(color);
    }
}
