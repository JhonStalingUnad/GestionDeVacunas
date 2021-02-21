package com.gestion_de_vacunas.Vakunapp.home.recordatorio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import android.widget.Toast


class RecordatorioListFragment : Fragment() {


    private var recyclerView: RecyclerView? = null
    var adapter : recordatoriosAdapter? = null
    var query : Query? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recordatorio_list, container, false)

        val buttonRegistrarMiembro: FloatingActionButton = root.findViewById(R.id.buttonCrearRecordatorio);
        buttonRegistrarMiembro.setOnClickListener {
            val intent = Intent(activity, RecordatorioFormActivity::class.java)
            startActivity(intent);
        }

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {

        super.onViewCreated(itemView, savedInstanceState)

        //1 - REALIZAR EL QUERY A FIREBASE A LA TABLA /Users
        query = FirebaseDatabase.getInstance("https://vakunapp-default-rtdb.firebaseio.com/")
                .getReference("/Users")

        //2 - BUSCAR EL RECICLER VIEW Y ASIGNARLE UN LINEARLAYOUT
        recyclerView = itemView.findViewById(R.id.rv_item_list)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        //3 - EJECUTAR EL QUERY EN UNA VARIABLE
        val options = FirebaseRecyclerOptions.Builder<Recordatorios>()
                .setQuery(query!!, Recordatorios::class.java)
                .build()

        //4 - PASAR EL RESULTADO DEL QUERY AL ADAPTER PARA RENDERIZARLO
        adapter = recordatoriosAdapter(options)
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


    private fun deleteRecordatorio(){
        Toast.makeText(activity, "Eliminar recordatorio", Toast.LENGTH_SHORT).show()
    }


    private fun editRecordatorio(){
        Toast.makeText(activity, "Editar recordatorio", Toast.LENGTH_SHORT).show()
    }

    /*********** FUNCIONES DE INVOCACION DIRECTA DESDE LA VISTA ************/
    fun delete(view: View) {
        deleteRecordatorio()
    }

    fun edit(view: View) {
        editRecordatorio()
    }
}