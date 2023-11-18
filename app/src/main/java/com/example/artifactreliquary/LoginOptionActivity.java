package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LoginOptionActivity extends AppCompatActivity {
    static int activeUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        activeUserID = getIntent().getIntExtra("activeUserID", 0);
    }

    public static Intent getIntent(Context context, double activeUserID){
        Intent intent = new Intent(context, LoginOptionActivity.class);
        intent.putExtra("activeUserID", activeUserID);
        return intent;
    }
}