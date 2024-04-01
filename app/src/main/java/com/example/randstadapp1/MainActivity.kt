package com.example.randstadapp1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity()
{

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create or open a Room database instance
        var db = Room.databaseBuilder(this, MyDB::class.java,"mydatabase")
            .fallbackToDestructiveMigration().build()
        var h = Handler()

        // Find views by their IDs
        var  usernameEditTextText = findViewById<EditText>(R.id.editTextText5Username)
        var passwordEditTextText = findViewById<EditText>(R.id.editTextTextPassword3)
        var loginButton = findViewById<Button>(R.id.button4Login)
        var signupButton = findViewById<Button>(R.id.buttonSignup)
        val imageImageView = findViewById<ImageView>(R.id.imageViewImage)

        // Set click listener for login button
        loginButton.setOnClickListener {
            var username1 = usernameEditTextText.text.toString()
            var password1 = passwordEditTextText.text.toString()

            // Use a thread to query data from the database asynchronously
            thread {
                db.myDao().readData().forEach{
                    h.post {
                        var username2 = "${it.myname}"
                        var password2 = "${it.mypassword}"
                        // Check if username and password match with database records
                        if ((username1.equals(username2)) && (password1.equals(password2))) {
                            var myIntent = Intent(this, RecyclerViewActivity::class.java)
                            startActivity(myIntent)
                        }
                    }
                }
            }
        }
        // Set click listener for signup button
        signupButton.setOnClickListener{
            var myIntent = Intent(this,RegisterActivity::class.java)
            startActivity(myIntent)
        }
    }
}












