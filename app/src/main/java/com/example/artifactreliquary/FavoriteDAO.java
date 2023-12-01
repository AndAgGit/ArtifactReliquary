package com.example.artifactreliquary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert
    void insert(Favorite...favorites);

    @Update
    void update(Favorite...favorites);

    @Delete
    void delete(Favorite...favorites);

    @Query("SELECT * FROM favorites")
    List<Favorite> getFavorites();

    @Query("SELECT * FROM favorites WHERE userID = :userID")
    List<Favorite> getUserFavorites(int userID);

    @Query("DELETE FROM favorites WHERE userID = :userID AND setID = :setID")
    void delete(int userID, int setID);
}
