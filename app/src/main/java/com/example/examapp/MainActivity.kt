package com.example.examapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import kotlinx.android.synthetic.main.enter_name_dialog.*
import java.util.zip.DataFormatException


class MainActivity : AppCompatActivity() {

    lateinit var loadingDialog: LoadingDialog
    lateinit var howToDialog: HowToDialog
    lateinit var finishDialog: FinishDialog
    lateinit var enterNameDialog: EnterNameDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        loadingDialog = LoadingDialog(this)
        howToDialog = HowToDialog(this)
        finishDialog = FinishDialog(this)
        enterNameDialog = EnterNameDialog(this)


        val myLogo: ImageView = findViewById(R.id.myLogo)
        val gameName: TextView = findViewById(R.id.gameName)


        // Fade-in animation for Logo and gameName
        val fadeIn: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fadein)
        myLogo.startAnimation(fadeIn)
        gameName.startAnimation(fadeIn)




        // This shows the FinishDialog when one press "finish the game" in OptionsMenu.
        var finish = intent.getStringExtra("FINISH")
        if (finish == "finish"){
            finishDialog.startFinishDialog()
        }
    }





    fun howToPlay(view: View){
        val blink: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)
        view.startAnimation(blink)

        howToDialog.startHowToDialog()

        // Used Fragment, but changed to Dialog now :P
        /*val howToPlayFragment = HowToPlayFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, howToPlayFragment, "howToPlayFragment")
        transaction.commit()
        buttonx.alpha = 0F
        myLogo.alpha = 0F
        gameName.alpha = 0F*/

    }



    fun getName (view: View){
        enterNameDialog.startEnterNameDialog()

    }

    // after entered name and start the game
    fun startGame (view: View){
        loadingDialog.startLoadingDialog()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent(this, MapsActivity::class.java)

            var userInput = enterNameDialog.userInput()
            intent.putExtra("USER_INPUT", userInput)


            for (i in 0 until DataManager.nations.size){
                DataManager.nations[i].purchased = false
            }

            DataManager.wallet = 0
            DataManager.countQuestion = 0
            DataManager.countCountries = 1
            DataManager.countryBeenTo.clear()
            DataManager.countryBeenTo.add("Sweden")
            DataManager.currentCountry = -1



            startActivity(intent)
        },2050)
    }

    fun playAgain (view: View){
        DataManager.countryBeenTo.clear()
        DataManager.countryBeenTo.add("Sweden")
        finishDialog.dismiss()
        enterNameDialog.startEnterNameDialog()

    }

}