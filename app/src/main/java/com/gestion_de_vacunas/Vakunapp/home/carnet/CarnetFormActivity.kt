package com.gestion_de_vacunas.Vakunapp.home.carnet

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembroFormActivity
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosAdapter
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosListFragment
import com.google.firebase.database.*
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import com.itextpdf.text.pdf.draw.VerticalPositionMark
import kotlinx.android.synthetic.main.activity_form_carnet.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class CarnetFormActivity : AppCompatActivity() {

    private val STORAGE_CODE: Int = 100


    private lateinit var spinnerMiembros: Spinner
    private var miembrosIdArray = ArrayList<String>()
    private var miembrosNombresArray = ArrayList<String>()

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_carnet)

        btnGenerar.setOnClickListener {

            //Validación de que el SO sea mayor a 6.0 Marshmallow
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, STORAGE_CODE)
                }else{
                    //Llamo a la función que permite Guardar el PDF
                    guardarPDF()
                }
            }else{
                //Llamo a la función que permite Guardar el PDF
                guardarPDF()
            }
        }

        val btnCancel: Button = findViewById(R.id.btnCancelar)
        btnCancel.setOnClickListener {
        }




        //INICIALIZAR EL SPINNER
        spinnerMiembros = findViewById(R.id.spMiembrosCarnet)

        //BASE DE DATOS
        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")


        //TRAER LOS DATOS DE LOS MIEMBROS
        databaseReference = database.reference.child("/Members").child(AppPreferences.uid.toString())
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error trayendo datos de los miembros", "Error trayendo datos de los miembros")
            }

            //CUANDO SE TRAIGAN LOS DATOS
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children

                //RECORRER CADA KEY DEL REGISTRO
                children.forEach {

                    var memberid: String = ""
                    var firstName: String = ""
                    var lastName: String = ""

                    it.children.forEach {

                        Log.d("it.key.toString()", it.key.toString());
                        Log.d("it.value.toString()", it.value.toString());

                        if (it.key.toString() == "id") {
                            memberid = it.value.toString()
                        }
                        if (it.key.toString() == "firstName") {
                            firstName = it.value.toString()
                        }
                        if (it.key.toString() == "lastName") {
                            lastName = it.value.toString()
                        }
                    }

                    if (firstName != "" || lastName != "") {
                        miembrosIdArray.add(memberid)
                        miembrosNombresArray.add(firstName + " " + lastName)
                    }

                }


                val spinnerMiembrosAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this@CarnetFormActivity, android.R.layout.simple_spinner_item, miembrosNombresArray)
                spinnerMiembrosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerMiembros.adapter = spinnerMiembrosAdapter

            }
        })


    }

    private fun guardarPDF() {



        var miembroCarnetSelected = spinnerMiembros.getSelectedItem().toString()
        val spinnerPositionMiembro: Int = (spinnerMiembros.adapter as ArrayAdapter<String>).getPosition(miembroCarnetSelected)
        val miembroIdSelected = miembrosIdArray[spinnerPositionMiembro]

        Log.d("MIEMBRO SELECTED", miembroIdSelected.toString());


        //Instancio la propiedad para acceder al documento mediante la librería itextpdf
        val mDocument = com.itextpdf.text.Document()


        //Obtengo la Fecha y la hora actual del dispositivo para pasarsela al documento PDF
        val fecha_actual = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())

        //Asigno el Nombre del archivo PDF
        val file_name :String = "VakunApp $fecha_actual"

        //Asigno la ruta donde se va a almacenar el documento .PDF
        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + file_name + ".pdf"

        try {
            PdfWriter.getInstance(mDocument, FileOutputStream(mFilePath))

            //Abro el documento para realizar la escritura
            mDocument.open()

            //Agrego las propiedades principales del documento PDF, que no se miran en el documento
            mDocument.pageSize = PageSize.A4
            mDocument.addCreationDate()
            mDocument.addAuthor("VakunApp")
            mDocument.addCreator("Grupo 201495_2 UNAD")

            val color = BaseColor(0,153,204,255)
            val headingFontSize = 20.0f
            //val valueFontSize = 26.0f

            //Creo la Fuente que se verá en el documento, en la carpeta assets
            val fontName = BaseFont.createFont("assets/fonts/brandon_medium.otf","UTF-8", BaseFont.EMBEDDED)

            //Defino el título del estilo, y agrego el título
            val titleStyle = Font(fontName, 36.0f, Font.NORMAL, BaseColor.BLACK)
            addNewItem(mDocument,"CARNET DE VACUNACION", Element.ALIGN_CENTER, titleStyle)

            //Función para colocar una línea separadora
            addLineSeparator(mDocument)



            var headingStyle: Font
            var valuesStyle: Font


            databaseReference = database.reference.child("/Members").child( AppPreferences.uid ).child( miembroIdSelected )
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Error trayendo datos de los miembros", "Error trayendo datos de los miembros")
                }

                //CUANDO SE TRAIGAN LOS DATOS
                override fun onDataChange(snapshot: DataSnapshot) {
                    val children = snapshot!!.children
                    Log.d("IN ONDATACHANGE", "INONDATACHANGE")

                    var firstname = ""
                    var lastname = ""
                    var type_document = ""
                    var number_document = ""
                    var phone_number = ""

                    //RECORRER CADA KEY DEL REGISTRO
                    children.forEach {

                        if (it.key.toString() == "firstName") {
                            firstname = it.value.toString()
                        }
                        if (it.key.toString() == "lastName") {
                            lastname = it.value.toString()
                        }
                        if (it.key.toString() == "documentNumber") {
                            number_document = it.value.toString()
                        }
                        if (it.key.toString() == "documentType") {
                            type_document = it.value.toString()
                        }
                        if (it.key.toString() == "numberPhone") {
                            phone_number = it.value.toString()
                        }

                    }

                    headingStyle = Font(fontName, headingFontSize, Font.NORMAL, color)
                    addNewItem(mDocument, "Nombre del Usuario:", Element.ALIGN_LEFT,headingStyle)
                    valuesStyle = Font(fontName, headingFontSize, Font.NORMAL, BaseColor.BLACK)
                    addNewItem(mDocument, firstname + " " + lastname, Element.ALIGN_LEFT,valuesStyle)
                    addLineSeparator(mDocument)


                    headingStyle = Font(fontName, headingFontSize, Font.NORMAL, color)
                    addNewItem(mDocument, "Identificación:", Element.ALIGN_LEFT,headingStyle)
                    valuesStyle = Font(fontName, headingFontSize, Font.NORMAL, BaseColor.BLACK)
                    addNewItem(mDocument, type_document + ": " + number_document, Element.ALIGN_LEFT,valuesStyle)
                    addLineSeparator(mDocument)
                    addNewItem(mDocument,  "                ", Element.ALIGN_LEFT, valuesStyle)
                    addNewItem(mDocument,  "                ", Element.ALIGN_LEFT, valuesStyle)
                    addNewItem(mDocument,  "                ", Element.ALIGN_LEFT, valuesStyle)





                    databaseReference = database.reference.child("/Plan").child( AppPreferences.uid ).child( miembroIdSelected )
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

                                var aplicationDate = ""
                                var vacunaName = ""

                                it.children.forEach {

                                    if (it.key.toString() == "aplicationDate") {
                                        aplicationDate = it.value.toString()
                                    }
                                    if (it.key.toString() == "vacunaName") {
                                        vacunaName = it.value.toString()
                                    }

                                }

                                addNewItem(mDocument, "Nombre de la Vacuna aplicada:", Element.ALIGN_LEFT,headingStyle)
                                addNewItem(mDocument, vacunaName, Element.ALIGN_LEFT,valuesStyle)
                                addLineSeparator(mDocument)

                                addNewItem(mDocument, "Fecha de aplicación e la vacuna:", Element.ALIGN_LEFT,headingStyle)
                                addNewItem(mDocument, aplicationDate, Element.ALIGN_LEFT,valuesStyle)
                                addLineSeparator(mDocument)
                                addNewItem(mDocument,  "                ", Element.ALIGN_LEFT, valuesStyle)
                                addNewItem(mDocument,  "                ", Element.ALIGN_LEFT, valuesStyle)
                                addNewItem(mDocument,  "                ", Element.ALIGN_LEFT, valuesStyle)

                            }



                            //Por último cierro el documento
                            mDocument.close()

                            //Muestro Toast de Archivo Generado
                            Toast.makeText(this@CarnetFormActivity,"Archivo PDF Generado satisfactoriamente en el almacenamiento interno del equipo", Toast.LENGTH_SHORT).show()

                        }
                    })

                }
            })

        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(DocumentException::class)
    private fun addLineSeparator(mDocument: Document) {
        val lineSeparator = LineSeparator()
        lineSeparator.lineColor = BaseColor(0,0,0,68)
        addLineSpace(mDocument)
        mDocument.add(Chunk(lineSeparator))
        addLineSpace(mDocument)
    }

    @Throws(DocumentException::class)
    private fun addLineSpace(mDocument: Document) {
        mDocument.add(Paragraph(""))
    }

    @Throws(DocumentException::class)
    private fun addNewItem(mDocument: Document, text: String, align: Int, style: Font) {
        val chunk = Chunk(text,style)
        val p = Paragraph(chunk)
        p.alignment = align
        mDocument.add(p)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            STORAGE_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    guardarPDF()
                }
                else{
                    Toast.makeText(this,"Permiso Denegado...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


