package com.example.examapp

import com.google.android.gms.maps.model.LatLng

//singleton
object DataManager {


    //val ClickedItemPosition = mutableListOf<Int>()

    val nations = mutableListOf<NationClass>()

    init{
        createMockData()
    }

    fun createMockData(){
        nations.add(NationClass("Sweden", 100, QuestionClass("How many harbours does SWEDEN have?", "Correct", "SWEDEN", "13", "21", LatLng(20.2616609, 115.5543753)), false))
        nations.add(NationClass("France", 355, QuestionClass("How many harbours does FRANCE have?", "Correct", "FRANCE", "13", "21", LatLng(20.2616609, 135.5543753)), false))
        //nations.add(NationClass("Norway", 270))
        //nations.add(NationClass("Spain", 600))

    }

}