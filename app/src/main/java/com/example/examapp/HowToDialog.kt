package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.how_to_dialog.*

class HowToDialog (var activity: Activity){

    lateinit var howToDialog: AlertDialog

    fun startHowToDialog() {

        var builder = AlertDialog.Builder(activity)


        val howToInflator: LayoutInflater = activity.layoutInflater
        builder.setView(howToInflator.inflate(R.layout.how_to_dialog, null))
        builder.setCancelable(true)

        howToDialog = builder.create()
        howToDialog.show()
        val blink: Animation = AnimationUtils.loadAnimation(howToDialog.context, R.anim.blink)
        howToDialog.howTextView.alpha = 0F



        howToDialog.concept.setOnClickListener(){

            howToDialog.concept.startAnimation(blink)

            howToDialog.concept.alpha = 0.4F
            howToDialog.rules.alpha = 1F
            howToDialog.howTextView.alpha = 1F
            howToDialog.howTextView.text = """By answering Qs (questions) correctly, you'll earn coins to purchase tickets to new countries for more Qs.
                
In current version, you only compete with yourself. You end/finish the game by clicking setting button on upper right at anytime you want. 
            """.trimMargin()


        }

        howToDialog.rules.setOnClickListener(){

            howToDialog.rules.startAnimation(blink)

            howToDialog.rules.alpha = 0.4F
            howToDialog.concept.alpha = 1F
            howToDialog.howTextView.alpha = 1F
            howToDialog.howTextView.text = """1 Each Q can only be revealed once
 
2 If you run out of coins & Qs, the game ends. Please then press 'Finish' Button to see your results.""".trimIndent()

        }

        howToDialog.howExit.setOnClickListener(){
            howToDialog.cancel()
        }




    }



}