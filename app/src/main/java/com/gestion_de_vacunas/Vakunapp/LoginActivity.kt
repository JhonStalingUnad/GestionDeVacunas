package com.gestion_de_vacunas.Vakunapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent);
        }
    }

}