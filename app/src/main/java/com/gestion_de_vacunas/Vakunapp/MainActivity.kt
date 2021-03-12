package com.gestion_de_vacunas.Vakunapp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.home.HomeActivity
import com.gestion_de_vacunas.Vakunapp.home.perfil.RegistrarActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var mProgressBar: ProgressDialog

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fcmToken: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.init(this)

        //SI EL USUARIO ESTA LOGEADO ABRIR LA REFERENCIA A LA DB
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        //CAPTURAR EL TOKEN DE FCM SIEMPRE QUE SE ABRA LA APP
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->

            if (!task.isSuccessful) {
                Log.w("TOKEN FCM FAILED", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            //CAPTURAR EL TOKEN Y GGUARDARLO EN LA DB
            fcmToken = task.result.toString()
            AppPreferences.fcmtoken = fcmToken
            if(AppPreferences.isLogin){
                databaseReference = database.reference.child("/Users").child(AppPreferences.uid.toString())
                databaseReference.child("fcmToken").setValue(fcmToken)
            }
            Log.d("TOKEN FCM", fcmToken)

        })

        // SI ESTA LOGEADO LO MANDAMOS AL HOMEACTIVITY
        if(AppPreferences.isLogin){
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }else{
            setContentView(R.layout.activity_form_main)
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

                            val user: FirebaseUser = mAuth.currentUser!!

                            // SI SE INICIÓ CORRECTAMENTE LA SESIÓN VAMOS A LA VISTA HOME DE LA APLICACIÓN
                            AppPreferences.isLogin = true
                            AppPreferences.uid = user.uid
                            AppPreferences.username = email
                            AppPreferences.fcmtoken = fcmToken

                            databaseReference = database.reference.child("/Users").child(user.uid)
                            databaseReference.child("fcmToken").setValue(fcmToken)

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

    /*override fun onBackPressed() {
        super.onDestroy()
    }*/

}
