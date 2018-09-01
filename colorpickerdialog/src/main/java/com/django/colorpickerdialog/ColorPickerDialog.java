package com.django.colorpickerdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPickerDialog extends Dialog {
    private Context context;
    private IColorPickerReceivable receivable;

    private Button buttonPositive;
    private Button buttonNegative;

    private SeekBar seekR;
    private SeekBar seekG;
    private SeekBar seekB;

    private TextView textR;
    private TextView textG;
    private TextView textB;

    private View viewCanvas;
    private int colour = 0;

    public ColorPickerDialog(@NonNull Context context, @NonNull IColorPickerReceivable receivable) {
        super(context);
        this.context = context;
        this.receivable = receivable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.picker_main);

        viewCanvas = findViewById(R.id.viewCanvas);

        buttonNegative = findViewById(R.id.buttonNegative);
        buttonPositive = findViewById(R.id.buttonPositive);

        seekR = findViewById(R.id.seekR);
        textR = findViewById(R.id.textR);

        seekG = findViewById(R.id.seekG);
        textG = findViewById(R.id.textG);

        seekB = findViewById(R.id.seekB);
        textB = findViewById(R.id.textB);

        initSeekListeners();
        initButtonListeners();

        onValueUpdated();
    }

    private void onValueUpdated() {
        colour = Color.argb(255, seekR.getProgress(), seekG.getProgress(), seekB.getProgress());
        viewCanvas.setBackgroundColor(colour);
    }

    private void initSeekListeners() {
        seekR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textR.setText(String.valueOf(i));
                onValueUpdated();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textG.setText(String.valueOf(i));
                onValueUpdated();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textB.setText(String.valueOf(i));
                onValueUpdated();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initButtonListeners() {
        buttonNegative.setOnClickListener(view -> dismiss());
        buttonPositive.setOnClickListener(view -> {
            receivable.onColorChosen(colour);
            dismiss();
        });
    }
}
