package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.loading_dialog.*

class LoadingDialog (var activity: Activity) {

    lateinit var dialog: AlertDialog
    lateinit var myActivity: Activity
    //lateinit var bar: ProgressBar
    lateinit var cdt: CountDownTimer






    fun startLoadingDialog(){
        var builder = AlertDialog.Builder(activity)


        val loadingInflator: LayoutInflater = activity.layoutInflater
        builder.setView(loadingInflator.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)



        dialog = builder.create()
        dialog.show()
        countDown()

    }

    fun dismiseDialog(){
        dialog.dismiss()
    }

    fun countDown () {

        var bar = dialog.progressBar

        bar.max = 3000
        //bar.setProgress(1500)
        var timepassed = 500

        cdt = object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                timepassed += 125
                bar.setProgress(timepassed)
                Log.d("jjj", timepassed.toString())

            }

            override fun onFinish() {
                //Toast.makeText(this,"Haha", Toast.LENGTH_SHORT).show()
            }

        }
        cdt.start()
    }




}

/*
    LoadingDialog(myActivity: Activity){
    activity = myActivity}
 */