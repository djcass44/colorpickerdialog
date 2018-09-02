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

import com.django.colorpickerdialog.util.ColourUtils;

public class ColorPickerDialog extends Dialog {
    private Context context;
    private IColorPickerReceivable receivable;
    private String textPositive;
    private String textNegative;
    private int startColour = Color.WHITE;

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

    private ColorPickerDialog(@NonNull Context context, @NonNull IColorPickerReceivable receivable, String textPositive, String textNegative, int startColour) {
        super(context);
        this.context = context;
        this.receivable = receivable;
        this.textPositive = textPositive;
        this.textNegative = textNegative;
        this.startColour = startColour;
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

        setInitialValues();
    }

    private void setInitialValues() {
        buttonPositive.setText(textPositive);
        buttonNegative.setText(textNegative);
        int[] colourBreakdown = ColourUtils.getColourBreakdown(startColour);
        seekR.setProgress(colourBreakdown[0]);
        seekG.setProgress(colourBreakdown[1]);
        seekB.setProgress(colourBreakdown[2]);
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
    public static class Builder {
        private Context context;
        private IColorPickerReceivable receivable;
        private String textPositive;
        private String textNegative;
        private int startColour = Color.WHITE;

        public Builder(@NonNull Context context, @NonNull IColorPickerReceivable receivable) {
            this.context = context;
            this.receivable = receivable;
        }

        public Builder setTextPositive(String textPositive) {
            this.textPositive = textPositive;
            return this;
        }

        public Builder setTextNegative(String textNegative) {
            this.textNegative = textNegative;
            return this;
        }

        public Builder setStartColour(int startColour) {
            this.startColour = startColour;
            return this;
        }
        public ColorPickerDialog build() {
            if(textPositive == null || textPositive.isEmpty())
                textPositive = context.getString(android.R.string.ok);
            if(textNegative == null || textNegative.isEmpty())
                textNegative = context.getString(android.R.string.cancel);
            return new ColorPickerDialog(context, receivable, textPositive, textNegative, startColour);
        }
    }
}