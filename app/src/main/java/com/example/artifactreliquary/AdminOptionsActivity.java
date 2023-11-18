package com.example.artifactreliquary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artifactreliquary.databinding.ActivityAccountOptionsBinding;

public class AdminOptionsActivity extends AppCompatActivity {
    ActivityAccountOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);

        binding = ActivityAccountOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AdminOptionsActivity.class);
        return intent;
    }
}