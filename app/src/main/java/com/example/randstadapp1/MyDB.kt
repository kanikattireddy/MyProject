package com.example.randstadapp1

import androidx.room.Database
import androidx.room.RoomDatabase
// Define MyDB class, which represents the Room database
@Database(entities = [MyEntity::class],version = 1)
abstract  class MyDB : RoomDatabase(){
    // Declare an abstract function to provide access to DAO (Data Access Object)
    public abstract  fun myDao() : MyDAOInterface
}