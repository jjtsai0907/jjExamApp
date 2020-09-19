package com.example.examapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class QuestionActivity : AppCompatActivity() {

    lateinit var currentCountryTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        currentCountryTV = findViewById(R.id.currentCountryTextView)

        var currentCountry = intent.getStringExtra("currentCountry")

        currentCountryTV.setText(currentCountry)

        Toast.makeText(this, currentCountry, Toast.LENGTH_LONG).show()
    }
}