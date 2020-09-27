package com.example.examapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ShoppingActivity : AppCompatActivity() {


    lateinit var shoppingWalletTextVIew: TextView

    var nationList = listOf<NationClass>(NationClass("Sweden", 100),
                                         NationClass("France", 355),
                                         NationClass("Norway", 270),
                                         NationClass("Spain", 600))




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this, nationList)

        Log.d ("!!!", nationList.toString())
        recyclerView.adapter = adapter


        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)
        var wallet = intent.getIntExtra("WALLET",990)
        shoppingWalletTextVIew.text = wallet.toString()




    }
}