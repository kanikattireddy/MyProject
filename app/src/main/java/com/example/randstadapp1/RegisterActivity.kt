package com.example.randstadapp1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        var usernameEditText = findViewById<EditText>(R.id.editTextUserName)
        var phoneEditText = findViewById<EditText>(R.id.editTextPhone)
        var emailEditText = findViewById<EditText>(R.id.editTextEmail)
        var passwordEditText = findViewById<EditText>(R.id.editTextPassword)

        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        // Set up spinner for gender selection
        val genderOptions = arrayOf("Select","Male", "Female")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter

        // Create or open a Room database instance
        var db = Room.databaseBuilder(this, MyDB::class.java,"mydatabase")
            .fallbackToDestructiveMigration().build()
        var h = Handler()

        // Set click listener for register button
        registerButton.setOnClickListener {
            var UserName = usernameEditText.text.toString()
            var Password = passwordEditText.text.toString()

            //  coroutine to insert user data into the database
            GlobalScope.launch {
                var users = MyEntity()
                users.myname = UserName
                users.mypassword = Password
                db.myDao().saveData(users)
            }
            // Clear EditText fields after registration
            usernameEditText.setText("")
            passwordEditText.setText("")

            // Check if username and password are not empty before starting MainActivity
            if(UserName.isNotEmpty()&&Password.isNotEmpty())
            {
                var myIntent = Intent(this, MainActivity::class.java)
                startActivity(myIntent)
            }
        }
    }
}