package com.example.examapp

import android.util.Log
import com.google.android.gms.maps.model.LatLng

//singleton
object DataManager {


    val nations = mutableListOf<NationClass>()

    init{
        createMockData()
    }

    fun createMockData(){

        nations.add(NationClass("Sweden", 900, mutableListOf(
            QuestionClass("How many harbours does SWEDEN have?", "Correct", "SWEDEN", "harbours", "21", LatLng(59.3189444,18.233228)),
            QuestionClass("How many schools does SWEDEN have?", "Correct", "SWEDEN", "schools", "21", LatLng(59.329150, 18.302500)),
            QuestionClass("How many dogs does SWEDEN have?", "Correct", "SWEDEN", "dogs", "21", LatLng(59.4141746,18.2186214)),
            QuestionClass("How many ice cream s does SWEDEN have?", "Correct", "SWEDEN", "ice cream", "21", LatLng(58.263996, 12.701151)),
            QuestionClass("How cute is a cat?", "Very", "Sådär", "OK", "Not at all", LatLng(58.385150, 11.252655)))))

        nations.add(NationClass("Spain", 200, mutableListOf(QuestionClass("How many harbours does SWEDEN have?", "Correct", "SWEDEN", "13", "21", LatLng(20.2616609, 115.5543753)),
            QuestionClass("How cute is Nicke Baby?", "Very2", "Sådär", "OK", "Not at all", LatLng(59.7658945,16.8078885)))))


        nations.add(NationClass("USA", 10000, mutableListOf(
            QuestionClass("How many harbours does SWEDEN have?", "Correct", "SWEDEN", "harbours", "21", LatLng(59.3189444,18.233228)),
            QuestionClass("How many schools does SWEDEN have?", "Correct", "SWEDEN", "schools", "21", LatLng(59.329150, 18.302500)),
            QuestionClass("How many dogs does SWEDEN have?", "Correct", "SWEDEN", "dogs", "21", LatLng(59.4141746,18.2186214)),
            QuestionClass("How many ice cream s does SWEDEN have?", "Correct", "SWEDEN", "ice cream", "21", LatLng(58.263996, 12.701151)),
            QuestionClass("How cute is a cat?", "Very", "Sådär", "OK", "Not at all", LatLng(58.385150, 11.252655)))))


        nations.add(NationClass("Iceland", 1100, mutableListOf(
            QuestionClass("How many harbours does SWEDEN have?", "Correct", "Iceland", "harbours", "21", LatLng(64.142314, -21.056091)),
            QuestionClass("How many schools does SWEDEN have?", "Correct", "SWEDEN", "schools", "21", LatLng(60.116762, -18.677731)),
            QuestionClass("How many dogs does SWEDEN have?", "Correct", "SWEDEN", "dogs", "21", LatLng(65.220143, -16.671786)),
            QuestionClass("How many ice cream s does SWEDEN have?", "Correct", "SWEDEN", "ice cream", "21", LatLng(64.869977, -20.901669)),
            QuestionClass("How cute is a cat?", "Very", "Sådär", "OK", "Not at all", LatLng(65.644703, -23.082149)))))

    }

}