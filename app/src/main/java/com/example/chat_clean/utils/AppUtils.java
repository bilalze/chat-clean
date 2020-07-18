package com.example.chat_clean.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

/*
 * for showing toast messages could do without this might remove in fututre
 */
public class AppUtils {

    public static void showToast(Context context, @StringRes int text, boolean isLong) {
        showToast(context, context.getString(text), isLong);
    }

    public static void showToast(Context context, String text, boolean isLong) {
        Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}