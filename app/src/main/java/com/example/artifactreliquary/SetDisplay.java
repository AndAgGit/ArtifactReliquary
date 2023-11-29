package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.artifactreliquary.databinding.ActivitySetDisplay2Binding;

import java.util.List;

public class SetDisplay extends AppCompatActivity {
    int artifactID;

    TextView artifactName, twoPieceLabel, twoPieceEffect, fourPieceLabel, fourPieceEffect;

    ActivitySetDisplay2Binding binding;
    LinearLayout linearLayout;

    List<Set> artifactSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_display2);

        artifactID = getIntent().getIntExtra("artifactID", 1);
        artifactSet = MainActivity.setDAO.getSetByID(artifactID);
        binding = ActivitySetDisplay2Binding.inflate(getLayoutInflater());

        artifactName = binding.artifactName;

        linearLayout = binding.starsDisplay;
        //ideally this would show how many stars the artifact is
        for(int i=0;i<artifactSet.get(0).getStarMax();i++){
            ImageView star = new ImageView(getApplicationContext());
            star.setBackgroundResource(R.drawable.star_foreground);

            ViewGroup insertPoint = findViewById(R.id.starsDisplay);
            insertPoint.addView(star);
        }

        twoPieceLabel = binding.twoPieceLabel;
        twoPieceEffect = binding.twoPieceEffect;
        twoPieceEffect.setText(artifactSet.get(0).getTwoPiece());

        fourPieceLabel = binding.fourPieceLabel;
        fourPieceEffect = binding.fourPieceEffect;
        fourPieceEffect.setText(artifactSet.get(0).getFourPiece());

        artifactName.setText(artifactSet.get(0).getName());

        setContentView(binding.getRoot());
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SetDisplay.class);
        return intent;
    }
}