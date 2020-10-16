package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlin.math.roundToInt

class ProgressDialog (var activity: Activity) {

    lateinit var progressDialog: AlertDialog
    lateinit var pCountCountry: TextView
    lateinit var pCountQuestion: TextView
    lateinit var pWallet: TextView
    lateinit var ringBar: ProgressBar
    lateinit var percentTextView: TextView


    //used to count how many countries one has been to, but now I have it in DataManager.
    /*fun countCountries () {

        for (i in 0 until DataManager.nations.size) {
            if (DataManager.nations[i].purchased) {
                countCountries++
            }
        }
    }*/



    fun startProgressDialog(){
        var builder = AlertDialog.Builder(activity)


        val progressInflator: LayoutInflater = activity.layoutInflater
        builder.setView(progressInflator.inflate(R.layout.progress_dialog, null))
        builder.setCancelable(true)

        progressDialog = builder.create()
        progressDialog.show()

        percentTextView = progressDialog.percentTextView
        var TotalQuestionCount = 0

        for (i in 0 until DataManager.nations.size){
            for (q in 0 until DataManager.nations[i].questionClassList.size){
                TotalQuestionCount += 1
            }
        }



        pCountCountry = progressDialog.pCountQuestion
        pCountCountry.text = """Hey, ${DataManager.userName}!
|At this moment, you've been to ${DataManager.countCountries} countries!""".trimMargin()

        pCountQuestion = progressDialog.pCountCountry
        pCountQuestion.text = "So far, you've answered ${DataManager.countQuestion} questions correctly :)"

        pWallet = progressDialog.pWallet
        pWallet.text = "At this moment, you have ${DataManager.wallet} in your pocket..."

        var percent: Double =  (DataManager.countQuestion.toDouble()/TotalQuestionCount.toDouble()) * 100
        percentTextView.text = percent.roundToInt().toString()
        ringBar = progressDialog.ringBar
        ringBar.progress = percent.toInt()


    }


}

