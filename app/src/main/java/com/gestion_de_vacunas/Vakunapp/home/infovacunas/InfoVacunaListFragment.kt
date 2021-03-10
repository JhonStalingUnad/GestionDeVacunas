package com.gestion_de_vacunas.Vakunapp.home.infovacunas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class InfoVacunaListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    var adapter : InfoVacunaAdapter? = null
    var query : Query? = null

    // muestra el frame de la clase
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_infovacunas_list, container, false)

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        //Consulto a la base de datos, en la Tabla donde se registraron la informacion de las vacunas
        query = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
                .getReference("/InfoVacunas")

        //Busco el RecyclerView, y se asigna el LinearLayout
        recyclerView = itemView.findViewById(R.id.rv_item_list_Infovacunas)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        //Ejecuto la query en la variable sentencia
        val sentencia = FirebaseRecyclerOptions.Builder<InfoVacuna>().setQuery(query!!, InfoVacuna::class.java).build()

        //4 - PASAR EL RESULTADO DEL QUERY AL ADAPTER PARA RENDERIZARLO
        adapter = InfoVacunaAdapter(sentencia)
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