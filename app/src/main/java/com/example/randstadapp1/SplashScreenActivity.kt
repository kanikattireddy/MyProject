package com.example.randstadapp1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        var splashTextView = findViewById<TextView>(R.id.textViewSplash)

        //  Coroutine to update splashTextView text with a delay
        GlobalScope.launch {
            for (i in 1..5) {
                delay(1000)
                launch(Dispatchers.Main) {
                    splashTextView.setText("launching.... $i")
                }
            }
        }
        //Use Thread to wait for 5 seconds before launching MainActivity
        thread {
            Thread.sleep(5000)

            var myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }
}