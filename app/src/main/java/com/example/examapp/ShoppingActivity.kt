package com.example.examapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_nation.*
import java.lang.Thread.sleep
import kotlin.concurrent.timerTask


class ShoppingActivity : MenuClass(), NationListRecycleAdapter.OnItemClickListener {


    lateinit var shoppingWalletTextVIew: TextView
    lateinit var recyclerView: RecyclerView
    var ticketClickedPosition = 0
    lateinit var coinSound: MediaPlayer

    lateinit var loadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        loadingDialog = LoadingDialog(this)
        coinSound = MediaPlayer.create(this, R.raw.coin)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, DataManager.nations, this)
        recyclerView.adapter = adapter

        shoppingWalletTextVIew.text = " ${DataManager.wallet}"
    }



    fun goBackToQuestion (view: View){

        var intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("TICKET_CLICKED_POSITION", ticketClickedPosition)
        setResult(111,intent)

        finish()
    }


    // These are Interface functions, to control over RecyclerView from ShoppingActivity
    @SuppressLint("SetTextI18n")
    override fun onClick(int: Int) {
        ticketClickedPosition = int

        if (DataManager.wallet >= DataManager.nations[ticketClickedPosition].ticketFare) {
            doubleCheckPurchase(ticketClickedPosition)
        }
        else  {
            Toast.makeText(this, "Need More Money!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLongClick(int: Int) {
        Toast.makeText(this, "Are you gonna buy?",Toast.LENGTH_SHORT).show()
    }




    fun doubleCheckPurchase(ticketClickedPosition: Int){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Purchase?")
            .setMessage("Do you want to buy this ticket to ${DataManager.nations[ticketClickedPosition].nation} " +
                        "for ${DataManager.nations[ticketClickedPosition].ticketFare} kr?")
            .setPositiveButton("Purchase") {dialog, which ->
                DataManager.wallet -= DataManager.nations[ticketClickedPosition].ticketFare
                shoppingWalletTextVIew.text = " ${DataManager.wallet}"
                DataManager.nations[ticketClickedPosition].purchased = true
                recyclerView.adapter?.notifyDataSetChanged()
                coinSound.start()
                DataManager.currentCountry = ticketClickedPosition
                DataManager.clearMaps = true
                DataManager.countCountries += 1
                DataManager.countryBeenTo.add(DataManager.nations[ticketClickedPosition].nation)

                loadingDialog.startLoadingDialog()

                Handler(Looper.getMainLooper()).postDelayed({

                    loadingDialog.dismiseDialog()

                    var intent = Intent(this, QuestionActivity::class.java)
                    intent.putExtra("TICKET_CLICKED_POSITION", ticketClickedPosition)
                    setResult(111,intent)

                    finish()

                },3500)

            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.show()
    }


    // For the OptionsMenu: from MenuClass
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return true
    }
}
