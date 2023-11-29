package com.example.artifactreliquary;

import static java.security.AccessController.getContext;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.artifactreliquary.databinding.ActivitySetsBinding;

public class SetsActivity extends AppCompatActivity {
    ActivitySetsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        binding = ActivitySetsBinding.inflate(getLayoutInflater());

        int setsNumber = MainActivity.setDAO.getSets().size();

        LayoutInflater inflater = getLayoutInflater();
        boolean alternate = true;
        for(int i=1;i<setsNumber+1;i++){
            String setName = MainActivity.setDAO.getSetByID(i).get(0).getName();

            Button temp = new Button(getApplicationContext());
            temp.setTextSize(20);
            temp.setText(setName);
            temp.setTextSize(30);

            temp.setWidth(dpToPx(500));
            temp.setHeight(dpToPx(100));

            if(alternate){
                temp.setAlpha(0.75f);
            }

            alternate = !alternate;


            temp.setBackgroundColor(Color.parseColor("#ffcdcdff"));

            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SetsActivity.this, "Take me to "+setName, Toast.LENGTH_SHORT).show();
                }
            });

            ViewGroup insertPoint = (ViewGroup) findViewById(R.id.setsLinearLayout);
            insertPoint.addView(temp);
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SetsActivity.class);
        return intent;
    }

    public int dpToPx(int dp) {
        return (int)(dp * getResources().getDisplayMetrics().density);
    }

    public int spToPx(int sp) {
        return (int)(sp * getResources().getDisplayMetrics().scaledDensity);
    }
}