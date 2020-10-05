package com.example.examapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_finish.*
import kotlin.concurrent.thread
import kotlin.concurrent.schedule
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var finish1: TextView
    lateinit var buttonx: Button
    var countCountries: Int = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val goButton: Button = findViewById(R.id.goButton)
        val howToPlayButton: Button = findViewById(R.id.howToPlayButton)
        val buttonx = findViewById<Button>(R.id.buttonx)


        var finish = intent.getStringExtra("FINISH")

        if (finish == "finish"){


            finishGame()
            buttonx.alpha = 1F


            //Timer("Delay", false).schedule(1000){
                //changeText()

                //val fragmentFinish = supportFragmentManager.findFragmentByTag("finishFragment") as FinishFragment?
                //fragmentFinish.finishCountryTextVIew


                //fragmentFinish?.setText("2345")
                //Log.d("!!!!!","funkar")

            //}






        }







    }

    fun entryButtonClicked (view: View){
        val intent: Intent = Intent(this, MapsActivity::class.java)

        for (i in 0 until DataManager.nations.size){
            DataManager.nations[i].purchased = false

        }
        DataManager.wallet = 0
        DataManager.countQuestion = 0



        startActivity(intent)

    }



    fun howToPlay(view: View){

        val howToPlayFragment = HowToPlayFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, howToPlayFragment, "howToPlayFragment")
        transaction.commit()
    }

    fun finishGame (){
        val finishFragment = FinishFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, finishFragment, "finishFragment")

        transaction.commit()
        //changeText("Hey")
        //finishFragment.setText("24")
        //changeText()

    }



    /*fun countCountries (): Int{
        for (i in 0 until DataManager.nations.size){
            if(DataManager.nations[i].purchased){
                countCountries ++

            }
        }
        return countCountries
    }*/

    //fun countQuestions (){}



    fun changeText(view: View){

        var buttont: Button = findViewById(R.id.buttonx)
        buttont.alpha = 0F
        //buttonx.alpha = 0F
        //countCountries()


        val fragmentFinish = supportFragmentManager.findFragmentByTag("finishFragment") as FinishFragment?
        //fragmentFinish.finishCountryTextVIew
        fragmentFinish?.setText()


    }



}