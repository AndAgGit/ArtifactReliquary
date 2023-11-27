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
    private  String fourPiece;

    public Set(@NonNull String name, @NonNull String twoPiece, @NonNull String fourPiece) {
        this.name = name;
        this.twoPiece = twoPiece;
        this.fourPiece = fourPiece;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return setID == set.setID && name.equals(set.name) && twoPiece.equals(set.twoPiece) && fourPiece.equals(set.fourPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setID, name, twoPiece, fourPiece);
    }

    @Override
    public String toString() {
        return "Set{" +
                "setID=" + setID +
                ", name='" + name + '\'' +
                ", twoPiece='" + twoPiece + '\'' +
                ", fourPiece='" + fourPiece + '\'' +
                '}';
    }
}
