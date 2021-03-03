package com.gestion_de_vacunas.Vakunapp.home.carnet

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import com.gestion_de_vacunas.Vakunapp.R
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembroFormActivity
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosAdapter
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosListFragment
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
    }

    private fun guardarPDF() {



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

            //Registro datos hardcodeados para las pruebas
            val headingStyle = Font(fontName, headingFontSize, Font.NORMAL, color)
            addNewItem(mDocument, "Nombre del Usuario:", Element.ALIGN_LEFT,headingStyle)
            val valuesStyle = Font(fontName, headingFontSize, Font.NORMAL, BaseColor.BLACK)
            addNewItem(mDocument, "Jhon Staling Sevillano Zemanate", Element.ALIGN_LEFT,valuesStyle)

            addLineSeparator(mDocument)

            addNewItem(mDocument, "Nombre de la Vacuna aplicada:", Element.ALIGN_LEFT,headingStyle)
            addNewItem(mDocument, "COVID 19 VACCINE AZTRAZENECA", Element.ALIGN_LEFT,valuesStyle)

            addLineSeparator(mDocument)

            addNewItem(mDocument, "Fecha de aplicación e la vacuna:", Element.ALIGN_LEFT,headingStyle)
            addNewItem(mDocument, "02/03/2021", Element.ALIGN_LEFT,valuesStyle)

            addLineSeparator(mDocument)

            addLineSpace(mDocument)
            addNewItem(mDocument,"OBSERVACION GENERAL DE LA VACUNA", Element.ALIGN_CENTER, titleStyle)

            addLineSeparator(mDocument)

            addNewItem(mDocument, "Se aplica la primera Dosis el día 3 de Marzo del 2021, deberá regresar el 18 de Marzo del 2021 para aplicar la segunda dosis", Element.ALIGN_LEFT,valuesStyle)

            //Por último cierro el documento
            mDocument.close()

            //Muestro Toast de Archivo Generado
            Toast.makeText(this,"Archivo PDF Generado satisfactoriamente en el almacenamiento interno del equipo", Toast.LENGTH_SHORT).show()

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


