package com.gestion_de_vacunas.Vakunapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AcercaDeFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__acercade)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }
}