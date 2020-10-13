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


class ShoppingActivity : AppCompatActivity(), NationListRecycleAdapter.OnItemClickListener {


    lateinit var shoppingWalletTextVIew: TextView
    lateinit var recyclerView: RecyclerView
    var ticketClickedPosition = 0
    lateinit var coinSound: MediaPlayer

    lateinit var loadingDialog: LoadingDialog
    lateinit var progressDialog: ProgressDialog

    //lateinit var bar: ProgressBar
    //lateinit var cdt: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)





        loadingDialog = LoadingDialog(this)

        progressDialog = ProgressDialog(this)


        coinSound = MediaPlayer.create(this, R.raw.coin)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, DataManager.nations, this)
        recyclerView.adapter = adapter

        shoppingWalletTextVIew.text = " ${DataManager.wallet.toString()} kr"
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
                shoppingWalletTextVIew.text = " ${DataManager.wallet.toString()} kr"
                DataManager.nations[ticketClickedPosition].purchased = true
                recyclerView.adapter?.notifyDataSetChanged()
                coinSound.start()
                DataManager.currentCountry = ticketClickedPosition
                DataManager.clearMaps = true
                //bar = findViewById(R.id.progressBar)
                //bar.setProgress(10000)
                //loadingDialog.countDown()

                loadingDialog.startLoadingDialog()
                //var handler: Handler = Handler()
                Handler(Looper.getMainLooper()).postDelayed({
                    //Toast.makeText(this, "Haha", Toast.LENGTH_SHORT).show()
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


    // For the OptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){

            R.id.progress -> {
                progressDialog.startProgressDialog()
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

    fun doubleCheckMenu (clickedMenuItem: String){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Are you sure?")
            .setMessage("Do you want to leave this page and $clickedMenuItem the game? All progress will be lost.")
            .setPositiveButton("Sure") {dialog, which ->

                if (clickedMenuItem == "restart"){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent (this, MainActivity::class.java)
                    intent.putExtra("FINISH", "finish")
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
