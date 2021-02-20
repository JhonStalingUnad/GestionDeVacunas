package com.gestion_de_vacunas.Vakunapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var mProgressBar: ProgressDialog
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.init(this)

        // SI ESTA LOGEADO LO MANDAMOS AL HOMEACTIVITY
        if(AppPreferences.isLogin){
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }else{
            setContentView(R.layout.activity_main)
            initialise()
        }

    }


    // CREAMOS UN MÉTODO PARA INICIALIZAR NUESTROS ELEMENTOS DEL DISEÑO Y LA AUTENTICACIÓN DE FIREBASE
    private fun initialise() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
    }


    private fun loginUser() {

        email = etEmail.text.toString()
        password = etPassword.text.toString()

        //VERIFICAMOS QUE LOS CAMPOS NO ESTE VACIOS
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            //MOSTRAMOS EL LOADING
            mProgressBar.setMessage("Iniciando sesion...")
            mProgressBar.show()

            //INICIAMOS SESIÓN CON EL MÉTODO SIGNIN Y ENVIAMOS USUARIO Y CONTRASEÑA
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        //VERIFICAMOS QUE LA TAREA SE EJECUTÓ CORRECTAMENTE
                        task ->
                        if (task.isSuccessful) {

                            // SI SE INICIÓ CORRECTAMENTE LA SESIÓN VAMOS A LA VISTA HOME DE LA APLICACIÓN
                            AppPreferences.isLogin = true
                            AppPreferences.username = email
                            AppPreferences.password = password

                            //OCULTAMOS EL LOADING
                            mProgressBar.hide()

                            //NOS REDIRIGIMOS AL HOMEACTIVITY
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)

                        } else {
                            //SI OCURRIO UN ERROR LOGEANDONOS LE INFORMAMOS
                            Toast.makeText(this, "Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "Digite todos los campos", Toast.LENGTH_SHORT).show()
        }

    }


    /*********** FUNCIONES DE INVOCACION DIRECTA DESDE LA VISTA ************/
    fun login(view: View) {
        loginUser()
    }


    fun forgotPassword(view: View) {
        startActivity( Intent(this, RecordarActivity::class.java) )
    }


    fun register(view: View) {
        startActivity( Intent(this, RegistrarActivity::class.java) )
    }

}