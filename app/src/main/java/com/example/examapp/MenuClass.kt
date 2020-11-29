package com.example.examapp

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open abstract class MenuClass : AppCompatActivity()  {

    var pp: ProgressDialog = ProgressDialog(this)
    var historyDialog = HistoryDialog(this)
    lateinit var db: AppDatabase


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){

            R.id.progress -> {
                pp.startProgressDialog()
                return true
            }
            R.id.finish -> {
                doubleCheckMenu("finish")
                return true
            }
            R.id.restart -> {
                doubleCheckMenu("restart")
                return true
            }

            R.id.about -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://jjtsai0907.wordpress.com/"))
                startActivity(intent)
                return true
            }
            R.id.history -> {
                historyDialog.startHistoryDialog()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    open fun doubleCheckMenu (clickedMenuItem: String){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Are you sure?")
            .setMessage("Do you want to leave this page and $clickedMenuItem the game? You will not be able to continue with your current progress.")
            .setPositiveButton("Sure") {dialog, which ->

                if (clickedMenuItem == "restart"){
                    val intent = Intent(this, MainActivity::class.java)
                    DataManager.reStart = true
                    startActivity(intent)
                }
                else {

                    db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "saved_player_progress")
                        .fallbackToDestructiveMigration()
                        .build()

                    val intent = Intent (this, MainActivity::class.java)
                    intent.putExtra("FINISH", "finish")
                    DataManager.reStart = true




                    val playerProgress = PlayerProgress(0, DataManager.userName,
                        DataManager.wallet,
                        DataManager.countCountries, DataManager.countQuestion)



                    saveProgress(playerProgress)


                    startActivity(intent)
                }
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.show()
    }

    // Launch: dont get anything back
    // Async: get sth. back


    fun saveProgress (playerProgress: PlayerProgress){

        GlobalScope.launch(Dispatchers.IO){
            db.databaseDao().insert(playerProgress)
        }

    }

    /*fun clearProgress (){

        /*GlobalScope.launch(Dispatchers.IO){
            db.databaseDao().delete()
        }*/

    }*/


}