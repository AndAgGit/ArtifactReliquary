package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.artifactreliquary.databinding.ActivityFavoritesBinding;

import java.util.List;

public class Favorites extends AppCompatActivity {
    ActivityFavoritesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());

        User activeUser = MainActivity.userDAO.getActiveUser().get(0);
        List<Favorite> favoriteList = MainActivity.favoriteDAO.getUserFavorites(activeUser.getUserID());

        boolean alternate = true;
        for(Favorite fave: favoriteList){
            Button button = new Button(getApplicationContext());
            Set curSet = MainActivity.setDAO.getSetByID(fave.getSetID()).get(0);

            button.setText(curSet.getName());
            button.setTextSize(30);

            button.setWidth(dpToPx(500));
            button.setHeight(dpToPx(100));

            if(alternate){
                button.setAlpha(0.75f);
            }

            alternate = !alternate;


            button.setBackgroundColor(Color.parseColor("#ffcdcdff"));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = SetDisplay.getIntent(getApplicationContext());
                    intent.putExtra("artifactID", curSet.getSetID());
                    startActivity(intent);
                }
            });
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MainActivity.favoriteDAO.delete(MainActivity.userDAO.getActiveUser().get(0).getUserID(), curSet.getSetID());
                    button.setVisibility(View.GONE);
                    return true;
                }
            });

            ViewGroup insertPoint = findViewById(R.id.setsLinearLayout);
            insertPoint.addView(button);
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, Favorites.class);
        return intent;
    }

    public int dpToPx(int dp) {
        return (int)(dp * getResources().getDisplayMetrics().density);
    }
}