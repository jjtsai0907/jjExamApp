package com.example.examapp


import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.room.Room
import kotlinx.android.synthetic.main.history_dialog.*
import kotlinx.android.synthetic.main.how_to_dialog.*
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class HistoryDialog (var activity: Activity) {

    lateinit var historyDialog: AlertDialog
    lateinit var playerTextView1: TextView
    lateinit var historyTitle: TextView
    lateinit var db: AppDatabase
    lateinit var playerList: List<PlayerProgress>


    fun startHistoryDialog(){
        val builder = AlertDialog.Builder(activity)


        val historyInflator: LayoutInflater = activity.layoutInflater
        builder.setView(historyInflator.inflate(R.layout.history_dialog, null))
        builder.setCancelable(true)

        historyDialog = builder.create()
        historyDialog.show()

        historyTitle = historyDialog.historyTitle

        historyTitle.text = "${DataManager.userName}'s Previous Records"

        db = Room.databaseBuilder(this.historyDialog.context, AppDatabase::class.java, "saved_player_progress")
            .fallbackToDestructiveMigration()
            .build()


        playerTextView1 = historyDialog.playerTextView1


        val list = LoadSameNameHistory(DataManager.userName)

        GlobalScope.async{
            playerList = list.await()

            //Default sorting of list
            val sortedPlayerList = playerList.sortedByDescending { it.playerWallet }
            createPlayerString(sortedPlayerList)

        }


        //Sort list by amount of coins
        historyDialog.SortCoins.setOnClickListener(){
            val sortedPlayerList = playerList.sortedByDescending { it.playerWallet }
            createPlayerString(sortedPlayerList)
        }

        //Sort list by amount of answered questions
        historyDialog.SortQuestions.setOnClickListener(){
            val sortedPlayerList = playerList.sortedByDescending { it.playerCountQuestion }
            createPlayerString(sortedPlayerList)
        }

        //Sort list by amount of visited countries
        historyDialog.SortCountries.setOnClickListener(){
            val sortedPlayerList = playerList.sortedByDescending { it.playerCountCountries }
            createPlayerString(sortedPlayerList)
        }



        historyDialog.historyExit.setOnClickListener(){
            historyDialog.cancel()
        }

    }

    //Function to create a long string of the sorted history list and print it in a scrollable text view
    fun createPlayerString (sortedPlayerList:List<PlayerProgress>){

        var playerHistory = ""
        var nr = 0
        for (player in sortedPlayerList){
            Log.d("!!!", "History: players")
            nr ++
            playerHistory += "#$nr: ${player.playerWallet} coins, ${player.playerCountQuestion} correct answers, been to ${player.playerCountCountries} countries.\n\n"
            //playerTextView1.text =

        }

        playerTextView1.text = playerHistory

    }


    fun LoadSameNameHistory (playerName: String) =

        GlobalScope.async(Dispatchers.IO){
            db.databaseDao().findByPlayerName(playerName)
        }




}