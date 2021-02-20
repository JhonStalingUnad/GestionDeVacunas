package com.gestion_de_vacunas.Vakunapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RecordarActivity : AppCompatActivity() {

    private var etEmail: EditText? = null
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordar)
        initialise()
    }


    //METODO DE INICIALIZACION
    private fun initialise() {
        etEmail = findViewById<View>(R.id.etEmail) as EditText
        mAuth = FirebaseAuth.getInstance()
    }


    private fun sendPasswordResetEmail() {
        val email = etEmail?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            mAuth!!
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Email de restauracion enviado", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, "No se encontró el usuario con este correo", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "Digite el correo", Toast.LENGTH_SHORT).show()
        }
    }


    /*********** FUNCIONES DE INVOCACION DIRECTA DESDE LA VISTA ************/
    fun reset(view: View) {
        sendPasswordResetEmail()
    }

}