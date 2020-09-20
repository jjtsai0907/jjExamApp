package com.example.examapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random.Default.nextInt

class QuestionActivity : AppCompatActivity() {

    lateinit var currentCountryTV: TextView
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    var locationArray = mutableListOf<Int>(0,0,0,0)

fun checkAnswer (view: View){
    if (locationArray[0].toString() == view.tag.toString()){
        Toast.makeText(this,"Correct!", Toast.LENGTH_SHORT).show()
    }else {
        Toast.makeText(this,"Wrong!", Toast.LENGTH_SHORT).show()
    }
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        currentCountryTV = findViewById(R.id.currentCountryTextView)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        var currentCountry = intent.getStringExtra("currentCountry")
        var delimiter = ","
        var splitTag = currentCountry!!.split(delimiter)

        //Log.d("!!!!", splitTag.toString())

        locationArray = mutableListOf<Int>(0,0,0,0)
        locationArray[0] = Random().nextInt(4)
        locationArray[1] = Random().nextInt(4)
        locationArray[2] = Random().nextInt(4)
        locationArray[3] = Random().nextInt(4)
        while (locationArray[1] == locationArray[0]) {
            locationArray[1] = Random().nextInt(4)
        }
        while (locationArray[2] == locationArray[0] || locationArray[2] == locationArray[1]) {
            locationArray[2] = Random().nextInt(4)
        }
        while (locationArray[3] == locationArray[0] || locationArray[3] == locationArray[1] || locationArray[3] == locationArray[2]) {
            locationArray[3] = Random().nextInt(4)
        }


        var buttonTextList = mutableListOf<String>("","","","")


        for (i in 0..3) {
            buttonTextList[locationArray[i]] = splitTag[i + 1]

        }


        currentCountryTV.setText(splitTag[0])
        button0.setText(buttonTextList[0])
        button1.setText(buttonTextList[1])
        button2.setText(buttonTextList[2])
        button3.setText(buttonTextList[3])





    }



        //Toast.makeText(this, currentCountry, Toast.LENGTH_LONG).show()
    }
