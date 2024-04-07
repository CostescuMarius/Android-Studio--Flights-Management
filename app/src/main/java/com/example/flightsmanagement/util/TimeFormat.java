package com.example.flightsmanagement.util;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;

public class TimeFormat {
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat onlyDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat intervalFormat = new SimpleDateFormat("hh:mm");
}
