package com.gestion_de_vacunas.Vakunapp.home.plan

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.*


class PlanFormActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    var adapter : PlanAsociarAdapter? = null
    var query : Query? = null

    private lateinit var accion: String
    private lateinit var memberId: String

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var txtFirstName: TextView
    private lateinit var txtLastName: TextView
    private lateinit var txtDateOfBirth: TextView
    private lateinit var txtGenderUser: TextView
    private lateinit var txtDocumentType: TextView
    private lateinit var txtDocumentNumber: TextView
    private lateinit var txtRelationship: TextView
    private lateinit var txtBloodType: TextView

    private lateinit var  progressBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_plan)

        txtFirstName = findViewById(R.id.rvFirstName)
        txtLastName = findViewById(R.id.rvLastName)
        txtDateOfBirth = findViewById(R.id.rvDateOfBirth)
        txtGenderUser = findViewById(R.id.rvGenderUser)
        txtDocumentType = findViewById(R.id.rvDocumentType)
        txtDocumentNumber = findViewById(R.id.rvDocumentNumber)
        txtRelationship = findViewById(R.id.rvRelationship)
        txtBloodType = findViewById(R.id.rvBloodType)

        progressBar = ProgressDialog(this)


        //Capturo los parámetros que se pasan por el Intent
        val intentExtras = intent.extras

        accion = "Gestion"
        memberId = intentExtras.getString("id")
        val firstname = intentExtras.getString("firstName")
        val lastname = intentExtras.getString("lastName")
        val dateofbirth = intentExtras.getString("dateOfBirth")
        val genderuser = intentExtras.getString("genderUser")
        val document_type = intentExtras.getString("documentType")
        val document_number = intentExtras.getString("documentNumber")
        val relation_ship = intentExtras.getString("relationship")
        val bloodtype = intentExtras.getString("bloodType")


        txtFirstName.setText(firstname)
        txtLastName.setText(lastname)
        txtDateOfBirth.setText(dateofbirth)
        txtDateOfBirth.setText(dateofbirth)
        txtGenderUser.setText(genderuser)
        txtDocumentType.setText(document_type)
        txtDocumentNumber.setText(document_number)
        txtRelationship.setText(relation_ship)
        txtBloodType.setText(bloodtype)

        database = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
        databaseReference = database.reference.child("/Members").child(AppPreferences.uid.toString())

        val btnAsociarVacuna: Button = findViewById(R.id.btnAsociarVacuna)
        btnAsociarVacuna.setOnClickListener {

            val intent = Intent(this, PlanAsociarFormActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("memberId",  memberId)
            startActivity(intent)

        }




        //1 - REALIZAR EL QUERY A FIREBASE A LA TABLA /Users
        query = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
                .getReference("/Plan")
                .child( AppPreferences.uid.toString() )
                .child( memberId.toString() )

        //2 - BUSCAR EL RECICLER VIEW Y ASIGNARLE UN LINEARLAYOUT
        recyclerView = findViewById(R.id.rv_item_list_vacunas)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        //3 - EJECUTAR EL QUERY EN UNA VARIABLE
        val options = FirebaseRecyclerOptions.Builder<PlanAsociar>()
                .setQuery(query!!, PlanAsociar::class.java)
                .build()

        //4 - PASAR EL RESULTADO DEL QUERY AL ADAPTER PARA RENDERIZARLO
        adapter = PlanAsociarAdapter(options)
        recyclerView!!.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }

}