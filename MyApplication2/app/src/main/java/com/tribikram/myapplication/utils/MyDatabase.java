package com.tribikram.myapplication.utils;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tribikram.myapplication.models.User;
import com.tribikram.myapplication.models.UserDAO;

/**
 * Created by Tribikram on 5/8/2018.
 */
@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public static MyDatabase instance;
    public abstract UserDAO userDAO();

    public static MyDatabase getDatabase(Context context){
      if(instance == null){
          instance = Room.databaseBuilder(context, MyDatabase.class, "android_course").allowMainThreadQueries().build();
      }
      return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }
}
