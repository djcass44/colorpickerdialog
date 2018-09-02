package com.django.colorpickerdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.django.colorpickerdialog.util.ColourUtils;

public class ColorPickerDialog extends Dialog {
    private Context context;
    private IColorPickerReceivable receivable;
    private String textPositive;
    private String textNegative;
    private int startColour;
    private int defaultColour;

    private Button buttonPositive;
    private Button buttonNegative;
    private Button buttonNeutral;

    private SeekBar seekR;
    private SeekBar seekG;
    private SeekBar seekB;

    private TextView textR;
    private TextView textG;
    private TextView textB;

    private View viewCanvas;
    private int colour = 0;

    private final String INSTANCE_COLOUR = "COLOUR";

    private ColorPickerDialog(@NonNull Context context, @NonNull IColorPickerReceivable receivable, String textPositive, String textNegative, int startColour, int defaultColour) {
        super(context);
        this.context = context;
        this.receivable = receivable;
        this.textPositive = textPositive;
        this.textNegative = textNegative;
        this.startColour = startColour;
        this.defaultColour = defaultColour;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.picker_main);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);

        viewCanvas = findViewById(R.id.viewCanvas);

        buttonNegative = findViewById(R.id.buttonNegative);
        buttonPositive = findViewById(R.id.buttonPositive);
        buttonNeutral = findViewById(R.id.buttonNeutral);
        Log.d(getClass().getSimpleName(), "onCreate: " + defaultColour);
        buttonNeutral.setVisibility(defaultColour != -1 ? View.VISIBLE : View.GONE);

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

    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        Bundle bundle = super.onSaveInstanceState();
        bundle.putInt(INSTANCE_COLOUR, colour);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        startColour = savedInstanceState.getInt(INSTANCE_COLOUR, Color.WHITE);
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
        buttonNeutral.setOnClickListener(view -> {
            startColour = defaultColour;
            setInitialValues();
        });
    }
    public static class Builder {
        private Context context;
        private IColorPickerReceivable receivable;
        private String textPositive;
        private String textNegative;
        private int startColour = Color.WHITE;
        private int defaultColour = -1;

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

        public Builder setDefaultColour(int defaultColour) {
            this.defaultColour = defaultColour;
            return this;
        }

        public ColorPickerDialog build() {
            if(textPositive == null || textPositive.isEmpty())
                textPositive = context.getString(android.R.string.ok);
            if(textNegative == null || textNegative.isEmpty())
                textNegative = context.getString(android.R.string.cancel);
            return new ColorPickerDialog(context, receivable, textPositive, textNegative, startColour, defaultColour);
        }
    }
}