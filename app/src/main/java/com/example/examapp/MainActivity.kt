package com.example.examapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goButton: Button = findViewById(R.id.goButton)


    }

    fun entryButtonClicked (view: View){
        val intent: Intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)

    }
}