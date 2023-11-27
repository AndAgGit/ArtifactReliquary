package com.example.artifactreliquary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User...users);

    @Update
    void update(User...users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE userID = :id")
    List<User> getUserByID(int id);

    @Query("SELECT * FROM users WHERE username = :name")
    List<User> getUserByUsername(String name);

    @Query("SELECT * FROM users WHERE isActive = 1")
    List<User> getActiveUser();
}
