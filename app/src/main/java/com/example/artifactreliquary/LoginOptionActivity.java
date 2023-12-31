package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artifactreliquary.databinding.ActivityLoginOptionBinding;

import java.util.List;

public class LoginOptionActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button logInButton, createAccountButton;

    ActivityLoginOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        binding = ActivityLoginOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameInput = binding.usernameEditText;
        passwordInput = binding.passWordEditText;

        logInButton = binding.logInButton;
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToFind = usernameInput.getText().toString();
                String passwordToMatch = passwordInput.getText().toString();
                if (userToFind.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please input a username", Toast.LENGTH_LONG).show();
                } else {
                    List<User> userList = MainActivity.userDAO.getUserByUsername(userToFind);
                    if (userList.size() == 0) {
                        Toast.makeText(getApplicationContext(), "No user with the username " + userToFind, Toast.LENGTH_LONG).show();
                    } else {
                        if (!userList.get(0).getPassword().equals(passwordToMatch)) {
                            Toast.makeText(getApplicationContext(), "Password for " + userToFind + " does not match", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Successful Log In", Toast.LENGTH_SHORT).show();
                            MainActivity.userDAO.updateActiveUser(1,userList.get(0).getUserID());


                            Intent intent = AccountOptionsActivity.getIntent(getApplicationContext());
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        createAccountButton = binding.createAccountButton;
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                if(username.length()>0){
                    if(MainActivity.userDAO.getUserByUsername(username).size()>0){
                        Toast.makeText(getApplicationContext(), username+" already in use", Toast.LENGTH_SHORT).show();
                    }else{
                        if(password.length()>0){
                            User nuevo = new User(username, password, true, false);
                            MainActivity.userDAO.insert(nuevo);
                            Intent intent = AccountOptionsActivity.getIntent(getApplicationContext());
                            Toast.makeText(getApplicationContext(), "Successful account creation", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Please input a password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please input a username", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LoginOptionActivity.class);
        return intent;
    }
}