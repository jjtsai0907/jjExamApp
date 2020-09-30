package com.example.examapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_nation.*


class ShoppingActivity : AppCompatActivity(), NationListRecycleAdapter.onItemClickListener {


    lateinit var shoppingWalletTextVIew: TextView
    lateinit var recyclerView: RecyclerView
    var wallet: Int = 0
    var walletAfterPurchase : Int = 0





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        var intentButtonToMaps = findViewById<Button>(R.id.intentButtonToMaps)
        var buttonToQue = findViewById<Button>(R.id.buttonToQue)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, DataManager.nations, this)
        recyclerView.adapter = adapter



        //recyclerView.adapter.onBindViewHolder(viewHold,3)

        //recyclerView.adapter?.notifyDataSetChanged()


        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)
        wallet = intent.getIntExtra("WALLET_QUESTION",0)
        shoppingWalletTextVIew.text = wallet.toString()



        /*if (DataManager.nations[1].purchased){
            wallet -= DataManager.nations[1].ticketFare
            shoppingWalletTextVIew.text = wallet.toString()
        }*/

        /*var ttt = NationListRecycleAdapter(this, DataManager.nations).calculate()
        wallet -= ttt

        shoppingWalletTextVIew.text = wallet.toString()*/
    }

    /*override fun onResume(){
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    } */

    fun goBackToQuestion (view: View){
        var intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("WALLET_SHOPPING", wallet)
        //Toast.makeText(this, wallet.toString(), Toast.LENGTH_LONG).show()
        //startActivity(intent)

        setResult(111,intent)
        finish()

        //recyclerView.adapter?.notifyDataSetChanged()
        //var intent = Intent(this, MapsActivity::class.java)
        //Toast.makeText(this, DataManager.nations[].nation,Toast.LENGTH_LONG).show()

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(ticketClickedPosition: Int) {



        wallet -= DataManager.nations[ticketClickedPosition].ticketFare
        //walletAfterPurchase = wallet
        shoppingWalletTextVIew.text = wallet.toString()
        nationTextView.text = "${DataManager.nations[ticketClickedPosition].nation} Purchased"
        DataManager.nations[ticketClickedPosition].markerShown = true
            //this.alpha = 0.2F
        //Toast.makeText(this, "Bajs!!!",Toast.LENGTH_SHORT).show()
        DataManager.nations[ticketClickedPosition].purchased = true
        //DataManager.nations[position].ticketFare = 0

        recyclerView.adapter?.notifyDataSetChanged()


    }

    override fun onLongClick(position: Int) {
        Toast.makeText(this, "Heeeeeey",Toast.LENGTH_SHORT).show()
    }


}
