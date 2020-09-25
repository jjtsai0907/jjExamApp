package com.example.examapp

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class QuestionClass (val question: String,
                     val answer1: String,
                     val answer2: String,
                     val answer3: String,
                     val answer4: String,
                     val position: LatLng,
                     val cleared: Boolean) {


    
}