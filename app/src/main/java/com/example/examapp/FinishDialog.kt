package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import kotlinx.android.synthetic.main.finish_dialog.*

class FinishDialog (var activity: Activity) {

    lateinit var finishDialog: AlertDialog
    lateinit var centerButton: ImageButton
    lateinit var finishTextView: TextView
    lateinit var leftButton: ImageView
    lateinit var rightButton: ImageView
    lateinit var fadeIn: Animation
    var rightClicked: Boolean = false

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

        fadeIn = AnimationUtils.loadAnimation(finishDialog.context, R.anim.fadein)


        centerButton.setOnClickListener(){

            centerClicked()
        }

        leftButton.setOnClickListener(){
            //leftClicked()
            rightClicked = false
            //val goRight: Animation = AnimationUtils.loadAnimation(finishDialog.context, R.anim.slide_out)
            //leftButton.startAnimation(goRight)
            switchInfo()

        }
        rightButton.setOnClickListener(){
            rightClicked = true
            //val logoIn: Animation = AnimationUtils.loadAnimation(finishDialog.context, R.anim.slide_out)
            //centerButton.startAnimation(logoIn)
            switchInfo()
            //rightClicked()

        }




    }
    // center : question
    fun centerClicked(){

        centerButton.startAnimation(fadeIn)
        finishTextView.startAnimation(fadeIn)
    }

    var leftCentered: Boolean = false

    var rightCentered: Boolean = false
    var rightRIght: Boolean = true
    var leftLeft: Boolean = true

    //right : Country
    fun switchInfo(){

        Handler(Looper.getMainLooper()).postDelayed({

            if(rightRIght && leftLeft){

               if (rightClicked){
                   centerButton.setImageResource(R.drawable.right)
                   rightButton.setImageResource(R.drawable.finiex)
                   finishTextView.text = "Wow! You've been to  countries!"
                   rightCentered = true
                   rightRIght = false
               }
                else{
                   centerButton.setImageResource(R.drawable.left)
                   leftButton.setImageResource(R.drawable.finiex)
                   finishTextView.text = "Wow! You've been to  countries!"
                   leftCentered = true
                   leftLeft = false
               }
            }
            else if (rightCentered && leftLeft){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.finiex)
                    rightButton.setImageResource(R.drawable.right)
                    finishTextView.text = "You've answered ${DataManager.countQuestion.toString()} questions correctly!"
                    rightCentered = false
                    rightRIght = true
                }
                else{
                    centerButton.setImageResource(R.drawable.left)
                    leftButton.setImageResource(R.drawable.right)
                    leftCentered = true
                    leftLeft = false
                    rightCentered = false
                }
            }

            else if (rightRIght && leftCentered){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.right)
                    rightButton.setImageResource(R.drawable.left)
                    finishTextView.text = "Wow! You've been to  countries!"
                    rightCentered = true
                    rightRIght = false
                    leftCentered = false
                    //${countCountries.toString()}
                }
                else{
                    centerButton.setImageResource(R.drawable.finiex)
                    leftButton.setImageResource(R.drawable.left)
                    leftLeft = true
                    leftCentered = false
                }
            }
            else if (!rightRIght && leftCentered){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.finiex)
                    rightButton.setImageResource(R.drawable.left)
                    finishTextView.text = "You've answered ${DataManager.countQuestion.toString()} questions correctly!"
                    leftLeft = false
                    leftCentered = false
                }
                else{
                    centerButton.setImageResource(R.drawable.right)
                    leftButton.setImageResource(R.drawable.left)
                    rightCentered = true
                    leftLeft = true
                }
            }
            else if(rightCentered && !leftLeft){

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.left)
                    rightButton.setImageResource(R.drawable.right)
                    finishTextView.text = "€€€"
                    rightCentered = false
                    rightRIght = true
                    leftCentered = true
                }
                else{
                    centerButton.setImageResource(R.drawable.finiex)
                    leftButton.setImageResource(R.drawable.right)
                    rightCentered = false
                    rightRIght = false
                }

            }
            else{

                if(rightClicked){
                    centerButton.setImageResource(R.drawable.left)
                    rightButton.setImageResource(R.drawable.finiex)
                    finishTextView.text = "€€€"
                    rightCentered = false
                    leftCentered = true
                    leftLeft = false
                }
                else{
                    centerButton.setImageResource(R.drawable.right)
                    leftButton.setImageResource(R.drawable.finiex)
                    rightCentered = true
                    leftLeft = false
                    leftCentered = false
                    rightRIght = false
                }
            }

            finishTextView.startAnimation(fadeIn)
        },600)


    }




}