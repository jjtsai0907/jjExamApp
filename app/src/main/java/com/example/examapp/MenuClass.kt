package com.example.examapp

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open abstract class MenuClass : AppCompatActivity()  {

    var pp: ProgressDialog = ProgressDialog(this)

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
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    open fun doubleCheckMenu (clickedMenuItem: String){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Are you sure?")
            .setMessage("Do you want to leave this page and $clickedMenuItem the game? All progress will be lost.")
            .setPositiveButton("Sure") {dialog, which ->

                if (clickedMenuItem == "restart"){
                    val intent = Intent(this, MainActivity::class.java)
                    DataManager.reStart = true
                    startActivity(intent)
                }
                else {
                    val intent = Intent (this, MainActivity::class.java)
                    intent.putExtra("FINISH", "finish")
                    DataManager.reStart = true
                    startActivity(intent)
                }
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.show()
    }
}