package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
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

        howToDialog.howTextView.text = "When tapping START you will travel to Sweden, your first country, and few markers will appear. " +
                "Tapping a marker a message box will appear, tap again to get to the connected question. To answer tap on of the 4 alternatives. " +
                "If you get it right the 1st time you will be awarded 1000kr, 2nd try will give you 300kr, 3rd 0kr and 4th -300kr. " +
                "You will need money to buy tickets for traveling to new countries with more exciting questions. " +
                "Tapping the marker symbol in the upper left corner will bring you back to the map, but be aware " +
                "that the marker connected to the question you just answered will no longer be visible" +
                "Tapping the flight symbol in the uppre right corner will bring you to the Travel agency where you can buy flight tickets." +
                "Tap a ticket which you have enough money for to buy it and travel to the desired destination. " +
                "Be aware that all un answered questions in your current country will be left behind if you decide to journey onwards."


    }
}