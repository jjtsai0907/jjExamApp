package com.example.examapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.finish_dialog.*
import java.util.*


class QuestionActivity : MenuClass() {

    lateinit var walletTextView: TextView
    lateinit var questionTextView: TextView
    lateinit var questionImageView: ImageView
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    var trys: Int = 0
    var locationArray = mutableListOf<Int>(0,0,0,0)
    var ticketClickedPosition = 0
    lateinit var rightAnswerDialog: RightAnswerDialog
    lateinit var finishDialog: FinishDialog





    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        walletTextView = findViewById(R.id.walletTextView)
        //shoppingImageView = findViewById(R.id.shoppingImageButton)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        questionTextView = findViewById(R.id.questionTextView)
        questionImageView = findViewById(R.id.questionImageView)
        rightAnswerDialog = RightAnswerDialog(this)
        finishDialog = FinishDialog(this)



        var photo = intent.getIntExtra("TAG_QUESTION_BACKGROUND", R.drawable.norway)




        if(DataManager.totalQleft == 0){
            toMapsButton.alpha = 0.2F
            toMapsButton2.alpha = 0.2F
        }


        questionImageView.setImageResource(photo)
        walletTextView.text = "${DataManager.wallet}"



        var answerList = listOf<String>(
            intent.getStringExtra("TAG_A1")!!,
            intent.getStringExtra("TAG_A2")!!,
            intent.getStringExtra("TAG_A3")!!,
            intent.getStringExtra("TAG_A4")!!
        )


        // To create random numbers
        locationArray = mutableListOf<Int>(0,0,0,0)
        locationArray[0] = Random().nextInt(4)

        locationArray[1] = Random().nextInt(4)
        while (locationArray[1] == locationArray[0]) {
            locationArray[1] = Random().nextInt(4)
        }
        locationArray[2] = Random().nextInt(4)
        while (locationArray[2] == locationArray[0] || locationArray[2] == locationArray[1]) {
            locationArray[2] = Random().nextInt(4)
        }
        locationArray[3] = Random().nextInt(4)
        while (locationArray[3] == locationArray[0] || locationArray[3] == locationArray[1] ||
            locationArray[3] == locationArray[2]) {
            locationArray[3] = Random().nextInt(4)
        }


        var buttonTextList = mutableListOf<String>("","","","")
        for (i in 0..3) {
            buttonTextList[locationArray[i]] = answerList[i]
        }


        questionTextView.setText(intent.getStringExtra("TAG_QUESTION"))
        button0.setText(buttonTextList[0])
        button1.setText(buttonTextList[1])
        button2.setText(buttonTextList[2])
        button3.setText(buttonTextList[3])
    }


    // This is for what's gonna happen after coming back from ShoppingActivity through "finish()"
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        ticketClickedPosition = data!!.getIntExtra("TICKET_CLICKED_POSITION", 9)

        if(DataManager.nations[ticketClickedPosition].purchased && DataManager.clearMaps){
            // if one buys a ticket, then goes directly to Maps.
            var intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("TICKET_CLICKED_FROM_QUESTION", ticketClickedPosition)
            setResult(999,intent)
            finish()
        }
        else{
            // if one does not buy any ticket, then stays on this Activity.
            walletTextView.text = "${DataManager.wallet}"
        }
    }



    fun goShopping (view: View){
        var intent2 = Intent(this, ShoppingActivity::class.java)
        startActivityForResult(intent2,111)
    }




    fun goBackToMaps (view: View){

        if(DataManager.totalQleft == 0){
            Toast.makeText(this, "No markers left. Buy tickets!", Toast.LENGTH_LONG).show()

        }
        else{
            var intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("TICKET_CLICKED_FROM_QUESTION", ticketClickedPosition)
            setResult(999,intent)
            finish()
        }


    }



    fun checkAnswer (view: View){

        val blink: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)
        view.startAnimation(blink)

        if (locationArray[0].toString() == view.tag.toString()){


            view.alpha = 0.2F
            view.isEnabled = false

            when {
                trys == 0 -> DataManager.wallet += 1000
                trys == 1 -> DataManager.wallet += 700
                trys == 2 -> DataManager.wallet += 500
                trys == 3 -> DataManager.wallet += 300
            }
            walletTextView.setText(" ${DataManager.wallet}")
            trys++
            if (DataManager.totalQleft == 0 && DataManager.countCountries == 10){
                finishDialog.startFinishDialog()

            }
            else{
                DataManager.countQuestion++
                rightAnswerDialog.startRightAnswerDialog()
            }


        }
        else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
            view.isEnabled = false
            DataManager.wallet -= 200
            walletTextView.setText(" ${DataManager.wallet}")
            trys++
            view.alpha = 0.2F
        }

    }


    // For the OptionsMenu: from MenuClass
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return true
    }

}