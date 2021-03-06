package com.example.examapp

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : MenuClass(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    lateinit var activeMarker: Marker
    var ticketClickedPosition = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    // This is for what's gonna happen when coming back from QuestionActivity through "finish()"
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        ticketClickedPosition = data!!.getIntExtra("TICKET_CLICKED_FROM_QUESTION", 9)


        if (DataManager.nations[ticketClickedPosition].purchased && DataManager.clearMaps){
                mMap.clear()
                addMarkers()
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DataManager.nations[ticketClickedPosition].questionClassList[0].position, 2.0F))
                activeMarker.isVisible = false
                DataManager.clearMaps = false

        }
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


        // Style the Maps
        try{
            var success: Boolean = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
            if(!success){
                Log.e("try", "Style parsing failed.")
            }
        }
        catch (e: Resources.NotFoundException){
            Log.e("try", "Can't find style. Error: ", e)
        }



        // These are markers that show automatically once one enters:
        val kiruna = QuestionClass("Year 2010 it was decided that the whole city center of Kiruna were to be relocated, what was the reason?", "Mining-related subsidence", "Large Chemical leakage", "Atomic accident", "Wildlife preservation", LatLng(67.8558432,20.2251198), "Kiruna", R.drawable.kiruna)
        val kirunaMarker = mMap.addMarker(MarkerOptions().position(kiruna.position).title("You are in Kiruna"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kiruna.position, 1.0F))
        kirunaMarker.tag = kiruna

        val stockholm = QuestionClass("Who is the architect that created Stockholm City Hall?", "Ragnar Östberg", "Carl Von Linné'", "Gustav Vasa", "Nils Eriksson", LatLng(59.3275031,18.0548047),"Stockholm", R.drawable.stockholm)
        val stockholmMarker = mMap.addMarker(MarkerOptions().position(stockholm.position).title("You are in Stockholm"))
        stockholmMarker.tag = stockholm

        val gothenburg = QuestionClass("'Feskekörka' is a famous tourist site in Gothenburg, what is it?", "Seafood market", "Church for fishermen", "Library", "Fishing museum", LatLng(57.7010889,11.9576791),"Gothenburg", R.drawable.gothenburg)
        var gothenburgMarker = mMap.addMarker(MarkerOptions().position(gothenburg.position).title("You are in Gothenburg"))
        gothenburgMarker.tag = gothenburg


        DataManager.userName = intent.getStringExtra("USER_INPUT")!!




        mMap.setOnMarkerClickListener { marker ->

            activeMarker = marker

            if (marker.isInfoWindowShown) {

                marker.hideInfoWindow()
                Toast.makeText(applicationContext,"Press the white window! ",Toast.LENGTH_LONG).show()
            } else {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 8.0F))
                Toast.makeText(applicationContext,"Press the white window! ",Toast.LENGTH_SHORT).show()
                marker.showInfoWindow()
            }
            true
        }


        mMap.setOnInfoWindowClickListener {

            DataManager.totalQleft -= 1

            val intent = Intent (this, QuestionActivity::class.java)

            intent.putExtra("TAG_QUESTION", (activeMarker.tag as QuestionClass).question)
            intent.putExtra("TAG_A1", (activeMarker.tag as QuestionClass).answer1)
            intent.putExtra("TAG_A2", (activeMarker.tag as QuestionClass).answer2)
            intent.putExtra("TAG_A3", (activeMarker.tag as QuestionClass).answer3)
            intent.putExtra("TAG_A4", (activeMarker.tag as QuestionClass).answer4)
            intent.putExtra("CURRENT_CITY", (activeMarker.tag as QuestionClass).city)
            intent.putExtra("TAG_QUESTION_BACKGROUND", (activeMarker.tag as QuestionClass).questionImageResource)



            activeMarker.isVisible = false
            startActivityForResult(intent,999)
        }
    }


    // Is used after purchase tickets:
    fun addMarkers (){

        var newMarkerList = mutableListOf<Marker>()

        for(i in 0 until (DataManager.nations[ticketClickedPosition].questionClassList.size)) {

            newMarkerList.add(mMap.addMarker(MarkerOptions().position(DataManager.nations[ticketClickedPosition].questionClassList[i].position)
                    .title("You are in ${DataManager.nations[ticketClickedPosition].questionClassList[i].city}")))

            var detail = QuestionClass(
                DataManager.nations[ticketClickedPosition].questionClassList[i].question,
                DataManager.nations[ticketClickedPosition].questionClassList[i].answer1,
                DataManager.nations[ticketClickedPosition].questionClassList[i].answer2,
                DataManager.nations[ticketClickedPosition].questionClassList[i].answer3,
                DataManager.nations[ticketClickedPosition].questionClassList[i].answer4,
                DataManager.nations[ticketClickedPosition].questionClassList[i].position,
                DataManager.nations[ticketClickedPosition].questionClassList[i].city,
                DataManager.nations[ticketClickedPosition].questionClassList[i].questionImageResource)

            newMarkerList[i].tag = detail
        }
    }

    // For the OptionsMenu: from MenuClass
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return true
    }
}