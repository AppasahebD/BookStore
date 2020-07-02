package com.example.bookstore.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.bookstore.CommonActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassVM extends CommonViewModel{
    public MutableLiveData<String> email = new MutableLiveData<>();
    private Application application;
    private FirebaseAuth firebaseAuth;

    public ForgotPassVM(@NonNull Application application) {
        super(application);
        this.application = application;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void onSendEmailLink(){
        firebaseAuth.sendPasswordResetEmail(email.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                snackBarMessage.setValue("Send email link to you are register email");
                ((CommonActivity)application.getApplicationContext()).finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                snackBarMessage.setValue(e.getMessage());

            }
        });
    }

    public void onCancelClick(){
        ((CommonActivity)application.getApplicationContext()).finish();
    }
}
