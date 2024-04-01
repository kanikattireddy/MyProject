package com.example.randstadapp1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


// Define MyDAOInterface, which serves as a Data Access Object (DAO) for MyEntity
@Dao
interface  MyDAOInterface{

    // Define a function to insert data into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveData(myEntity : MyEntity)

    // Define a function to read data from the database
    @Query("select * from MyEntity")
    fun readData() : List<MyEntity>
}