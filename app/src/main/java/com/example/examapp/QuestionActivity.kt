package com.example.examapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Drawable.createFromPath
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_shopping.*
import java.io.File
import java.util.*
import kotlin.random.Random.Default.nextInt


class QuestionActivity : AppCompatActivity() {

    lateinit var currentCountryTV: TextView
    lateinit var walletTextView: TextView
    lateinit var shoppingImageView: ImageButton
    lateinit var questionTextView: TextView
    lateinit var questionImageView: ImageView
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    var trys: Int = 0
    var locationArray = mutableListOf<Int>(0,0,0,0)
    var ticketClickedPosition = 0



    @SuppressLint("ResourceType")
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
        questionImageView = findViewById(R.id.questionImageView)




        /*button0.setOnClickListener {
            val bounce: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.bounce)
            button0.startAnimation(bounce)

        }*/




        currentCountryTV.text = intent.getStringExtra("CURRENT_CITY")
        var photo = intent.getIntExtra("TAG_QUESTION_BACKGROUND", R.drawable.nicke)


        questionImageView.setImageResource(photo)
        walletTextView.text = "${DataManager.wallet.toString()} kr"



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

        walletTextView.text = "${DataManager.wallet.toString()} kr"
        ticketClickedPosition = data!!.getIntExtra("TICKET_CLICKED_POSITION", 9)

        var intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("TICKET_CLICKED_FROM_QUESTION", ticketClickedPosition)
        setResult(999,intent)
        finish()
    }



    fun goShopping (view: View){
        var intent2 = Intent(this, ShoppingActivity::class.java)
        startActivityForResult(intent2,111)
    }




    fun goBackToMaps (view: View){

        var intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("TICKET_CLICKED_FROM_QUESTION", ticketClickedPosition)
        setResult(999,intent)
        finish()
    }



    fun checkAnswer (view: View){

        val bounce: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.bounce)
        view.startAnimation(bounce)

        if (locationArray[0].toString() == view.tag.toString()){


            Toast.makeText(this,"Correct!", Toast.LENGTH_SHORT).show()
            view.isEnabled = false

            when {
                trys == 0 -> DataManager.wallet += 1000
                trys == 1 -> DataManager.wallet += 300
                trys == 2 -> DataManager.wallet += 0
                trys == 3 -> DataManager.wallet -= 300
            }
            walletTextView.setText(" ${DataManager.wallet.toString()} kr")
            trys ++
            DataManager.countQuestion ++
        }
        else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
            view.isEnabled = false
            trys++
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.finish -> {
                doubleCheckMenu("finish")
                return true
            }
            R.id.restart -> {
                doubleCheckMenu("restart")
                return true
            }
            R.id.about -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://jjtsai0907.wordpress.com/"))
                startActivity(intent)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


    fun doubleCheckMenu (clickedMenuItem: String){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Are you sure?")
            .setMessage("Do you want to leave this page and $clickedMenuItem the game? All progress will be lost.")
            .setPositiveButton("Sure") {dialog, which ->

                if (clickedMenuItem == "restart"){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent (this, MainActivity::class.java)
                    intent.putExtra("FINISH", "finish")
                    startActivity(intent)
                }
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.show()
    }
}