package com.gestion_de_vacunas.Vakunapp.ui.miembro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.R

class MiembroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_miembros)

        val btnLogin: Button = findViewById(R.id.buttonGuardarRecordatorio);
        btnLogin.setOnClickListener {
            super.onBackPressed();
        }
    }

}