package com.example.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
/*
class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private lateinit var mMap: GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap
            createMarker()
            mMap.setOnMyLocationButtonClickListener(this)
            mMap.setOnMyLocationButtonClickListener(this)
            enableMyLocation()
        }


    private fun createMarker() {

        val Punto_Vacunación_Cruz_Roja_Galerías = LatLng(4.6431896, -74.0769369)
        mMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Galerías).title("Punto_Vacunación_Cruz_Roja_Galerías"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Punto_Vacunación_Cruz_Roja_Galerías))

        val Punto_Vacunación_Cruz_Roja_Andino = LatLng(4.6667831, -74.0548946)
        mMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Andino).title("Punto_Vacunación_Cruz_Roja_Andino"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Punto_Vacunación_Cruz_Roja_Andino))

        val Punto_Vacunación_Cruz_Roja_Unicentro = LatLng(4.5858043, -74.209426)
        mMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Unicentro).title("Punto_Vacunación_Cruz_Roja_Unicentro"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Punto_Vacunación_Cruz_Roja_Unicentro))

        val Puesto_de_Salud_La_Despensa = LatLng(4.5887633, -74.1991682)
        mMap.addMarker(MarkerOptions().position(Puesto_de_Salud_La_Despensa).title("Puesto_de_Salud_La_Despensa"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Puesto_de_Salud_La_Despensa))

        val HMGY_Consulta_Externa = LatLng(4.5793473, -74.2240235)
        mMap.addMarker(MarkerOptions().position(HMGY_Consulta_Externa).title("HMGY_Consulta_Externa"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HMGY_Consulta_Externa))

        val Cruz_Roja_Punto_De_Vacunacion = LatLng(4.6230108, -74.1429242)
        val marker = MarkerOptions().position(Cruz_Roja_Punto_De_Vacunacion).title("Cruz_Roja_Punto_De_Vacunacion")
        mMap.addMarker(marker)
        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(Cruz_Roja_Punto_De_Vacunacion, 11f),
                4000,
                null
        )
    }

    private fun isPermissionsGranted() =

            ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED

    private fun enableMyLocation() {
        if (!::mMap.isInitialized) return
        if (isPermissionsGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mMap.isMyLocationEnabled = true
            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::mMap.isInitialized) return
        if(!isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = false
            Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estás en ${p0.latitude}, ${p0.longitude}",
                Toast.LENGTH_SHORT).show()

    }
}
 */
