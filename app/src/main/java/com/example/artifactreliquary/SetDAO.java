package com.example.artifactreliquary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SetDAO {
    @Insert
    void insert(Set...sets);

    @Update
    void update(Set...sets);

    @Delete
    void delete(Set set);

    @Query("SELECT * FROM sets")
    List<Set> getSets();

    @Query("SELECT * FROM sets WHERE setID = :id")
    List<Set> getSetByID(int id);
}
