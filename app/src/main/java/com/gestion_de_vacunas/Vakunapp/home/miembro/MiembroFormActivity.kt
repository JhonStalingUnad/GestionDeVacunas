package com.gestion_de_vacunas.Vakunapp.home.miembro

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.R
import java.util.*

class MiembroFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_miembros)

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