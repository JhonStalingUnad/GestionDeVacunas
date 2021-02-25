package com.gestion_de_vacunas.Vakunapp.home.recordatorio

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_form_recordatorio.*

class RecordatorioFormActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_recordatorio)


        //BASE DE DATOS
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
        databaseReference = database.reference.child("Users").child("uEDBvPYSxtMCPwXBX6jNPAGPJms1")


        //TRAER LOS DATOS MEDIANTE UN ESCUCHA
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error trayendo datos", "Error trayendo datos")
            }

            //CUANDO SE TRAIGAN LOS DATOS
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children

                //RECORRER CADA KEY DEL REGISTRO
                children.forEach {

                    //it.key.toString() --> KEY DEL REGISTRO
                    //it.value.toString() --> VALOR DEL REGISTRO

                    if( it.key.toString() == "firstName" ){
                        testField.setText( it.value.toString() )
                    }

                }
            }
        })




        //CARGAR DATOS DE UN SPINNER
        val miembrosArray = arrayOf("Javier Zapata", "Angelica Valencia", "Jairo Castillo")
        val spinner: Spinner = findViewById(R.id.spinnerMiembros)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, miembrosArray)


        //EVENTO CLICK EN BOTON GUARDAR RECORDATORIO
        val btnLogin: Button = findViewById(R.id.buttonGuardarRecordatorio);
        btnLogin.setOnClickListener {


            //TABLA EN LA CUAL SE VA A ALMACENAR
            databaseReference = database.reference.child("Recordatorios")

            //GUARDAMOS UN NUEVO ELEMENTO CON EL ID DEL USUARIO
            val currentUserDb = databaseReference.child("1")
            currentUserDb.child("miembroNombre").setValue("javier zapata")
            currentUserDb.child("vacunaNombre").setValue("coronavirus")


            val miembroSelected: String = spinnerMiembros.getSelectedItem().toString()
            Log.i("miembroSelected", miembroSelected);
            super.onBackPressed();

        }

    }

}