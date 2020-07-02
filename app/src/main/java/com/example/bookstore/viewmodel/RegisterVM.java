package com.example.bookstore.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.bookstore.CommonActivity;
import com.example.bookstore.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterVM extends CommonViewModel{

    private FirebaseAuth firebaseAuth;
    private Application application;
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password =new MutableLiveData<>();

    public RegisterVM(@NonNull Application application) {
        super(application);
        this.application = application;
        firebaseAuth = FirebaseAuth.getInstance();
    }

//    public RegisterVM(Context context) {
//        this.context = context;
//
//    }

    public void onClickRegister(){
        firebaseAuth.createUserWithEmailAndPassword(email.getValue(),password.getValue()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                     Intent intent = new Intent(application.getApplicationContext(), LoginActivity.class);
                     application.getApplicationContext().startActivity(intent);
                    ((CommonActivity)application.getApplicationContext()).finish();

                }else{
                    snackBarMessage.setValue(task.getException().getMessage());
                }

            }
        });
    }


}
