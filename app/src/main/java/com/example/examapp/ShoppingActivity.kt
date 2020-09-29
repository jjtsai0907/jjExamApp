package com.example.examapp

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





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        var intentButtonToMaps = findViewById<Button>(R.id.intentButtonToMaps)
        var buttonToQue = findViewById<Button>(R.id.buttonToQue)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, DataManager.nations, this)
        //Log.d ("!!!", nationList.toString())
        recyclerView.adapter = adapter



        //recyclerView.adapter.onBindViewHolder(viewHold,3)

        //recyclerView.adapter?.notifyDataSetChanged()


        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)
        wallet = intent.getIntExtra("WALLET",990)
        shoppingWalletTextVIew.text = wallet.toString()



        if (DataManager.nations[1].purchased){
            wallet -= DataManager.nations[1].ticketFare
            shoppingWalletTextVIew.text = wallet.toString()
        }

        /*var ttt = NationListRecycleAdapter(this, DataManager.nations).calculate()
        wallet -= ttt

        shoppingWalletTextVIew.text = wallet.toString()*/
    }

    /*override fun onResume(){
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    } */

    fun goBackToQuestion (view: View){

        //recyclerView.adapter?.notifyDataSetChanged()
        //var intent = Intent(this, MapsActivity::class.java)
        //Toast.makeText(this, DataManager.nations[].nation,Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onClick(position: Int) {
        Toast.makeText(this, "Bajs!!!",Toast.LENGTH_SHORT).show()

        if (!DataManager.nations[1].purchased) {
            wallet -= DataManager.nations[position].ticketFare
            shoppingWalletTextVIew.text = wallet.toString()
            nationTextView.text = "${DataManager.nations[position].nation} Purchased"
            DataManager.nations[position].shown = true
            //this.alpha = 0.2F

            DataManager.nations[position].purchased = true

            recyclerView.adapter?.notifyDataSetChanged()
        }

    }

    override fun onLongClick(position: Int) {
        Toast.makeText(this, "Heeeeeey",Toast.LENGTH_SHORT).show()
    }


}
