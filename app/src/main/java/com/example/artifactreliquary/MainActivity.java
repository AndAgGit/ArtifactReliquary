package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.artifactreliquary.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int activeUserID;

    ActivityMainBinding binding;

    Button wikiButton, collectionButton, loginButton;

    UserDAO userDAO;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activeUserID = 0;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wikiButton = binding.wikiButton;
        collectionButton = binding.collectionButton;

        loginButton = binding.loginButton;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginOptionActivity.getIntent(getApplicationContext(), activeUserID);
                startActivity(intent);
            }
        });

        userDAO = Room.databaseBuilder(this,
                AppDatabase.class,
                "Database")
                .allowMainThreadQueries()
                .build().getUserDAO();

        userList = userDAO.getUsers();
        if(userList.size()==0){
            System.out.println("Populating default users");
            userDAO.insert(new User("admin", "secretAdminPassword", false));
        }
    }

    public static Intent getIntent(Context context, double activeUserID){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("activeUserID", activeUserID);
        return intent;
    }}