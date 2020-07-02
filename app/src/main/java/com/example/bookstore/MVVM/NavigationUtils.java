package com.example.bookstore.MVVM;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Chetan on 16/8/18.
 */
public class NavigationUtils extends MutableLiveData<Navigator> {

    @Override
    public void setValue(Navigator value) {
        super.setValue(value);
    }

    @Nullable
    @Override
    public Navigator getValue() {
        return super.getValue();
    }
}