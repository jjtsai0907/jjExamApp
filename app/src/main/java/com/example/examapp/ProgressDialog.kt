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
    var countCountries = 1

    fun countCountries () {

        for (i in 0 until DataManager.nations.size) {
            if (DataManager.nations[i].purchased) {
                countCountries++
            }
        }
    }



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

        var nrnations: TextView = progressDialog.asd2
        countCountries()

        nrnations.text = "Wow! You've been to ${countCountries.toString()} countries!"

        var q: TextView = progressDialog.asd3
        q.text = "You've answered ${DataManager.countQuestion.toString()} questions correctly!"

        var f: TextView = progressDialog.asd4
        f.text = "At this moment, you still have ${DataManager.wallet.toString()} kr left."

        /* var beenTo = mutableListOf<String>()
        if(DataManager.nations[1].purchased){
            for(){

            }

        }


        if(){
            for (i in 0..){
                nrnations.text = "${DataManager.nations[1].nation}"
            }
        }
        */

    }


}

