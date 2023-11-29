package com.example.artifactreliquary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artifactreliquary.databinding.ActivitySetsBinding;

public class SetsActivity extends AppCompatActivity {
    ActivitySetsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        binding = ActivitySetsBinding.inflate(getLayoutInflater());

        int setsNumber = MainActivity.setDAO.getSets().size();

        boolean alternate = true;
        for(int i=1;i<setsNumber+1;i++){
            String setName = MainActivity.setDAO.getSetByID(i).get(0).getName();

            Button button = new Button(getApplicationContext());
            button.setTextSize(20);
            button.setText(setName);
            button.setTextSize(30);

            button.setWidth(dpToPx(500));
            button.setHeight(dpToPx(100));

            if(alternate){
                button.setAlpha(0.75f);
            }

            alternate = !alternate;


            button.setBackgroundColor(Color.parseColor("#ffcdcdff"));

            final int temp = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = SetDisplay.getIntent(getApplicationContext());
                    intent.putExtra("artifactID", temp);
                    startActivity(intent);
                }
            });

            ViewGroup insertPoint = findViewById(R.id.setsLinearLayout);
            insertPoint.addView(button);
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