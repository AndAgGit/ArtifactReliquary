package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.artifactreliquary.databinding.ActivityLoginOptionBinding;
import com.example.artifactreliquary.databinding.ActivityMainBinding;

public class LoginOptionActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button logIn, createAccount;

    ActivityLoginOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        binding = ActivityLoginOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LoginOptionActivity.class);
        return intent;
    }
}