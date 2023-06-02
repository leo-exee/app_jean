package com.example.sensmove.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sensmove.dao.UserDao;
import com.example.sensmove.entities.User;


@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "User";

    public static UserDatabase getDb(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    public abstract UserDao userDao();
}
