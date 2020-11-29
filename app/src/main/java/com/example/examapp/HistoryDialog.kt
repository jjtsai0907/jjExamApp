package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.room.Room
import kotlinx.android.synthetic.main.history_dialog.*
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class HistoryDialog (var activity: Activity) {

    lateinit var historyDialog: AlertDialog
    lateinit var playerTextView1: TextView
    lateinit var historyTitle: TextView
    lateinit var db: AppDatabase


    fun startProgressDialog(){
        val builder = AlertDialog.Builder(activity)


        val historyInflator: LayoutInflater = activity.layoutInflater
        builder.setView(historyInflator.inflate(R.layout.history_dialog, null))
        builder.setCancelable(true)

        historyDialog = builder.create()
        historyDialog.show()

        historyTitle = historyDialog.historyTitle

        historyTitle.text = "${DataManager.userName} Previous Records"

        db = Room.databaseBuilder(this.historyDialog.context, AppDatabase::class.java, "saved_player_progress")
            .fallbackToDestructiveMigration()
            .build()


        playerTextView1 = historyDialog.playerTextView1


        val list = LoadSameNameHistory(DataManager.userName)

        GlobalScope.async{
            val playerList = list.await()

            var playerHistory = ""
            for (player in playerList){
                Log.d("!!!", "History: players")

                playerHistory += "ID: ${player.playerId}: ${player.playerWallet} coins, ${player.playerCountQuestion} correct answers, been to ${player.playerCountCountries} countries.\n\n"
                //playerTextView1.text =



            }

            playerTextView1.text = playerHistory
        }







        /*historyDialog.progressExit.setOnClickListener(){
            historyDialog.cancel()
        }*/




    }


    fun LoadSameNameHistory (playerName: String) =

        GlobalScope.async(Dispatchers.IO){
            db.databaseDao().findByPlayerName(playerName)
        }




}