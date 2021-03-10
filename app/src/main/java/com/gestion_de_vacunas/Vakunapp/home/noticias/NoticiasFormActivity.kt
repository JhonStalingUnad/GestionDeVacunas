package com.gestion_de_vacunas.Vakunapp.home.noticias


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.recycler_view_noticias.*


class NoticiasFormActivity : AppCompatActivity() {



    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_noticias_list)

        //Se instancia la base de datos, pasando la referencia con el que se definió en Firebase.
        database= FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
        //Se crea la tabla para registrar noticias
        databaseReference = database.reference.child("News")



        //Traemos los datos
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error trayendo datos", "Error trayendo datos")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children

                //Se recorre cada registro, para pintarlo en pantalla en tiempo real
                children.forEach {
                    if( it.key.toString() == "title" ){
                        rvtitle.setText(it.value.toString())
                    }
                }
            }
        })
    }

}