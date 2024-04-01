package com.example.randstadapp1


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_view)
        // Retrieve DataClass object passed through intent extras
        val data = intent.getParcelableExtra<DataClass>("data")

        // Retrieve DataClass object passed through intent extras
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(data?.thumbnailUrl).into(imageView)

        // Set title text to TextView
        val titleTextView = findViewById<TextView>(R.id.textView)
        titleTextView.text = data?.title
    }
}

