package com.gestion_de_vacunas.Vakunapp.home.perfil

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.gestion_de_vacunas.Vakunapp.home.miembro.Miembros
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.properties.Delegates

class EditarFormActivity : AppCompatActivity() {

    private lateinit var accion: String

    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtDocumentType: Spinner
    private lateinit var txtDocumentNumber: EditText
    private lateinit var txtNumberPhone: EditText

    private lateinit var  progressBar: ProgressDialog
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private var firstName by Delegates.notNull<String>()
    private var lastName by Delegates.notNull<String>()
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
        txtDocumentType = findViewById(R.id.spRegTipoDocumento)
        txtDocumentNumber = findViewById(R.id.etNumberDocument)
        txtNumberPhone = findViewById(R.id.etNumberPhone)

        progressBar = ProgressDialog(this)

        //INICIALIZO LA BASE DE DATOS
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        //INICIALIZO LA AUTHENTICACION DE FIREBASE
        auth = FirebaseAuth.getInstance()

        databaseReference = database.reference.child("/Users").child(AppPreferences.uid.toString())

        val intentExtras = intent.extras

        accion = "Edit"

        //idUser = intentExtras.getString("id")
        var firstname = ""
        var lastname = ""
        var type_document = ""
        var number_document = ""
        var phone_number = ""

        //Se instancia la base de datos, pasando la referencia con el que se definió en Firebase.
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error trayendo datos de los miembros", "Error trayendo datos de los miembros")
            }

            //CUANDO SE TRAIGAN LOS DATOS
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children
                Log.d("IN ONDATACHANGE", "INONDATACHANGE")

                //RECORRER CADA KEY DEL REGISTRO
                children.forEach {

                    if (it.key.toString() == "firstName") {
                        firstname = it.value.toString()
                    }
                    if (it.key.toString() == "lastName") {
                        lastname = it.value.toString()
                    }
                    if (it.key.toString() == "numberPhone") {
                        phone_number = it.value.toString()
                    }
                    if (it.key.toString() == "numberDocument") {
                        number_document = it.value.toString()
                    }
                    if (it.key.toString() == "typeDocument") {
                        type_document = it.value.toString()
                    }

                }

                txtName.setText(firstname.toString())
                txtLastName.setText(lastname.toString())

                val spinnerPosition: Int = (txtDocumentType.adapter as ArrayAdapter<String>).getPosition(type_document)
                txtDocumentType.setSelection(spinnerPosition)

                txtDocumentNumber.setText(number_document.toString())
                txtNumberPhone.setText(phone_number.toString())

            }
        })


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
        documentType = txtDocumentType.getSelectedItem().toString()
        documentNumber = txtDocumentNumber.text.toString()
        numberPhone = txtNumberPhone.text.toString()

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(documentNumber) && !TextUtils.isEmpty(numberPhone)){

            if (documentType != "Seleccione el Tipo de Documento") {

                //Muestro un dialogo de Progreso, mientras se modifican los datos de un miembros en la base de datos de Firebase

                /*progressBar.setMessage("Modificando Miembro, Espere por favor ...")
                progressBar.onStart()*/

                /*Obtenemos la referencia d ela base de datos, y enviamos a actualizar el registro con el id
                del miembro a modificar */
                //val currentMembersDb = databaseReference.child(idUser)
                val currentUsersDb = databaseReference
                currentUsersDb.child("firstName").setValue(firstName)
                currentUsersDb.child("lastName").setValue(lastName)
                currentUsersDb.child("typeDocument").setValue(documentType)
                currentUsersDb.child("numberDocument").setValue(documentNumber)
                currentUsersDb.child("numberPhone").setValue(numberPhone)

                //OCULTAMOS EL LOADING
                //progressBar.dismiss()
                Toast.makeText(this, R.string.perfil_update_sucesfull, Toast.LENGTH_SHORT).show()
                super.onBackPressed();
            }else{
                Toast.makeText(this, R.string.register_selection, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, R.string.register_all_fields, Toast.LENGTH_SHORT).show()
        }
    }
}