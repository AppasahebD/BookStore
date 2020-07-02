package com.example.bookstore;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import androidx.core.content.res.ResourcesCompat;
import com.facebook.stetho.Stetho;


public class ApplicationEx extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);

    }

    public static Typeface getAppTypeface() {
        return ResourcesCompat.getFont(ApplicationEx.getContext(), R.font.app_font);
    }
    public static Context getContext() {
        return context;
    }
}
