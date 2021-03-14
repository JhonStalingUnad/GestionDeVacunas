package com.gestion_de_vacunas.Vakunapp.home.plan

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_form_recordatorio.*
import java.util.*
import kotlin.properties.Delegates


class PlanAsociarFormActivity : AppCompatActivity() {

    private lateinit var  progressBar: ProgressDialog

    private lateinit var modo: String
    private lateinit var planId: String
    private lateinit var memberId: String

    private lateinit var txtVacuna: Spinner
    private lateinit var txtFecha: EditText

    private var miembroName by Delegates.notNull<String>()
    private var vacunaName by Delegates.notNull<String>()
    private var fechaAplicacion by Delegates.notNull<String>()

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_plan_asociar)

        txtVacuna = findViewById(R.id.spVacuna)
        txtFecha = findViewById(R.id.dtFechaAplicacion)
        progressBar = ProgressDialog(this)

        //EVENTO DE CLICK EN EL TEXTVIEW DE CALENDAR
        selectedDate()

        //CAPTURAR LOS PARAMETROS QUE SE LE PASAN AL INTENT
        val intentExtras = intent.extras
        memberId = intentExtras.getString("memberId")

        //INICIALIZAR LA VARIABLE Y PASARLE LOS DATOS A LAS VARIABLES SI SI VIENEN EN EL INTENT
        var vacunaname = ""
        var aplicationdate = ""
        if (intentExtras.getString("id") != null) {
            modo = "edit"
            planId = intentExtras.getString("id")
            vacunaname = intentExtras.getString("vacunaname")
            aplicationdate = intentExtras.getString("aplicationdate")
        } else {
            modo = "create"
        }
        Log.i("-----------------MODO FORM-----------------", modo);


        //BASE DE DATOS
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")

        //TRAER LOS DATOS DE LAS VACUNAS
        var vacunasArray = ArrayList<String>()

        databaseReference = database.reference.child("/Vacuna")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error trayendo datos de las vacunas", "Error trayendo datos de las vacunas")
            }

            //CUANDO SE TRAIGAN LOS DATOS
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children

                //RECORRER CADA KEY DEL REGISTRO
                children.forEach {

                    var vacunaName: String = ""

                    it.children.forEach {

                        Log.d("it.key.toString()", it.key.toString());
                        Log.d("it.value.toString()", it.value.toString());

                        if (it.key.toString() == "name") {
                            vacunaName = it.value.toString()
                        }
                    }

                    if ( vacunaName != "" ) {
                        vacunasArray.add( vacunaName )
                    }

                }

                val spinnerVacunas = findViewById<View>(R.id.spVacuna) as Spinner
                val spinnerVacunasAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this@PlanAsociarFormActivity, android.R.layout.simple_spinner_item, vacunasArray)
                spinnerVacunasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerVacunas.adapter = spinnerVacunasAdapter

                //SETEAR VALORES POR DEFECTO EN MODO EDICION
                if(modo == "edit"){
                    val spinnerPositionVacunas: Int = (spinnerVacunas.adapter as ArrayAdapter<String>).getPosition(vacunaname)
                    spinnerVacunas.setSelection(spinnerPositionVacunas)
                }

            }
        })

        //DEJO LA REFERENCIA EN LA TABLE REMEMBERS (PARA PROXIMAS CONSULTAS)
        databaseReference = database.reference.child("/Plan")
                .child( AppPreferences.uid.toString() )
                .child( memberId.toString() )



        //SETEAR VALORES POR DEFECTO EN MODO EDICION
        if(modo == "edit"){
            txtFecha.setText(aplicationdate)
        }

        //METODO DEL BUTTON GUARDAR
        val btnSave: Button = findViewById(R.id.btnSave);
        btnSave.setOnClickListener {

            if(modo == "create"){
                createRemember()
            }else{
                updateRemember()
            }

        }

    }

    //FUNCION EVENTO CLICK CALENDAR
    private fun selectedDate(){

        val C = Calendar.getInstance()
        val yearSelected = C.get(Calendar.YEAR)
        val monthSelected = C.get(Calendar.MONTH)
        val daySelected = C.get(Calendar.DAY_OF_MONTH)

        val fechaAplicacion: EditText = findViewById(R.id.dtFechaAplicacion)
        fechaAplicacion.setInputType(InputType.TYPE_NULL)

        fechaAplicacion.setOnClickListener {
            fechaAplicacion.requestFocus()
            fechaAplicacion.setInputType(InputType.TYPE_NULL)

            val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.getWindowToken(), 0)

            val datePickerDialog = DatePickerDialog(this, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert ,DatePickerDialog.OnDateSetListener
            { view, mYear, mMonth, mdayOfMonth -> fechaAplicacion.setText("" + mdayOfMonth + "/" + (mMonth + 1) + "/" + mYear) }, yearSelected, monthSelected, daySelected)
            datePickerDialog.show()
        }

    }

    //METODO PARA CREAR UN NUEVO RECORDATORIO
    private fun createRemember() {

        //CAPTURAR DATOS DEL FORMULARIO
        vacunaName = txtVacuna.getSelectedItem().toString()
        fechaAplicacion = txtFecha.text.toString()

        //Valido que los campos no estén vacíos para el registro
        if (!TextUtils.isEmpty(vacunaName) && !TextUtils.isEmpty(fechaAplicacion)) {

            //MOSTRAR LOADING
            progressBar.setMessage("Registrando recordatorio ...")
            progressBar.show()

            //GUARDAMOS UN NUEVO ELEMENTO CON EL ID DEL USUARIO
            val currentRememberDb = databaseReference.push()
            currentRememberDb.child("member").setValue(memberId)
            currentRememberDb.child("id").setValue(currentRememberDb.getKey())
            currentRememberDb.child("vacunaName").setValue(vacunaName)
            currentRememberDb.child("aplicationDate").setValue(fechaAplicacion)

            //OCULTAMOS EL LOADING
            progressBar.hide()
            Toast.makeText(this, "Recordatorio creado con éxito.", Toast.LENGTH_SHORT).show()
            super.onBackPressed();
        }else{
            Toast.makeText(this, R.string.register_all_fields, Toast.LENGTH_SHORT).show()
        }

    }


    //METODO PARA CREAR UN NUEVO RECORDATORIO
    private fun updateRemember() {

        //CAPTURAR DATOS DEL FORMULARIO
        vacunaName = txtVacuna.getSelectedItem().toString()
        fechaAplicacion = txtFecha.text.toString()

        if (!TextUtils.isEmpty(vacunaName) && !TextUtils.isEmpty(fechaAplicacion)) {

            //MOSTRAR LOADING
            progressBar.setMessage("Registrando usuario ...")
            progressBar.show()

            //GUARDAMOS UN NUEVO ELEMENTO CON EL ID DEL USUARIO
            val currentRememberDb = databaseReference.child(planId)
            currentRememberDb.child("member").setValue(memberId)
            currentRememberDb.child("vacunaName").setValue(vacunaName)
            currentRememberDb.child("aplicationDate").setValue(fechaAplicacion)

            //OCULTAMOS EL LOADING
            progressBar.hide()
            Toast.makeText(this, "Recordatorio actualizado con exito.", Toast.LENGTH_SHORT).show()
            super.onBackPressed();
        }else{
            Toast.makeText(this, R.string.register_all_fields, Toast.LENGTH_SHORT).show()
        }

    }

    //METODO PARA CREAR UN NUEVO RECORDATORIO
    fun deleteRemember(idMember: String, idPlan: String) {

        Log.d("------deletePlan-------", idPlan);

        //BASE DE DATOS
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
        databaseReference = database.reference.child("/Plan").child(AppPreferences.uid.toString())
        databaseReference.child(idMember).child(idPlan).removeValue()

    }

}