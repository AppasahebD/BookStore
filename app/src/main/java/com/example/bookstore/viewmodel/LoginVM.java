package com.example.bookstore.viewmodel;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bookstore.ApplicationEx;
import com.example.bookstore.CommonActivity;
import com.example.bookstore.ForgotPassActivity;
import com.example.bookstore.LoginActivity;
import com.example.bookstore.MainActivity;
import com.example.bookstore.RegistrationActivity;
import com.example.bookstore.SyncActivity;
import com.example.bookstore.interfaces.IGoogleLogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginVM extends CommonViewModel {
    private Application application;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();;
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private IGoogleLogin iGoogleLogin;

    public LoginVM(@NonNull Application application) {
        super(application);
        this.application = application;
        setUpGoogleSignIn();
    }

    public void onForgotPassword(){
        Log.d("","click");
        Intent intentForgot = new Intent(application.getApplicationContext(), ForgotPassActivity.class);
        application.getApplicationContext().startActivity(intentForgot);
    }

    private void setUpGoogleSignIn() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("780717446340-bqmh0p3un8723s4oq0r78tghm6ppts4o.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(application.getApplicationContext() , googleSignInOptions);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(application.getApplicationContext());

        if (googleSignInAccount != null || firebaseAuth.getCurrentUser() !=  null) {
            Intent intent = new Intent(application.getApplicationContext() , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            application.getApplicationContext().startActivity(intent);
            finishActivity.setValue(true);
        }
    }

    public void onLoginClick(){
        firebaseAuth.signInWithEmailAndPassword(email.getValue(),password.getValue()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(application.getApplicationContext() , SyncActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    application.getApplicationContext().startActivity(intent);
                    finishActivity.setValue(true);
                }else {
                    snackBarMessage.setValue(task.getException().getMessage());
                }

            }
        });
    }

    public void onGoogleSignClick(){
        Intent googleIntent = googleSignInClient.getSignInIntent();
        iGoogleLogin.onGoogleLogin(googleIntent);
    }

    public void onRegisterClick(){
        Log.i("Click","Click");
        Intent intent = new Intent(application.getApplicationContext(),RegistrationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        application.getApplicationContext().startActivity(intent);
    }

    public void checkGoogleLogin(Intent data) {
        Task<GoogleSignInAccount> signTask = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount signInAcc = signTask.getResult(ApiException.class);
            AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(),null);
            firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Intent intent = new Intent(application.getApplicationContext(), SyncActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    application.getApplicationContext().startActivity(intent);
                    finishActivity.setValue(true);
                }
            });
            snackBarMessage.setValue("Account Created");
        } catch (ApiException e) {
            snackBarMessage.setValue(e.getMessage());
        }
    }

    public void setOnClickListener(IGoogleLogin loginActivity) {
        iGoogleLogin = loginActivity;
    }
}
