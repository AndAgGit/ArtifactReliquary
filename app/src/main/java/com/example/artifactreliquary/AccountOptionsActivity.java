package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.artifactreliquary.databinding.ActivityAccountOptionsBinding;
import com.example.artifactreliquary.databinding.ActivityLoginOptionBinding;

public class AccountOptionsActivity extends AppCompatActivity {
    ActivityAccountOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);

        binding = ActivityAccountOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AccountOptionsActivity.class);
        return intent;
    }
}