package com.gestion_de_vacunas.Vakunapp.ui.recordatorio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.R

class RecordatorioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio)

        val btnLogin: Button = findViewById(R.id.buttonGuardarRecordatorio);
        btnLogin.setOnClickListener {
            super.onBackPressed();
        }
    }

}