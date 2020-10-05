package com.example.examapp

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class QuestionClass (val question: String,
                     val answer1: String,
                     val answer2: String,
                     val answer3: String,
                     val answer4: String,
                     val position: LatLng,
                     val city: String = "Taipei",
                     val questionImageResource: Int = R.drawable.jj) {


    
}