package com.example.examapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import kotlin.random.Random.Default.nextInt

class QuestionActivity : AppCompatActivity() {

    lateinit var currentCountryTV: TextView
    lateinit var walletTextView: TextView
    lateinit var shoppingImageView: ImageButton
    lateinit var questionTextView: TextView
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    var wallet: Int = 0
    var walletFromMaps: Int = 0
    var trys: Int = 0
    var locationArray = mutableListOf<Int>(0,0,0,0)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        currentCountryTV = findViewById(R.id.currentCountryTextView)
        walletTextView = findViewById(R.id.walletTextView)
        shoppingImageView = findViewById(R.id.shoppingImageButton)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        questionTextView = findViewById(R.id.questionTextView)


        var answerList = listOf<String>(
            intent.getStringExtra("TAG_A1")!!,
            intent.getStringExtra("TAG_A2")!!,
            intent.getStringExtra("TAG_A3")!!,
            intent.getStringExtra("TAG_A4")!!
        )

        Log.d("!!answerList", answerList.toString())

        var buttonTextList = mutableListOf<String>("","","","")
        for (i in 0..3) {
            buttonTextList[locationArray[i]] = answerList[i]

        }

        Log.d("!!answerList", buttonTextList.toString())

        //var currentCountry = intent.getStringExtra("currentCountry")
        //var delimiter = ","
        //var splitTag = currentCountry!!.split(delimiter)
        questionTextView.setText(intent.getStringExtra("TAG_QUESTION"))
        button0.setText(buttonTextList[0])
        button1.setText(buttonTextList[1])
        button2.setText(buttonTextList[2])
        button3.setText(buttonTextList[3])



        //Log.d("!!!!", splitTag.toString())

        // To create random numbers
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








        //currentCountryTV.setText(splitTag[0])
        //button0.setText(buttonTextList[0])
        //button1.setText(buttonTextList[1])
        //button2.setText(buttonTextList[2])
        //button3.setText(buttonTextList[3])


        //walletFromMaps = intent.getIntExtra("WALLET_MAPS", 0)
        //Toast.makeText(this, walletFromMaps.toString(), Toast.LENGTH_LONG).show()
        //walletTextView.setText(walletFromMaps.toString())

    }

    /*fun goShopping (view: View){
        intent = Intent(this, ShoppingActivity::class.java)
        intent.putExtra("WALLET", wallet)


        startActivity(intent)
        Toast.makeText(this, "Go Shopping!!", Toast.LENGTH_SHORT).show()
    }

        //Toast.makeText(this, currentCountry, Toast.LENGTH_LONG).show()

    */
    fun goBackToMaps (view: View){
        //var intent = Intent(this, MapsActivity::class.java)
        //intent.putExtra("WALLET_QUESTION", wallet)
        //Toast.makeText(this, wallet.toString(), Toast.LENGTH_LONG).show()
        //startActivity(intent)
        finish()

    }

    fun checkAnswer (view: View){
        if (locationArray[0].toString() == view.tag.toString()){
            Toast.makeText(this,"Correct!", Toast.LENGTH_SHORT).show()
            view.isEnabled = false
            wallet = walletFromMaps
            wallet += (- (trys * 3000)) + 10000
            walletTextView.setText(wallet.toString())
            trys ++


        }else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
            wallet = walletFromMaps
            wallet += -(trys * 1000) - 3000
            walletTextView.setText(wallet.toString())
            view.isEnabled = false
            trys++
        }
    }



}