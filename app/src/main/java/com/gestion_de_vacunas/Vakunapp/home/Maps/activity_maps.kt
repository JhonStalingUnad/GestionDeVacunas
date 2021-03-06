package com.gestion_de_vacunas.Vakunapp.home.Maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gestion_de_vacunas.Vakunapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class activity_maps : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val Punto_Vacunación_Cruz_Roja_Galerías = LatLng(4.6431896, -74.0769369)
        googleMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Galerías).title("Punto_Vacunación_Cruz_Roja_Galerías"))

        val Punto_Vacunación_Cruz_Roja_Andino = LatLng(4.6667831, -74.0548946)
        googleMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Andino).title("Punto_Vacunación_Cruz_Roja_Andino"))

        val Punto_Vacunación_Cruz_Roja_Unicentro = LatLng(4.5858043, -74.209426)
        googleMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Unicentro).title("Punto_Vacunación_Cruz_Roja_Unicentro"))

        val Puesto_de_Salud_La_Despensa = LatLng(4.5887633, -74.1991682)
        googleMap.addMarker(MarkerOptions().position(Puesto_de_Salud_La_Despensa).title("Puesto_de_Salud_La_Despensa"))

        val HMGY_Consulta_Externa = LatLng(4.5793473, -74.2240235)
        googleMap.addMarker(MarkerOptions().position(HMGY_Consulta_Externa).title("HMGY_Consulta_Externa"))

        val Centro_Médico_Colmédica_Salitre_Capital = LatLng(4.625285,-74.1494211)
        googleMap.addMarker(MarkerOptions().position(Centro_Médico_Colmédica_Salitre_Capital).title("Centro_Médico_Colmédica_Salitre_Capital"))

        val Unidad_de_Servicios_de_Salud = LatLng(4.5793473, -74.2240235)
        googleMap.addMarker(MarkerOptions().position(Unidad_de_Servicios_de_Salud).title("Unidad_de_Servicios_de_Salud"))

        val Cruz_Roja_Aeropuerto = LatLng(4.625285,-74.1494211)
        googleMap.addMarker(MarkerOptions().position(Cruz_Roja_Aeropuerto).title("Cruz_Roja_Aeropuerto"))

        val Centro_de_Salud_San_Juan_Bosco = LatLng(4.5853141,-74.1593857)
        googleMap.addMarker(MarkerOptions().position(Centro_de_Salud_San_Juan_Bosco).title("Centro_de_Salud_San_Juan_Bosco"))

        val Centro_de_salud_upa_50 = LatLng(4.6328817,-74.1480561)
        googleMap.addMarker(MarkerOptions().position(Centro_de_salud_upa_50).title("Centro_de_salud_upa_50"))

        val Unidad_Primaria_de_Atención_UPA_Boyacá_Real = LatLng(4.6328817,-74.1480561)
        googleMap.addMarker(MarkerOptions().position(Unidad_Primaria_de_Atención_UPA_Boyacá_Real).title("Unidad_Primaria_de_Atención_UPA_Boyacá_Real"))

        val Hospital_Engativá = LatLng(4.6667156,-74.1159747)
        googleMap.addMarker(MarkerOptions().position(Hospital_Engativá).title("Hospital_Engativá"))

        val HOSPITAL_DEL_SUR_UPA_CLASS_91 = LatLng(4.6154427,-74.1760529)
        googleMap.addMarker(MarkerOptions().position(HOSPITAL_DEL_SUR_UPA_CLASS_91).title("HOSPITAL_DEL_SUR_UPA_CLASS_91"))

        val Cruz_Roja_Punto_De_Vacunacion = LatLng(4.6230108, -74.1429242)
        googleMap.addMarker(MarkerOptions().position(Cruz_Roja_Punto_De_Vacunacion).title("Cruz_Roja_Punto_De_Vacunacion"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Cruz_Roja_Punto_De_Vacunacion, 11f),
                4000,
                null)











    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_activity_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}