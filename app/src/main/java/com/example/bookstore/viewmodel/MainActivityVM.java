package com.example.bookstore.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

public class MainActivityVM extends CommonViewModel {
    private  Context context;

    public MainActivityVM(@NonNull Application application) {
        super(application);
    }

//    public MainActivityVM(Context context) {
//       this.context = context;
//    }

    public void onLogOutClick(View view){


    }
}
