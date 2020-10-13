package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater

class FinishDialog (var activity: Activity) {

    lateinit var finishDialog: AlertDialog

    fun startFinishDialog() {
        var builder = AlertDialog.Builder(activity)


        val finishInflator: LayoutInflater = activity.layoutInflater
        builder.setView(finishInflator.inflate(R.layout.finish_dialog, null))
        builder.setCancelable(true)

        finishDialog = builder.create()
        finishDialog.show()
    }

}