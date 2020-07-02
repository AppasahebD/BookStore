package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookstore.databinding.ActivityLoginBinding;
import com.example.bookstore.interfaces.IGoogleLogin;
import com.example.bookstore.viewmodel.Constant;
import com.example.bookstore.viewmodel.LoginVM;


public class LoginActivity extends CommonActivity {
    private ActivityLoginBinding activityLoginBinding;
    private LoginVM loginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginVM = new ViewModelProvider(this).get(LoginVM.class);
        loginVM.setOnClickListener(gLogin);
        activityLoginBinding.setLogin(loginVM);
        activityLoginBinding.buttonGSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginVM.onGoogleSignClick();

            }
        });
        setUpLiveData();
    }

    IGoogleLogin gLogin = new IGoogleLogin() {
        @Override
        public void onGoogleLogin(Intent googleIntent) {
            startActivityForResult(googleIntent, Constant.GOOGLE_SIGNIN_REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.GOOGLE_SIGNIN_REQUEST_CODE) {
            loginVM.checkGoogleLogin(data);
        }
    }

    public void setUpLiveData() {
        loginVM.snackBarMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showMsgPrimaryColor(s);
            }
        });

        loginVM.finishActivity.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean flag) {
                if (flag) {
                    finish();
                }
            }
        });
    }
}
