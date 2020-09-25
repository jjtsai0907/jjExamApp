package com.example.examapp

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // create markers:
        val taiwan = QuestionClass("Which is the capital of Taiwan?", "Kaoshuong", "Taipei", "Taichung", "Hulian", LatLng(24.2616609, 120.5543753), false)
        val taiwanMarker = mMap.addMarker(MarkerOptions().position(taiwan.position).title("You are in Taiwan"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan.position, 1.0F))
        taiwanMarker.tag = taiwan

        val hongkong = QuestionClass("How many harbours does HK have?", "12", "6", "13", "21", LatLng(20.2616609, 115.5543753), false)
        val hkMarker = mMap.addMarker(MarkerOptions().position(hongkong.position).title("HK"))
        hkMarker.tag = hongkong

        val indo = QuestionClass("How many official languages are there in this nation?", "6", "2", "9", "20", LatLng(4.2616609, 120.5543753), false)
        var indoMarker = mMap.addMarker(MarkerOptions().position(indo.position).title("BJ"))
        indoMarker.tag = indo

        //val listofmarkers = mutableListOf<QuestionClass>()





        lateinit var currentPosition: Marker
        var walletFromQuestion: Int = 0


        lateinit var activeMarker: Marker


        mMap.setOnMarkerClickListener { marker ->

            activeMarker = marker
            Log.d("ooo", (marker.tag as QuestionClass).question.toString())

            if (marker.isInfoWindowShown) {

                marker.hideInfoWindow()
                Toast.makeText(applicationContext,"Press the info to rock n roll! ",Toast.LENGTH_LONG).show()

            } else {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 4.0F))
                Toast.makeText(applicationContext,"Press the info to rock n roll! ",Toast.LENGTH_SHORT).show()
                marker.showInfoWindow()

                //walletFromQuestion = intent.getIntExtra("WALLET_QUESTION", 0)
                //Toast.makeText(this, walletFromQuestion.toString(), Toast.LENGTH_LONG).show()

            }
            true
        }


        mMap.setOnInfoWindowClickListener {



            val intent = Intent (this, QuestionActivity::class.java)
            //intent.putExtra("currentCountry",currentPosition)
            //intent.putExtra("WALLET_MAPS", walletFromQuestion)
            intent.putExtra("TAG_QUESTION", (activeMarker.tag as QuestionClass).question)
            intent.putExtra("TAG_A1", (activeMarker.tag as QuestionClass).answer1)
            intent.putExtra("TAG_A2", (activeMarker.tag as QuestionClass).answer2)
            intent.putExtra("TAG_A3", (activeMarker.tag as QuestionClass).answer3)
            intent.putExtra("TAG_A4", (activeMarker.tag as QuestionClass).answer4)
            intent.putExtra("TAG_Closed", (activeMarker.tag as QuestionClass).cleared)
            //Log.d("ooo", (activeMarker.tag as QuestionClass).question.toString())
            //activeMarker.isVisible = false

            startActivity(intent)

        }

    }

}