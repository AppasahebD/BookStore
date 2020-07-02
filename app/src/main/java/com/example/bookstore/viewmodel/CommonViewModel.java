package com.example.bookstore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookstore.MVVM.NavigationUtils;

public class CommonViewModel extends AndroidViewModel implements Observable {
    public NavigationUtils navigationUtils = new NavigationUtils();
    public MutableLiveData<String> snackBarMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    public MutableLiveData<Boolean> finishActivity = new MutableLiveData<>();

    public CommonViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
