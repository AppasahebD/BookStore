package com.example.bookstore;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import com.example.bookstore.databinding.ActivityRegistrationBinding;
import com.example.bookstore.viewmodel.RegisterVM;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends CommonActivity {

    private FirebaseAuth firebaseAuth;
    private ActivityRegistrationBinding binding;
    private RegisterVM registerVM;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
        registerVM = new ViewModelProvider(this).get(RegisterVM.class);
        binding.setRegister(registerVM);
        setUpLiveData();
    }

    private void setUpLiveData() {
        registerVM.snackBarMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                shortMsg(s);
            }
        });
    }
}
