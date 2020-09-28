package com.example.examapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import java.util.*



class ShoppingActivity : AppCompatActivity() {


    lateinit var shoppingWalletTextVIew: TextView
    lateinit var recyclerView: RecyclerView

    /*var nationList = mutableListOf<NationClass>(NationClass("Sweden", 100, QuestionClass("Which is the capital of Sweden?", "Correct", "Nacka", "Taichung", "Hulian", LatLng(24.2616609, 10.5543753), false)),
                                         NationClass("France", 355, QuestionClass("Which is the capital of France?", "Correct", "Paris", "Taichung", "Hulian", LatLng(24.2616609, 20.5543753), false)),
                                         NationClass("Norway", 270, QuestionClass("Which is the capital of Norway?", "Correct", "Oslo", "Taichung", "Hulian", LatLng(24.2616609, 320.5543753), false)),
                                         NationClass("Spain", 600, QuestionClass("Which is the capital of Spain?", "Correct", "Madrid", "Taichung", "Hulian", LatLng(24.2616609, 40.5543753), false)))*/






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        var intentButtonToMaps = findViewById<Button>(R.id.intentButtonToMaps)
        var buttonToQue = findViewById<Button>(R.id.buttonToQue)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, DataManager.nations)
        //Log.d ("!!!", nationList.toString())
        recyclerView.adapter = adapter

        //recyclerView.adapter.onBindViewHolder(viewHold,3)

        //recyclerView.adapter?.notifyDataSetChanged()


        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)
        var wallet = intent.getIntExtra("WALLET",990)
        shoppingWalletTextVIew.text = wallet.toString()




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


    /*override fun onResume() {
        super.onResume()
        //Log.d("!!", DataManager.nations[])


        recyclerView.adapter?.notifyDataSetChanged()
    }*/



}
