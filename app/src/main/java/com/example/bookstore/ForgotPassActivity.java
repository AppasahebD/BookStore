package com.example.bookstore;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import com.example.bookstore.databinding.ActivityForgotPassBinding;
import com.example.bookstore.viewmodel.ForgotPassVM;

public class ForgotPassActivity extends CommonActivity {

    private ActivityForgotPassBinding binding;
    private ForgotPassVM forgotPassVM;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_pass);
        forgotPassVM = new ViewModelProvider(this).get(ForgotPassVM.class);
        binding.setViewmodel(forgotPassVM);
        setUpLiveData();
    }

    private void setUpLiveData() {
        forgotPassVM.snackBarMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showMsgPrimaryColor(s);
            }
        });
    }
}
