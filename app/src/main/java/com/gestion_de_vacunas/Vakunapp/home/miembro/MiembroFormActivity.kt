package com.gestion_de_vacunas.Vakunapp.home.miembro

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_form_registrar_miembros.*
import kotlinx.android.synthetic.main.recycler_view_members.*
import java.util.*
import kotlin.properties.Delegates

class MiembroFormActivity : AppCompatActivity() {

    // Con esta variable obtengo el tipo de acción que deseo realizar (Agregar, Editar o Eliminar )
    private lateinit var accion: String
    private lateinit var idMember: String

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var txtFirstName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtDateOfBirth: EditText
    private lateinit var txtGenderUser: Spinner
    private lateinit var txtDocumentType: Spinner
    private lateinit var txtDocumentNumber: EditText
    private lateinit var txtRelationship: Spinner
    private lateinit var txtBloodType: Spinner

    private lateinit var txtTittleForm: TextView
    private lateinit var txtTittleButton: Button

    private var firstName by Delegates.notNull<String>()
    private var lastName by Delegates.notNull<String>()
    private var dateOfBirth by Delegates.notNull<String>()
    private var genderUser by Delegates.notNull<String>()
    private var documentType by Delegates.notNull<String>()
    private var documentNumber by Delegates.notNull<String>()
    private var relationship by Delegates.notNull<String>()
    private var bloodType by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_registrar_miembros)

        txtFirstName = findViewById(R.id.tiNombresUsuario)
        txtLastName = findViewById(R.id.tiApellidosUsuario)
        txtDateOfBirth = findViewById(R.id.tiFechaNacimiento)
        txtGenderUser = findViewById(R.id.spGenero)
        txtDocumentType = findViewById(R.id.spTipoDocumento)
        txtDocumentNumber = findViewById(R.id.tiNumeroIdentificacion)
        txtRelationship = findViewById(R.id.spParentesco)
        txtBloodType = findViewById(R.id.spGrupoSanguineo)

        txtTittleForm = findViewById(R.id.tvRegistroMiembros)
        txtTittleButton = findViewById(R.id.btnRegistrar)

        selectedDate()

        //Capturo los parámetros que se pasan por el Intent
        val intentExtras = intent.extras

        var id = ""
        var firstname = ""
        var lastname = ""
        var dateofbirth = ""
        var genderuser = ""
        var document_type = ""
        var document_number = ""
        var relation_ship = ""
        var bloodtype = ""

        // Validación para Ingresar al Editar o al Agregar Miembros
        if (intentExtras != null) {
            accion = "Edit"
            idMember = intentExtras.getString("id")
            id = intentExtras.getString("id")

            firstname = intentExtras.getString("firstName")
            lastname = intentExtras.getString("lastName")
            dateofbirth = intentExtras.getString("dateOfBirth")
            genderuser = intentExtras.getString("genderUser")
            document_type = intentExtras.getString("documentType")
            document_number = intentExtras.getString("documentNumber")
            relation_ship = intentExtras.getString("relationship")
            bloodtype = intentExtras.getString("bloodType")

        } else {
            accion = "Create"
        }

        //Se instancia la base de datos, pasando la referencia con el que se definió en Firebase.
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        /* Se crea la tabla para registrar los Miembros, y se realiza la validación para traer los datos de los
        miembros del usuario autenticado, y no los de toda la base de datos */
        databaseReference = database.reference.child("/Members").child(AppPreferences.uid.toString())

        if (accion == "Edit") {

            txtTittleForm.setText(R.string.modificar_miembros)
            txtTittleButton.setText(R.string.modificar)
            txtFirstName.setText(firstname)
            txtLastName.setText(lastname)
            txtDateOfBirth.setText(dateofbirth)
            val spinnerPositionGenderUser: Int = (txtGenderUser.adapter as ArrayAdapter<String>).getPosition(genderuser)
            txtGenderUser.setSelection(spinnerPositionGenderUser)
            val spinnerPositionDocumentType: Int = (txtDocumentType.adapter as ArrayAdapter<String>).getPosition(document_type)
            txtDocumentType.setSelection(spinnerPositionDocumentType)
            txtDocumentNumber.setText(document_number)
            val spinnerPositionRelationship: Int = (txtRelationship.adapter as ArrayAdapter<String>).getPosition(relation_ship)
            txtRelationship.setSelection(spinnerPositionRelationship)
            val spinnerPositionBloodType: Int = (txtBloodType.adapter as ArrayAdapter<String>).getPosition(bloodtype)
            txtBloodType.setSelection(spinnerPositionBloodType)
        }

        val btnRegistro: Button = findViewById(R.id.btnRegistrar)
        btnRegistro.setOnClickListener {

            if (accion == "Create") {
                createMembers()
            } else {
                updateMembers()
            }
        }

        val btnCancel: Button = findViewById(R.id.btnCancelar)
        btnCancel.setOnClickListener {
            super.onBackPressed()
        }
    }

    //Método para Registrar los Miembros en la base de datos de Firebase
    private fun createMembers() {

        /* Capturo los datos del formulario de Agregar Miembros donde primero capturo la
        posición de los Spinners */
        val positionGender = spGenero.selectedItemPosition
        val positionDocumentType = spTipoDocumento.selectedItemPosition
        val positionRelationship = spParentesco.selectedItemPosition
        val positionBloodType = spGrupoSanguineo.selectedItemPosition

        firstName = tiNombresUsuario.text.toString()
        lastName = tiApellidosUsuario.text.toString()
        dateOfBirth = tiFechaNacimiento.text.toString()
        genderUser = spGenero.getItemAtPosition(positionGender).toString()
        documentType = spTipoDocumento.getItemAtPosition(positionDocumentType).toString()
        documentNumber = tiNumeroIdentificacion.text.toString()
        relationship = spParentesco.getItemAtPosition(positionRelationship).toString()
        bloodType = spGrupoSanguineo.getItemAtPosition(positionBloodType).toString()

        //Valido que los campos no estén vacíos para el registro
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(dateOfBirth) && !TextUtils.isEmpty(genderUser)
                && !TextUtils.isEmpty(documentType) && !TextUtils.isEmpty(documentNumber)
                && !TextUtils.isEmpty(relationship) && !TextUtils.isEmpty(bloodType)) {

            //Valido que los Spinners no estén vacíos para realizar el registro
            if (genderUser != "Seleccione el Género" && documentType != "Seleccione el Tipo de Documento"
                    && relationship != "Seleccione el Parentesco" && bloodType != "Seleccione el Grupo Sanguíneo") {

                //Muestro un dialogo de Progreso
                /*progressBar.setMessage("Registrando miembro ...")
                        progressBar.show()*/

                //Guardo el elemento con el id publico del usuario
                val currentMembersDb = databaseReference.push()
                currentMembersDb.child("id").setValue(currentMembersDb.getKey())
                currentMembersDb.child("firstName").setValue(firstName)
                currentMembersDb.child("lastName").setValue(lastName)
                currentMembersDb.child("dateOfBirth").setValue(dateOfBirth)
                currentMembersDb.child("genderUser").setValue(genderUser)
                currentMembersDb.child("documentType").setValue(documentType)
                currentMembersDb.child("documentNumber").setValue(documentNumber)
                currentMembersDb.child("relationship").setValue(relationship)
                currentMembersDb.child("bloodType").setValue(bloodType)

                //OCULTAMOS EL LOADING
                //progressDialog.hide()
                //progressBar.hide()
                Toast.makeText(this, R.string.members_sucesfull, Toast.LENGTH_SHORT).show()
                super.onBackPressed()
            } else {
                Toast.makeText(this, R.string.register_selection, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, R.string.register_all_fields, Toast.LENGTH_SHORT).show()
        }
    }

    //Método para Eliminar los usuarios de la base de datos de Firebase
    fun deleteMembers(idMember : String ) {
        //Instanciamos la base de datos
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
        databaseReference = database.reference.child("/Members").child(AppPreferences.uid.toString())

        //Se manda a Eliminar el registro del usuario
        databaseReference.child(idMember).removeValue()

        //Toast.makeText(this, "Usuario Eliminado con Éxito de la aplicación.", Toast.LENGTH_SHORT).show()
    }

    //Método para Modificar los datos de los miembros registrados
    private fun updateMembers(){

        // Aqui capturamos los nuevos datos del formulario
        firstName = txtFirstName.text.toString()
        lastName = txtLastName.text.toString()
        dateOfBirth = txtDateOfBirth.text.toString()
        genderUser = txtGenderUser.getSelectedItem().toString()
        documentType = txtDocumentType.getSelectedItem().toString()
        documentNumber = txtDocumentNumber.text.toString()
        relationship = txtRelationship.getSelectedItem().toString()
        bloodType = txtBloodType.getSelectedItem().toString()

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(dateOfBirth) && !TextUtils.isEmpty(genderUser)
                && !TextUtils.isEmpty(documentType) && !TextUtils.isEmpty(documentNumber)
                && !TextUtils.isEmpty(relationship) && !TextUtils.isEmpty(bloodType)) {

            if (genderUser != "Seleccione el Género" && documentType != "Seleccione el Tipo de Documento"
                    && relationship != "Seleccione el Parentesco" && bloodType != "Seleccione el Grupo Sanguíneo") {

                /*
                progressBar.setMessage("Registrando usuario ...")
                progressBar.show()*/

                /*Obtenemos la referencia d ela base de datos, y enviamos a actualizar el registro con el id
                del miembro a modificar */
                val currentMembersDb = databaseReference.child(idMember)
                currentMembersDb.child("firstName").setValue(firstName)
                currentMembersDb.child("lastName").setValue(lastName)
                currentMembersDb.child("dateOfBirth").setValue(dateOfBirth)
                currentMembersDb.child("genderUser").setValue(genderUser)
                currentMembersDb.child("documentType").setValue(documentType)
                currentMembersDb.child("documentNumber").setValue(documentNumber)
                currentMembersDb.child("relationship").setValue(relationship)
                currentMembersDb.child("bloodType").setValue(bloodType)

                //OCULTAMOS EL LOADING
                //progressBar.hide()
                Toast.makeText(this, R.string.members_update_sucesfull, Toast.LENGTH_SHORT).show()
                super.onBackPressed();
            }else{
                Toast.makeText(this, R.string.register_selection, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, R.string.register_all_fields, Toast.LENGTH_SHORT).show()
        }
    }

    fun selectedDate() {
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
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
            { view, mYear, mMonth, mdayOfMonth -> fechaNacimiento.setText("" + mdayOfMonth + "/" + (mMonth + 1) + "/" + mYear) }, yearSelected, monthSelected, daySelected)
            datePickerDialog.show()

            fechaNacimiento.setInputType(InputType.TYPE_NULL)
        }
    }
}