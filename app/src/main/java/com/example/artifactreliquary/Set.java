package com.example.artifactreliquary;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "sets")
public class Set {
    @PrimaryKey(autoGenerate = true)
    private int setID;

    @NonNull
    private String name;

    @NonNull
    private String twoPiece;

    @NonNull
    private String fourPiece;

    @NonNull
    private int starMin;

    @NonNull
    private int starMax;

    public Set(@NonNull String name, @NonNull String twoPiece, @NonNull String fourPiece, @NonNull int starMin, @NonNull int starMax) {
        this.name = name;
        this.twoPiece = twoPiece;
        this.fourPiece = fourPiece;
        this.starMin = starMin;
        this.starMax = starMax;
    }

    public int getStarMax() {
        return starMax;
    }

    public void setStarMax(int starMax) {
        this.starMax = starMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return setID == set.setID && starMin == set.starMin && starMax == set.starMax && name.equals(set.name) && twoPiece.equals(set.twoPiece) && fourPiece.equals(set.fourPiece);
    }

    @Override
    public String toString() {
        return "Set{" +
                "setID=" + setID +
                ", name='" + name + '\'' +
                ", twoPiece='" + twoPiece + '\'' +
                ", fourPiece='" + fourPiece + '\'' +
                ", starMin=" + starMin +
                ", starMax=" + starMax +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(setID, name, twoPiece, fourPiece, starMin, starMax);
    }

    public int getStarMin() {
        return starMin;
    }

    public void setStarMin(int starMin) {
        this.starMin = starMin;
    }

    public int getSetID() {
        return setID;
    }

    public void setSetID(int setID) {
        this.setID = setID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getTwoPiece() {
        return twoPiece;
    }

    public void setTwoPiece(@NonNull String twoPiece) {
        this.twoPiece = twoPiece;
    }

    @NonNull
    public String getFourPiece() {
        return fourPiece;
    }

    public void setFourPiece(@NonNull String fourPiece) {
        this.fourPiece = fourPiece;
    }

}
