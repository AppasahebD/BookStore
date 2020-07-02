package com.example.bookstore.MVVM;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.Map;


/**
 * Created by Chetan on 16/8/18.
 */
public class Navigator {

    private Map<String, Serializable> dataToSend;
    private int requestCode;
    private Bundle bundle;
    private Intent implicitIntent;


    @NonNull
    private Class destination;
    @NonNull
    private NavigationAction navigationAction;

    /**
     * @param destination destination
     * @param bundle      bundle
     */
    public Navigator(@NonNull NavigationAction navigationAction, @NonNull Class destination, Bundle bundle) {
        this.bundle = bundle;
        this.destination = destination;
        this.navigationAction = navigationAction;
    }

    /**
     * @param destination destination
     * @param bundle      bundle
     * @param requestCode requestCode
     */
    public Navigator(@NonNull Class destination, Bundle bundle, int requestCode) {
        this.bundle = bundle;
        this.destination = destination;
        this.requestCode = requestCode;
        this.navigationAction = NavigationAction.startActivityForResult;
    }

    public Navigator(Map<String, Serializable> dataToSend) {
        this.navigationAction = NavigationAction.sendResult;
        this.dataToSend = dataToSend;
    }

    public Navigator() {
        this.navigationAction = NavigationAction.finishCurrent;
    }

    public Navigator(@NonNull NavigationAction navigationAction, Intent intent, int requestCode) {
        this.implicitIntent = intent;
        this.navigationAction = navigationAction;
        this.requestCode = requestCode;
    }

    public Map<String, Serializable> getDataToSend() {
        return dataToSend;
    }

    @NonNull
    public Class getDestination() {
        return destination;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public int getRequestCode() {
        return requestCode;
    }

    @NonNull
    public NavigationAction getNavigationAction() {
        return navigationAction;
    }

    public Intent getImplicitIntent() {
        return implicitIntent;
    }

    public enum NavigationAction {
        startActivity, startActivityFinishCurrent, startActivityForResult, sendResult, finishCurrent, implicitIntent, startActivityWithCloseAll
    }
}
