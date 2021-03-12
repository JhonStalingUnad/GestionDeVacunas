package com.gestion_de_vacunas.Vakunapp.home.miembro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class MiembrosListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    var adapter : MiembrosAdapter? = null
    var query : Query? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_miembros_list, container, false)
        val buttonRegistrarMiembro: FloatingActionButton = root.findViewById(R.id.buttonRegistrarMiembros);
        buttonRegistrarMiembro.setOnClickListener {
            val intent = Intent(activity, MiembroFormActivity::class.java)
            startActivity(intent);
        }
        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        //Consulto a la base de datos, en la Tabla donde se registraron los miembros
        query = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
                .getReference("/Members").child("/" + AppPreferences.uid.toString())

        //Busco el RecyclerView, y se asigna el LinearLayout
        recyclerView = itemView.findViewById(R.id.rv_item_list_members)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        //Ejecuto la query en la variable sentencia
        val sentencia = FirebaseRecyclerOptions.Builder<Miembros>().setQuery(query!!, Miembros::class.java).build()

        //Renderizamos el valor de la query en el adaptador
        adapter = MiembrosAdapter(sentencia)
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