package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.artifactreliquary.databinding.ActivityAccountOptionsBinding;
import com.example.artifactreliquary.databinding.ActivityLoginOptionBinding;

import java.util.List;

public class AccountOptionsActivity extends AppCompatActivity {
    ActivityAccountOptionsBinding binding;

    Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);

        binding = ActivityAccountOptionsBinding.inflate(getLayoutInflater());
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
        Intent intent = new Intent(context, AccountOptionsActivity.class);
        return intent;
    }
}