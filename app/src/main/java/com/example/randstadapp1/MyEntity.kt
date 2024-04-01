package com.example.randstadapp1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// MyEntity class defining which represents database Entity
@Entity
class MyEntity() {
    // Define primary key for the entity with auto-generation
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_column")
    var myid : Int = 0

    // Define column for storing name
    @ColumnInfo(name = "name_column")
    var myname : String = ""

    // Define column for storing password
    @ColumnInfo(name = "password_column")
    var mypassword : String  = ""
}