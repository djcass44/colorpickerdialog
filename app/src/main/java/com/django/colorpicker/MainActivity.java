package com.django.colorpicker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.django.colorpickerdialog.ColorPickerDialog;
import com.django.colorpickerdialog.IColorPickerReceivable;

public class MainActivity extends AppCompatActivity implements IColorPickerReceivable {
    private View viewCanvas;
    private int lastColour = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewCanvas = findViewById(R.id.viewCanvas);
        Button buttonMain = findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(view -> {
            ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this, this)
                    .setStartColour(lastColour);
            ColorPickerDialog dialog = builder.build();
            dialog.show();
        });
    }

    @Override
    public void onColorChosen(int color) {
        viewCanvas.setBackgroundColor(color);
        lastColour = color;
    }
}
