package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.TextView
import kotlinx.android.synthetic.main.loading_dialog.*
import kotlinx.android.synthetic.main.progress_dialog.*

class ProgressDialog (var activity: Activity) {

    lateinit var progressDialog: AlertDialog
    lateinit var myActivity: Activity



    fun startProgressDialog(){
        var builder = AlertDialog.Builder(activity)


        val progressInflator: LayoutInflater = activity.layoutInflater
        builder.setView(progressInflator.inflate(R.layout.progress_dialog, null))
        builder.setCancelable(true)




        progressDialog = builder.create()
        progressDialog.show()
        //countDown()
        var co2: TextView = progressDialog.asd
        co2.text = "fffffff"

    }


}

