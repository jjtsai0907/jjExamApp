package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.finish_dialog.*

class FinishDialog (var activity: Activity) {

    lateinit var finishDialog: AlertDialog
    lateinit var centerButton: ImageButton
    lateinit var finishTextView: TextView
    lateinit var leftButton: ImageView
    lateinit var rightButton: ImageView
    lateinit var fadeIn: Animation
    var rightClicked: Boolean = false
    lateinit var exit: ImageView
    lateinit var again: ImageView


    fun startFinishDialog() {
        var builder = AlertDialog.Builder(activity)



        val finishInflator: LayoutInflater = activity.layoutInflater
        builder.setView(finishInflator.inflate(R.layout.finish_dialog, null))
        builder.setCancelable(true)

        finishDialog = builder.create()
        finishDialog.show()

        centerButton = finishDialog.centerButton
        finishTextView = finishDialog.finishTextView
        leftButton = finishDialog.leftButton
        rightButton = finishDialog.rightButton
        exit = finishDialog.exit
        again = finishDialog.again
        finishTextView.text = "You've answered ${DataManager.countQuestion} questions correctly!"

        if(DataManager.totalQleft == 0 && DataManager.countCountries == 10){
            finishDialog.lastTextView.alpha = 1F
        }
        else{
            finishDialog.lastTextView.alpha = 0F
        }


        exit.setOnClickListener(){
            finishDialog.cancel()
        }




        centerButton.setOnClickListener(){

            centerClicked()
        }


        leftButton.setOnClickListener(){

            val rightUp: Animation = AnimationUtils.loadAnimation(finishDialog.context, R.anim.go_right_up)
            leftButton.startAnimation(rightUp)

            val leftDown: Animation = AnimationUtils.loadAnimation(finishDialog.context, R.anim.go_left_down)
            centerButton.startAnimation(leftDown)
            rightClicked = false
            switchInfo()
        }


        rightButton.setOnClickListener(){
            rightClicked = true
            val leftUp: Animation = AnimationUtils.loadAnimation(finishDialog.context, R.anim.go_left_up)
            rightButton.startAnimation(leftUp)

            val rightDown: Animation = AnimationUtils.loadAnimation(finishDialog.context, R.anim.go_right_down)
            centerButton.startAnimation(rightDown)

            switchInfo()


        }
    }



    fun centerClicked(){
        fadeIn = AnimationUtils.loadAnimation(finishDialog.context, R.anim.fadein)
        centerButton.startAnimation(fadeIn)
        finishTextView.startAnimation(fadeIn)
    }

    var leftCentered: Boolean = false

    var rightCentered: Boolean = false
    var rightRIght: Boolean = true
    var leftLeft: Boolean = true


    fun switchInfo(){

        Handler(Looper.getMainLooper()).postDelayed({

            if(rightRIght && leftLeft){

               if (rightClicked){
                   centerButton.setImageResource(R.drawable.ic_landing2)
                   rightButton.setImageResource(R.drawable.ic_check)
                   finishTextView.text = "Wow! You've been to ${DataManager.countCountries} countries: ${DataManager.countryBeenTo}. Did you enjoy it?"
                   rightCentered = true
                   rightRIght = false
               }
                else{
                   centerButton.setImageResource(R.drawable.ic_money_dark)
                   leftButton.setImageResource(R.drawable.ic_check)
                   finishTextView.text = "Let's see...you have ${DataManager.wallet} in the pocket!"
                   leftCentered = true
                   leftLeft = false
               }
            }
            else if (rightCentered && leftLeft){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.ic_check)
                    rightButton.setImageResource(R.drawable.ic_landing2)
                    finishTextView.text = "You've answered ${DataManager.countQuestion} questions correctly!"
                    rightCentered = false
                    rightRIght = true
                }
                else{
                    centerButton.setImageResource(R.drawable.ic_money_dark)
                    leftButton.setImageResource(R.drawable.ic_landing2)
                    finishTextView.text = "Let's see...you have ${DataManager.wallet} in the pocket!"
                    leftCentered = true
                    leftLeft = false
                    rightCentered = false
                }
            }

            else if (rightRIght && leftCentered){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.ic_landing2)
                    rightButton.setImageResource(R.drawable.ic_money_dark)
                    finishTextView.text = "Wow! You've been to ${DataManager.countCountries} countries: ${DataManager.countryBeenTo}. Did you enjoy it?"
                    rightCentered = true
                    rightRIght = false
                    leftCentered = false
                    //${countCountries.toString()}
                }
                else{
                    centerButton.setImageResource(R.drawable.ic_check)
                    leftButton.setImageResource(R.drawable.ic_money_dark)
                    finishTextView.text = "You've answered ${DataManager.countQuestion} questions correctly!"
                    leftLeft = true
                    leftCentered = false
                }
            }
            else if (!rightRIght && leftCentered){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.ic_check)
                    rightButton.setImageResource(R.drawable.ic_money_dark)
                    finishTextView.text = "You've answered ${DataManager.countQuestion} questions correctly!"
                    leftLeft = false
                    leftCentered = false
                }
                else{
                    centerButton.setImageResource(R.drawable.ic_landing2)
                    leftButton.setImageResource(R.drawable.ic_money_dark)
                    finishTextView.text = "Wow! You've been to ${DataManager.countCountries} countries: ${DataManager.countryBeenTo}. Did you enjoy it?"
                    rightCentered = true
                    leftLeft = true
                }
            }
            else if(rightCentered && !leftLeft){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.ic_money_dark)
                    rightButton.setImageResource(R.drawable.ic_landing2)
                    finishTextView.text = "Let's see...you have ${DataManager.wallet} in the pocket!"
                    rightCentered = false
                    rightRIght = true
                    leftCentered = true
                }
                else{
                    centerButton.setImageResource(R.drawable.ic_check)
                    leftButton.setImageResource(R.drawable.ic_landing2)
                    finishTextView.text = "You've answered ${DataManager.countQuestion} questions correctly!"
                    rightCentered = false
                    rightRIght = false
                }

            }
            else{

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.ic_money_dark)
                    rightButton.setImageResource(R.drawable.ic_check)
                    finishTextView.text = "Let's see...you have ${DataManager.wallet} in the pocket!"
                    rightCentered = false
                    leftCentered = true
                    leftLeft = false
                }
                else{
                    centerButton.setImageResource(R.drawable.ic_landing2)
                    leftButton.setImageResource(R.drawable.ic_check)
                    finishTextView.text = "Wow! You've been to ${DataManager.countCountries} countries: ${DataManager.countryBeenTo}. Did you enjoy it?"
                    rightCentered = true
                    leftLeft = false
                    leftCentered = false
                    rightRIght = false
                }
            }

            //finishTextView.startAnimation(fadeIn)
        },1000)


    }


    fun dismiss (){
        finishDialog.cancel()
    }





}