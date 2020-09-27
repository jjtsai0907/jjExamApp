package com.example.examapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ShoppingActivity : AppCompatActivity() {


    lateinit var shoppingWalletTextVIew: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NationListRecycleAdapter(this)
        recyclerView.adapter = adapter


        shoppingWalletTextVIew = findViewById(R.id.shoppingWalletTextView)
        var wallet = intent.getIntExtra("WALLET",990)
        shoppingWalletTextVIew.text = wallet.toString()




    }
}