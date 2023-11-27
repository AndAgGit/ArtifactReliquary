package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.artifactreliquary.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Button wikiButton, collectionButton, loginButton;

    public static UserDAO userDAO;
    List<User> userList;

    public static SetDAO setDAO;
    List<Set> setList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wikiButton = binding.wikiButton;
        wikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not ready yet", Toast.LENGTH_SHORT).show();
            }
        });

        collectionButton = binding.collectionButton;
        collectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not ready yet", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton = binding.accountButton;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = userDAO.getActiveUser();
                if(userList.size()==0){
                    Intent intent = LoginOptionActivity.getIntent(getApplicationContext());
                    startActivity(intent);
                }else {
                    Intent intent = AccountOptionsActivity.getIntent(getApplicationContext());
                    startActivity(intent);
                }
            }
        });

        userDAO = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "Database")
                .allowMainThreadQueries()
                .build().getUserDAO();

        userList = userDAO.getUsers();
        if (userList.size() == 0) {
            userDAO.insert(
                    //populating default users
                    new User("admin", "secretAdminPassword", false, true),
                    new User("user", "userPassword", false, false)
                    );
            Toast.makeText(getApplicationContext(), "Populating DB", Toast.LENGTH_LONG).show();
        }

        setDAO = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "Database")
                .allowMainThreadQueries()
                .build().getSetDAO();

        setList = setDAO.getSets();
        if (setList.size() == 0) {
            setDAO.insert(
                    //populating set table
            );
            Toast.makeText(getApplicationContext(), "Populating DB", Toast.LENGTH_LONG).show();
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}