package com.tribikram.myapplication.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tribikram on 5/8/2018.
 */

@Dao
public interface UserDAO {
    @Insert
    void insert( User user);

    @Query("Select * from user")
    List<User> getAllUsers();
}
