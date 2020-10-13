package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater

class HowToDialog (var activity: Activity){

    lateinit var howToDialog: AlertDialog

    fun startHowToDialog() {

        var builder = AlertDialog.Builder(activity)


        val howToInflator: LayoutInflater = activity.layoutInflater
        builder.setView(howToInflator.inflate(R.layout.how_to_dialog, null))
        builder.setCancelable(true)


        howToDialog = builder.create()
        howToDialog.show()
    }
}