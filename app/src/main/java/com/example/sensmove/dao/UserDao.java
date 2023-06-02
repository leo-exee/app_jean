package com.example.sensmove.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sensmove.entities.User;

import java.util.List;

@Dao
public interface UserDao {
   @Query("SELECT * FROM User WHERE email = :email")
   public User find(String email);

   @Query("SELECT * FROM User")
   public List<User> list();

   @Insert
   public void add(User...users);
   @Update
   public void update(User...users);
   @Delete
   public void delete(User...users);
}
