package com.gestion_de_vacunas.Vakunapp.home.Informacion
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gestion_de_vacunas.Vakunapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        createMarker()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
    private fun createMarker() {

        val Punto_Vacunación_Cruz_Roja_Galerías = LatLng(4.6431896,-74.0769369)
        mMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Galerías).title("Punto_Vacunación_Cruz_Roja_Galerías"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Punto_Vacunación_Cruz_Roja_Galerías))

        val Punto_Vacunación_Cruz_Roja_Andino = LatLng(4.6667831,-74.0548946)
        mMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Andino).title("Punto_Vacunación_Cruz_Roja_Andino"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Punto_Vacunación_Cruz_Roja_Andino))

        val Punto_Vacunación_Cruz_Roja_Unicentro = LatLng(4.5858043,-74.209426)
        mMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Unicentro).title("Punto_Vacunación_Cruz_Roja_Unicentro"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Punto_Vacunación_Cruz_Roja_Unicentro))

        val Puesto_de_Salud_La_Despensa = LatLng(4.5887633,-74.1991682)
        mMap.addMarker(MarkerOptions().position(Puesto_de_Salud_La_Despensa).title("Puesto_de_Salud_La_Despensa"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Puesto_de_Salud_La_Despensa))

        val HMGY_Consulta_Externa = LatLng(4.5793473,-74.2240235)
        mMap.addMarker(MarkerOptions().position(HMGY_Consulta_Externa).title("HMGY_Consulta_Externa"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HMGY_Consulta_Externa))

        val Cruz_Roja_Punto_De_Vacunacion = LatLng(4.6230108,-74.1429242)
        val marker = MarkerOptions().position(Cruz_Roja_Punto_De_Vacunacion).title("Cruz_Roja_Punto_De_Vacunacion")
        mMap.addMarker(marker)
        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(Cruz_Roja_Punto_De_Vacunacion, 11f),
                4000,
                null
        )

    }
}

