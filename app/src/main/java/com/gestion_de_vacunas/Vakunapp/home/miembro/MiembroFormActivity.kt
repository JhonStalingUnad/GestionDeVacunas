package com.gestion_de_vacunas.Vakunapp.home.miembro

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_recordatorio.*
import kotlinx.android.synthetic.main.activity_registrar_miembros.*
import kotlinx.android.synthetic.main.recycler_view_members.*
import java.util.*
import kotlin.properties.Delegates

class MiembroFormActivity : AppCompatActivity() {

    private lateinit var  progressBar: ProgressDialog

    private var firstName by Delegates.notNull<String>()
    private var lastName by Delegates.notNull<String>()
    private var dateOfBirth by Delegates.notNull<String>()
    private var genderUser by Delegates.notNull<String>()
    private var documentType by Delegates.notNull<String>()
    private var documentNumber by Delegates.notNull<Int>()
    private var relationship by Delegates.notNull<String>()
    private var bloodType by Delegates.notNull<String>()


    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_miembros)

        //Se instancia la base de datos, pasando la referencia con el que se definió en Firebase.
        database= FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
        //Se crea la tabla para registrar los Miembros
        //databaseReference = database.reference.child("Members").child("uEDBvPYSxtMCPwXBX6jNPAGPJms1")
        databaseReference = database.reference.child("Members")

        selectedDate()

        //Traemos los datos
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error trayendo datos", "Error trayendo datos")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children

                //Se recorre cada registro, para pintarlo en pantalla en tiempo real
                children.forEach {
                    if( it.key.toString() == "firstName" ){
                        tvFirstName.setText(it.value.toString())
                    }
                    if( it.key.toString() == "lastName" ){
                        tvLastName.setText(it.value.toString())
                    }
                    if( it.key.toString() == "relationship" ){
                        tvRelationship.setText(it.value.toString())
                    }
                }
            }
        })

        val btnRegistro: Button = findViewById(R.id.btnRegistrar);
        btnRegistro.setOnClickListener {

            //Indicamos la Tabla donde se va a almacenar el registro
            databaseReference = database.reference.child("Members")

            //Guardamos un elemento con el Id del Miembro Quemado para pruebas
            val currentMembersDb = databaseReference.child("1")

            // CAPTURO LOS DATOS DEL FORMULARIO DE REGISTRO
            firstName = tiNombresUsuario.text.toString()
            lastName = tiApellidosUsuario.text.toString()
            dateOfBirth = tiFechaNacimiento.text.toString()
            genderUser = spGenero.toString()
            documentType = spTipoDocumento.toString()
            documentNumber = tiNumeroIdentificacion.text.toString().toInt()
            relationship = spParentesco.toString()
            bloodType =  spGrupoSanguineo.toString()

            currentMembersDb.child("firstName").setValue(firstName)
            currentMembersDb.child("lastName").setValue(lastName)
            currentMembersDb.child("dateOfBirth").setValue(dateOfBirth)
            currentMembersDb.child("genderUser").setValue(genderUser)
            currentMembersDb.child("documentType").setValue(documentType)
            currentMembersDb.child("documentNumber").setValue(documentNumber)
            currentMembersDb.child("relationship").setValue(relationship)
            currentMembersDb.child("bloodType").setValue(bloodType)

            //val miembroSelected: String = spinnerMiembros.getSelectedItem().toString()
            //Log.i("miembroSelected", miembroSelected);
            super.onBackPressed();

        }

    }

    fun selectedDate(){
        //Importo el Calendario para obtener la fecha de nacimiento dentro del DatePicket
        val C = Calendar.getInstance()

        val yearSelected = C.get(Calendar.YEAR)
        val monthSelected = C.get(Calendar.MONTH)
        val daySelected = C.get(Calendar.DAY_OF_MONTH)

        val fechaNacimiento: EditText = findViewById(R.id.tiFechaNacimiento)

        //Oculto el Teclado, para que el usuario no ingrese la fecha manualmente
        fechaNacimiento.setInputType(InputType.TYPE_NULL)

        fechaNacimiento.setOnClickListener {
            //creo la variable para el DatePickDialog
            val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener
            { view, mYear, mMonth, mdayOfMonth -> fechaNacimiento.setText(""+ mdayOfMonth + "/"+ mMonth + "/"+ mYear)}, yearSelected, monthSelected, daySelected)
            datePickerDialog.show()
        }
    }
}