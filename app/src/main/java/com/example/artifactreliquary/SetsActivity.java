package com.example.artifactreliquary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artifactreliquary.databinding.ActivitySetsBinding;

import java.util.List;

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
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    List<User> userList = MainActivity.userDAO.getActiveUser();
                    if(!userList.isEmpty()){
                        List<Favorite> favoriteList = MainActivity.favoriteDAO.getUserFavorites(userList.get(0).getUserID());
                        Favorite fave = new Favorite(userList.get(0).getUserID(), temp);
                        if(!containsSet(favoriteList, temp)){
                            MainActivity.favoriteDAO.insert(fave);
                            Toast.makeText(getApplicationContext(),"Added "+setName+" to Favorite Sets",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Already in Favorite Sets",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Intent intent = LoginOptionActivity.getIntent(getApplicationContext());
                        Toast.makeText(getApplicationContext(),"Log in to set favorites",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                    return true;
                }
            });

            ViewGroup insertPoint = findViewById(R.id.setsLinearLayout);
            insertPoint.addView(button);
        }
    }

    private boolean containsSet(List<Favorite> list, int setID){
        for(Favorite fave: list){
            if(fave.getSetID()==setID){
                return true;
            }
        }
        return false;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SetsActivity.class);
        return intent;
    }

    public int dpToPx(int dp) {
        return (int)(dp * getResources().getDisplayMetrics().density);
    }
}