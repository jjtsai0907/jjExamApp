package com.example.examapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ShoppingActivity : AppCompatActivity(), NationListRecycleAdapter.OnItemClickListener {


    lateinit var shoppingWalletTextVIew: TextView
    lateinit var recyclerView: RecyclerView
    var wallet: Int = 0
    var ticketClickedPosition = 0





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, DataManager.nations, this)
        recyclerView.adapter = adapter

        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)
        wallet = intent.getIntExtra("WALLET_QUESTION",0)
        shoppingWalletTextVIew.text = " ${wallet.toString()} kr"

    }


    fun goBackToQuestion (view: View){

        var intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("WALLET_SHOPPING", wallet)
        intent.putExtra("TICKET_CLICKED_POSITION", ticketClickedPosition)
        setResult(111,intent)
        finish()


    }

    @SuppressLint("SetTextI18n")
    override fun onClick(int: Int) {

        ticketClickedPosition = int

        if (wallet >= DataManager.nations[ticketClickedPosition].ticketFare) {

            doubleCheckPurchase(ticketClickedPosition)

        }else  {
            Toast.makeText(this, "Need More Money!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onLongClick(int: Int) {
        Toast.makeText(this, "Heeeeeey",Toast.LENGTH_SHORT).show()
    }


    fun doubleCheckPurchase(ticketClickedPosition: Int){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Purchase?")
            .setMessage("Do you want to buy this ticket to ${DataManager.nations[ticketClickedPosition].nation} " +
                        "for ${DataManager.nations[ticketClickedPosition].ticketFare} kr?")
            .setPositiveButton("Purchase") {dialog, which ->
                wallet -= DataManager.nations[ticketClickedPosition].ticketFare
                shoppingWalletTextVIew.text = " ${wallet.toString()} kr"
                DataManager.nations[ticketClickedPosition].purchased = true
                recyclerView.adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.show()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main, menu)
        return true
    }


}
