package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlinx.android.synthetic.main.right_answer_dialog.*
import kotlin.math.roundToInt

class RightAnswerDialog (var activity: Activity) {

    lateinit var rightAnswerDialog: AlertDialog



    fun startRightAnswerDialog(){
        var builder = AlertDialog.Builder(activity)


        val rightAnswerInflator: LayoutInflater = activity.layoutInflater
        builder.setView(rightAnswerInflator.inflate(R.layout.right_answer_dialog, null))
        builder.setCancelable(false)

        rightAnswerDialog = builder.create()
        rightAnswerDialog.show()


        if(DataManager.totalQleft == 0){
            rightAnswerDialog.rightToMaps.alpha = 0.2F
            rightAnswerDialog.rightToMaps2.alpha = 0.2F
        }

    }


}

