package com.gestion_de_vacunas.Vakunapp.home.perfil

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.gestion_de_vacunas.Vakunapp.home.miembro.Miembros
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlin.properties.Delegates

class EditarFormActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_form_editar_perfil)
        initialise()
    }

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

        //TABLA EN LA CUAL SE VA A CONSULTAR
        databaseReference = database.reference.child("Users")

        val intentExtras = intent.extras

        //var id = ""
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

        updateAcount()

        val btnEditarPerfil: Button = findViewById(R.id.btnCuentaModificar)
        btnEditarPerfil.setOnClickListener {

            if (accion == "Edit") {
                updateAcount()
            }
        }

        val btnCancel: Button = findViewById(R.id.btnCuentaCancelar)
        btnCancel.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun updateAcount(){

        // Aqui capturamos los nuevos datos del formulario
        firstName = txtName.text.toString()
        lastName = txtLastName.text.toString()
        email = txtEmail.text.toString()
        password = txtPassword.text.toString()
        documentType = txtDocumentType.getSelectedItem().toString()
        documentNumber = txtDocumentNumber.text.toString()
        numberPhone = txtNumberPhone.text.toString()

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(documentNumber) && !TextUtils.isEmpty(numberPhone)){

            if (documentType != "Seleccione el Tipo de Documento") {

                //Muestro un dialogo de Progreso, mientras se modifican los datos de un miembros en la base de datos de Firebase

                /*progressBar.setMessage("Modificando Miembro, Espere por favor ...")
                progressBar.onStart()*/

                /*Obtenemos la referencia d ela base de datos, y enviamos a actualizar el registro con el id
                del miembro a modificar */
                val currentMembersDb = databaseReference.child(idUser)
                currentMembersDb.child("firstName").setValue(firstName)
                currentMembersDb.child("lastName").setValue(lastName)
                currentMembersDb.child("typeDocument").setValue(documentType)
                currentMembersDb.child("numberDocument").setValue(documentNumber)
                currentMembersDb.child("numberPhone").setValue(numberPhone)

                //OCULTAMOS EL LOADING
                //progressBar.dismiss()
                Toast.makeText(this, R.string.members_update_sucesfull, Toast.LENGTH_SHORT).show()
                super.onBackPressed();
            }else{
                Toast.makeText(this, R.string.register_selection, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, R.string.register_all_fields, Toast.LENGTH_SHORT).show()
        }
    }
}