package com.example.examapp

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.view.postOnAnimationDelayed
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_finish.*
import kotlin.concurrent.thread
import kotlin.concurrent.schedule
import java.util.*


class MainActivity : AppCompatActivity() {

    //lateinit var finish1: TextView
    //lateinit var buttonx: Button
    //var countCountries: Int = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val asd: ImageView = findViewById(R.id.asd)


        asd.setOnClickListener {
            asd.animate().apply {
                duration = 3000
                rotationYBy(360f)

            }.start()
        }*/

        val buttonx = findViewById<Button>(R.id.buttonx)
        val myLogo: ImageView = findViewById(R.id.myLogo)
        val gameName: TextView = findViewById(R.id.gameName)


        // Fade-in animation for Logo
        val logoFadeIn: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fadein)
        myLogo.startAnimation(logoFadeIn)



        // This shows the FinishFragment when one press "finish the game" in OptionsMenu.
        var finish = intent.getStringExtra("FINISH")
        if (finish == "finish"){
            myLogo.alpha = 0F
            gameName.alpha = 0F
            buttonx.alpha = 1F
            finishGame()
        }
    }




    fun change (view: View){


        //ticketAnimation.start()
        //asd.alpha = 1.0F

        //asd.setImageResource(R.drawable.globewhite)

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
        buttonx.alpha = 0F
        myLogo.alpha = 0F
        gameName.alpha = 0F

    }


    fun finishGame (){

        val finishFragment = FinishFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, finishFragment, "finishFragment")
        transaction.commit()
    }




    fun changeText(view: View){

        var buttont: Button = findViewById(R.id.buttonx)
        buttont.alpha = 0F

        val fragmentFinish = supportFragmentManager.findFragmentByTag("finishFragment") as FinishFragment?

        fragmentFinish?.setText()
    }
}