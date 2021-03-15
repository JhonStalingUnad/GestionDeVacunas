package com.gestion_de_vacunas.Vakunapp.home.maps

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gestion_de_vacunas.Vakunapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ActivityMaps : Fragment() {
    
    private val callback = OnMapReadyCallback { googleMap ->

        //PUNTOS CERCANOS BOGOTÁ
        val Punto_Vacunación_Cruz_Roja_Galerías = LatLng(4.6431896, -74.0769369)
        googleMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Galerías).title("Punto de vacunación Cruz Roja Galerías"))

        val Punto_Vacunación_Cruz_Roja_Andino = LatLng(4.6667831, -74.0548946)
        googleMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Andino).title("Punto de vacunación Cruz Roja Andino"))

        val Punto_Vacunación_Cruz_Roja_Unicentro = LatLng(4.5858043, -74.209426)
        googleMap.addMarker(MarkerOptions().position(Punto_Vacunación_Cruz_Roja_Unicentro).title("Punto de Vacunación Cruz Roja Unicentro"))

        val Puesto_de_Salud_La_Despensa = LatLng(4.5887633, -74.1991682)
        googleMap.addMarker(MarkerOptions().position(Puesto_de_Salud_La_Despensa).title("Puesto de Salud La Despensa"))

        val HMGY_Consulta_Externa = LatLng(4.5793473, -74.2240235)
        googleMap.addMarker(MarkerOptions().position(HMGY_Consulta_Externa).title("HMGY Consulta Externa"))

        val Centro_Médico_Colmédica_Salitre_Capital = LatLng(4.625285,-74.1494211)
        googleMap.addMarker(MarkerOptions().position(Centro_Médico_Colmédica_Salitre_Capital).title("Centro Médico Colmédica Salitre Capital"))

        val Unidad_de_Servicios_de_Salud = LatLng(4.5793473, -74.2240235)
        googleMap.addMarker(MarkerOptions().position(Unidad_de_Servicios_de_Salud).title("Unidad de Servicios de Salud"))

        val Cruz_Roja_Aeropuerto = LatLng(4.625285,-74.1494211)
        googleMap.addMarker(MarkerOptions().position(Cruz_Roja_Aeropuerto).title("Cruz Roja Aeropuerto"))

        val Centro_de_Salud_San_Juan_Bosco = LatLng(4.5853141,-74.1593857)
        googleMap.addMarker(MarkerOptions().position(Centro_de_Salud_San_Juan_Bosco).title("Centro de Salud San Juan Bosco"))

        val Centro_de_salud_upa_50 = LatLng(4.6328817,-74.1480561)
        googleMap.addMarker(MarkerOptions().position(Centro_de_salud_upa_50).title("Centro de Salud upa 50"))

        val Unidad_Primaria_de_Atención_UPA_Boyacá_Real = LatLng(4.6328817,-74.1480561)
        googleMap.addMarker(MarkerOptions().position(Unidad_Primaria_de_Atención_UPA_Boyacá_Real).title("Unidad Primaria de Atención UPA Boyacá Real"))

        val Hospital_Engativá = LatLng(4.6667156,-74.1159747)
        googleMap.addMarker(MarkerOptions().position(Hospital_Engativá).title("Hospital Engativá"))

        val HOSPITAL_DEL_SUR_UPA_CLASS_91 = LatLng(4.6154427,-74.1760529)
        googleMap.addMarker(MarkerOptions().position(HOSPITAL_DEL_SUR_UPA_CLASS_91).title("Hospital del Sur UPA Class 91"))

        val Cruz_Roja_Punto_De_Vacunacion = LatLng(4.6230108, -74.1429242)
        googleMap.addMarker(MarkerOptions().position(Cruz_Roja_Punto_De_Vacunacion).title("Cruz Roja Punto De Vacunación"))

        /* PUNTOS CERCANOS POPAYÁN */

        val Hospital_Universitario_San_Jose = LatLng(2.4499106, -76.5998701)
        googleMap.addMarker(MarkerOptions().position(Hospital_Universitario_San_Jose).title("Hospital Universitario San José"))

        val Clinica_Santa_Gracia = LatLng(2.459569, -76.603527)
        googleMap.addMarker(MarkerOptions().position(Clinica_Santa_Gracia).title("Clínica Santa Gracia"))

        val Hospital_Del_Norte = LatLng(2.485526, -76.563666)
        googleMap.addMarker(MarkerOptions().position(Hospital_Del_Norte).title("Hospital del Norte"))

        val Clinica_La_Estancia = LatLng(2.451046, -76.596450)
        googleMap.addMarker(MarkerOptions().position(Clinica_La_Estancia).title("Clínica la Estancia"))

        val Hospital_Susana_Lopez_De_Valencia = LatLng(2.437306, -76.619220)
        googleMap.addMarker(MarkerOptions().position(Hospital_Susana_Lopez_De_Valencia).title("Hospital Susana López de Valencia"))

        val Nueva_EPS_Popayan = LatLng(2.454831, -76.601295)
        googleMap.addMarker(MarkerOptions().position(Nueva_EPS_Popayan).title("Nueva EPS - Popayán"))

        val Sanitas_Popayan = LatLng(2.451946, -76.601659)
        googleMap.addMarker(MarkerOptions().position(Sanitas_Popayan).title("Sanitas - Popayán"))

        /* PUNTOS CERCANOS CALI */

        val Hospital_Universitario_Valle = LatLng(3.430633, -76.544236)
        googleMap.addMarker(MarkerOptions().position(Hospital_Universitario_Valle).title("Hospital Universitario del Valle"))

        val Cruz_Roja_Cali = LatLng(3.427152, -76.545095)
        googleMap.addMarker(MarkerOptions().position(Cruz_Roja_Cali).title("Cruz Roja - Seccional Cali"))

        val UNIPS = LatLng(3.424662, -76.548053)
        googleMap.addMarker(MarkerOptions().position(UNIPS).title("UNIPS"))

        val IPS_Sura_Tequendama = LatLng(3.424662, -76.548053)
        googleMap.addMarker(MarkerOptions().position(IPS_Sura_Tequendama).title("IPS Sura Tequendama"))

        val Clinica_Imbanaco = LatLng(3.426388, -76.544886)
        googleMap.addMarker(MarkerOptions().position(Clinica_Imbanaco).title("Clínica Imbanaco"))

        val Nueva_EPS_Cali = LatLng(3.433958, -76.538793)
        googleMap.addMarker(MarkerOptions().position(Nueva_EPS_Cali).title("Nueva EPS - Cali"))

        val Clinica_Esimed = LatLng(3.429143, -76.536464)
        googleMap.addMarker(MarkerOptions().position(Clinica_Esimed).title("Clínica Esimed"))

        val Clinica_San_Jose = LatLng(3.423926, -76.546008)
        googleMap.addMarker(MarkerOptions().position(Clinica_San_Jose).title("Clínica San José"))

        val SOS = LatLng(3.424336, -76.546816)
        googleMap.addMarker(MarkerOptions().position(SOS).title("SOS - Cali"))

        val EPS_Emssanar = LatLng(3.423897, -76.545069)
        googleMap.addMarker(MarkerOptions().position(EPS_Emssanar).title("EPS Emssanar"))

        val Conalmedicas = LatLng(3.426503, -76.543289)
        googleMap.addMarker(MarkerOptions().position(Conalmedicas).title("Conalmedicas"))

        val FundacionValleDeLili = LatLng(3.373352, -76.526675)
        googleMap.addMarker(MarkerOptions().position(FundacionValleDeLili).title("Fundación Valle de Lili"))

        val ClinicaDeOccidente = LatLng(3.451654, -76.532006)
        googleMap.addMarker(MarkerOptions().position(ClinicaDeOccidente).title("Clinica de Occidente"))

        /* PUNTOS CERCANOS MEDELLÍN */

        val HospitalUniversitarioSanVicenteFundacion = LatLng(6.261761, -75.565825)
        googleMap.addMarker(MarkerOptions().position(HospitalUniversitarioSanVicenteFundacion).title("Hospital Universitario San Vicente Fundación"))

        val HospitalSanVicenteDePaul = LatLng(6.262083, -75.566820)
        googleMap.addMarker(MarkerOptions().position(HospitalSanVicenteDePaul).title("Hospital San Vicente de Paul"))

        val HospitalGeneralDeMedellin = LatLng(6.234192, -75.572456)
        googleMap.addMarker(MarkerOptions().position(HospitalGeneralDeMedellin).title("Hospital General de Medellín"))

        val PabloTobonUribeHospital = LatLng(6.277012, -75.579815)
        googleMap.addMarker(MarkerOptions().position(PabloTobonUribeHospital).title("Pablo Tobón Uribe Hospital"))

        val ClinicaMedellin = LatLng(6.233176, -75.584308)
        googleMap.addMarker(MarkerOptions().position(ClinicaMedellin).title("Clínica Medellín"))

        val ClinicaSoma = LatLng(6.248964, -75.564539)
        googleMap.addMarker(MarkerOptions().position(ClinicaSoma).title("Clínica Soma"))

        val Hospital_La_Maria  = LatLng(6.286440, -75.574158)
        googleMap.addMarker(MarkerOptions().position(Hospital_La_Maria).title("Hospital La María"))

        val Clinica_Universitaria_Bolivariana  = LatLng(6.276795, -75.581926)
        googleMap.addMarker(MarkerOptions().position(Clinica_Universitaria_Bolivariana).title("Clínica Universitaria Bolivariana"))

        val UrgenciasClinicaSalucoop  = LatLng(6.224968, -75.598957)
        googleMap.addMarker(MarkerOptions().position(UrgenciasClinicaSalucoop).title("Urgencias Clínica Salucoop"))

        val NuevaClinicaSagradoCorazon  = LatLng(6.242667, -75.557436)
        googleMap.addMarker(MarkerOptions().position(NuevaClinicaSagradoCorazon).title("Nueva Clínica Sagrado Corazón"))

        /* PUNTOS CERCANOS BUCARAMANGA */

        val HospitalMilitarBucaramanga = LatLng(7.132261, -73.109988)
        googleMap.addMarker(MarkerOptions().position(HospitalMilitarBucaramanga).title("Hospital Militar Bucaramanga"))

        val LosComunerosHospitalUniversitarioBucaramanga = LatLng(7.125763, -73.118276)
        googleMap.addMarker(MarkerOptions().position(LosComunerosHospitalUniversitarioBucaramanga).title("Los Comuneros - Hospital Universitario de Bucaramanga"))

        val HospitalUniversitarioDeSantander = LatLng(7.128099, -73.113810)
        googleMap.addMarker(MarkerOptions().position(HospitalUniversitarioDeSantander).title("Hospital Universitario de Santander"))

        val HospitalLocalDelNorte = LatLng(7.148829, -73.134635)
        googleMap.addMarker(MarkerOptions().position(HospitalLocalDelNorte).title("Hospital Local del Norte"))

        val ClínicaChicamocha = LatLng(7.120431, -73.115213)
        googleMap.addMarker(MarkerOptions().position(ClínicaChicamocha).title("Clínica Chicamocha"))

        val IPSCabecera = LatLng(7.114188, -73.107741)
        googleMap.addMarker(MarkerOptions().position(IPSCabecera).title("IPS Cabecera"))

        /* PUNTOS CERCANOS PASTO */

        val HospitaUniversitarioDepartamentalDeNarino  = LatLng(1.205469, -77.268185)
        googleMap.addMarker(MarkerOptions().position(HospitaUniversitarioDepartamentalDeNarino).title("Hospital Universitario de Nariño"))

        val HospitalSanRafael  = LatLng(1.222883, -77.292005)
        googleMap.addMarker(MarkerOptions().position(HospitalSanRafael).title("Hospital San Rafael"))

        val ClinicaLosAndres = LatLng(1.227969, -77.286029)
        googleMap.addMarker(MarkerOptions().position(ClinicaLosAndres).title("Clínica Los Andres - Pasto"))

        val SanitasEPS = LatLng(1.227124, -77.286053)
        googleMap.addMarker(MarkerOptions().position(SanitasEPS).title("Sanitas EPS - Pasto"))

        val CentroDeSaludRosa = LatLng(1.192127, -77.275648)
        googleMap.addMarker(MarkerOptions().position(CentroDeSaludRosa).title("Centro de Salud La Rosa"))

        val ClinicaNuestraSenoraDeFatima = LatLng(1.217392, -77.276095)
        googleMap.addMarker(MarkerOptions().position(ClinicaNuestraSenoraDeFatima ).title("Clínica Nuestra Señora de Fátima"))

        val FundacionSanPedro  = LatLng(1.224400, -77.291481)
        googleMap.addMarker(MarkerOptions().position(FundacionSanPedro).title("Fundación San Pedro"))

        val HospitalLocalCivilPastoSalud  = LatLng(1.219093, -77.269789)
        googleMap.addMarker(MarkerOptions().position(HospitalLocalCivilPastoSalud).title("Hospital Local Civil Pasto Salud"))

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Cruz_Roja_Punto_De_Vacunacion, 11f), 4000, null)

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