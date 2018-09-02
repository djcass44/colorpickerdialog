package com.django.colorpickerdialog.util;

public class ColourUtils {
    public static int[] getColourBreakdown(int colour) {
        int red = (colour >> 16) & 0xFF;
        int green = (colour >> 8) & 0xFF;
        int blue = colour & 0xFF;
        return new int[] { red, green, blue };
    }
}
