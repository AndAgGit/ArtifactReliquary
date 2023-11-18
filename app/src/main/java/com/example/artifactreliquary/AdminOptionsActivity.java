package com.example.artifactreliquary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artifactreliquary.databinding.ActivityAccountOptionsBinding;
import com.example.artifactreliquary.databinding.ActivityAdminOptionsBinding;

import java.util.List;

public class AdminOptionsActivity extends AppCompatActivity {
    ActivityAdminOptionsBinding binding;

    Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);

        binding = ActivityAdminOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        logOutButton = binding.logOutButton;
        logOutButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                List<User> userList = MainActivity.userDAO.getActiveUser();
                User toUpdate = userList.get(0);
                toUpdate.setActive(false);
                MainActivity.userDAO.update(toUpdate);

                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);

                return false;
            }
        });
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AdminOptionsActivity.class);
        return intent;
    }
}