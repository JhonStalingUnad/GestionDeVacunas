package com.gestion_de_vacunas.Vakunapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class ActivityEditarperfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarperfil)

        val spinner = findViewById<Spinner>(R.id.spinner3)
       //* val lista = listOf("Cédula de Ciudadanía","Tarjeta de identidad","Cédula de extranjería","Registro civil")
        val lista = resources.getStringArray(R.array.tipo_documento)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,lista)
        spinner.adapter = adaptador
    }
}