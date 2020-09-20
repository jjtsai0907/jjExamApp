package com.example.examapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuestionActivity : AppCompatActivity() {

    lateinit var currentCountryTV: TextView
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        currentCountryTV = findViewById(R.id.currentCountryTextView)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        var currentCountry = intent.getStringExtra("currentCountry")
        var delimiter =","
        var splitTag = currentCountry!!.split(delimiter)

        //Log.d("!!!!", splitTag.toString())

        currentCountryTV.setText(splitTag[0])
        button0.setText(splitTag[1])
        button1.setText(splitTag[2])
        button2.setText(splitTag[3])
        button3.setText(splitTag[4])



        //Toast.makeText(this, currentCountry, Toast.LENGTH_LONG).show()
    }
}