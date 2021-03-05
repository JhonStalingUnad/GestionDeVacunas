package com.gestion_de_vacunas.Vakunapp.home.perfil

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.gestion_de_vacunas.Vakunapp.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_registrar.*
import kotlinx.android.synthetic.main.activity_form_registrar_miembros.*
import kotlin.properties.Delegates

class RegistrarActivity : AppCompatActivity() {

    private lateinit var accion: String

    private lateinit var idUser: String


    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtDocumentType: Spinner
    private lateinit var txtDocumentNumber: EditText
    private lateinit var txtNumberPhone: EditText

    private lateinit var  progressBar: ProgressDialog
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private var firstName by Delegates.notNull<String>()
    private var lastName by Delegates.notNull<String>()
    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private var documentType by Delegates.notNull<String>()
    private var documentNumber by Delegates.notNull<String>()
    private var numberPhone by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_registrar)
        initialise()
    }


    //METODO DE INICIALIZACION
    private fun initialise() {

        txtName = findViewById(R.id.txtName)
        txtLastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtDocumentType = findViewById(R.id.spRegTipoDocumento)
        txtDocumentNumber = findViewById(R.id.etNumberDocument)
        txtNumberPhone = findViewById(R.id.etNumberPhone)

        progressBar = ProgressDialog(this)

        //INICIALIZO LA BASE DE DATOS
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        //INICIALIZO LA AUTHENTICACION DE FIREBASE
        auth = FirebaseAuth.getInstance()

        //TABLA EN LA CUAL SE VA A ALMACENAR
        databaseReference = database.reference.child("Users")

        val intentExtras = intent.extras

        var id = ""
        var firstname = ""
        var lastname = ""
        var email = ""
        var password = ""
        var type_document = ""
        var number_document = ""
        var phone_number = ""

        if (intentExtras != null) {
            accion = "Edit"
            //idMember = intentExtras.getString("id")
            //id = intentExtras.getString("id")

            idUser = intentExtras.getString("id")
            firstname = intentExtras.getString("firstName")
            lastname = intentExtras.getString("lastName")
            email = intentExtras.getString("email")
            password = intentExtras.getString("password")
            type_document = intentExtras.getString("typeDocument")
            number_document = intentExtras.getString("numberDocument")
            phone_number = intentExtras.getString("numberPhone")
        } else {
            accion = "Create"
        }

        //Se instancia la base de datos, pasando la referencia con el que se definió en Firebase.
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        val btnCrearCuenta: Button = findViewById(R.id.btnCrearCuenta)
        btnCrearCuenta.setOnClickListener {

            if (accion == "Create") {
                createNewAccount()
            }
        }
    }

    //METODO PARA REGISTRAR UN USUARIO
    private fun createNewAccount() {

        val positionDocumentType = spRegTipoDocumento.selectedItemPosition

        //CAPTURAR DATOS DEL FORMULARIO
        firstName = txtName.text.toString()
        lastName = txtLastName.text.toString()
        email = txtEmail.text.toString()
        password = txtPassword.text.toString()
        documentType = spRegTipoDocumento.getItemAtPosition(positionDocumentType).toString()
        documentNumber = txtDocumentNumber.text.toString()
        numberPhone = txtNumberPhone.text.toString()


        //VALIDAR LOS CAMPOS DILIGENCIADOS
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(documentType) && !TextUtils.isEmpty(documentNumber)
                && !TextUtils.isEmpty(numberPhone)
        ) {

            //MOSTRAR LOADING
            progressBar.setMessage("Registrando usuario ...")
            progressBar.show()


            //METODO DE FIREBASE PARA CREAR UN USUARIO
            auth.createUserWithEmailAndPassword(email, password)
                    //METODO DE CREACION EXITOSA
                    .addOnCompleteListener(this) {

                        val user: FirebaseUser = auth.currentUser!!

                        //GUARDAMOS UN NUEVO ELEMENTO CON EL ID DEL USUARIO
                        val currentUserDb = databaseReference.child(user.uid)
                        currentUserDb.child("firstName").setValue(firstName)
                        currentUserDb.child("lastName").setValue(lastName)
                        currentUserDb.child("typeDocument").setValue(documentType)
                        currentUserDb.child("numberDocument").setValue(documentNumber)
                        currentUserDb.child("numberPhone").setValue(numberPhone)

                        //NOS REDIRIGIMOS AL HOMEACTIVITY
                        AppPreferences.isLogin = true
                        AppPreferences.uid = user.uid
                        AppPreferences.username = email

                        //OCULTAMOS EL LOADING
                        progressBar.hide()

                        //NOS REDIRIGIMOS AL HOMEACTIVITY
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)

                    }
                    //METODO DE CREACION FALLIDA
                    .addOnFailureListener{
                        Toast.makeText(this, "Error creando el usuario.", Toast.LENGTH_SHORT).show()
                    }

        } else {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    /*********** FUNCIONES DE INVOCACION DIRECTA DESDE LA VISTA ************/
    fun register(view: View){
        createNewAccount()
    }

}