package com.example.examapp

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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




        // Add a marker in Sydney and move the camera
        val chingShui = LatLng(24.2616609, 120.5543753)
        var taiwanMarker = mMap.addMarker(MarkerOptions().position(chingShui).title("Taiwan - questions about JJ's hometown"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chingShui, 1.0F))
        taiwanMarker.tag = "Taiwan"



        val sandBol = LatLng(58.9367904,12.5238587)
        var sandBolMarker = mMap.addMarker(MarkerOptions().position(sandBol).title("Sweden - questions about Sweden"))
        sandBolMarker.tag = "SandBol"


        val homeNacka = LatLng(18.2081305,9.309901)
        var homeNackaMarker = mMap.addMarker(MarkerOptions().position(homeNacka).title("Africccca - questions about the continent"))
        homeNackaMarker.tag = "Nacka"

        val grandCanyon = LatLng(36.0911045,-113.4036111)
        var gcMarker = mMap.addMarker(MarkerOptions().position(grandCanyon).title("USA - questions about Grand Canyon"))
        gcMarker.tag ="Grand Canyon"

        //val markers = mMap.addMarker(MarkerOptions())
        //markers.tag= "Heeeeeelo"

        lateinit var currentPosition: String


        mMap.setOnInfoWindowClickListener {


            val intent = Intent (this, QuestionActivity::class.java)
            intent.putExtra("currentCountry",currentPosition)
            startActivity(intent)

        }


        mMap.setOnMarkerClickListener { marker ->

            currentPosition=marker.tag.toString()

            if (marker.isInfoWindowShown) {

                marker.hideInfoWindow()
                Toast.makeText(applicationContext,"Press the info to rock n roll! ",Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(applicationContext,"Press the info to rock n roll! ",Toast.LENGTH_SHORT).show()
                marker.showInfoWindow()


            }
            true
        }


    }

}