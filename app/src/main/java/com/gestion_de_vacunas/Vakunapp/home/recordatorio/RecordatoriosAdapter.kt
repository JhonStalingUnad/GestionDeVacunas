package com.gestion_de_vacunas.Vakunapp.home.recordatorio

import android.content.Intent
import android.util.Log
import com.gestion_de_vacunas.Vakunapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class RecordatoriosAdapter(options: FirebaseRecyclerOptions<Recordatorios?>) : FirebaseRecyclerAdapter<Recordatorios?, RecordatoriosAdapter.recordatoriosViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                           viewType: Int): recordatoriosViewholder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recicler_view_recordatorio, parent, false)

        return recordatoriosViewholder(view)
    }

    override fun onBindViewHolder(holder: recordatoriosViewholder, position: Int, model: Recordatorios) {

        val context = holder.btnEdit.context

        holder.fullname.setText(model.getFullname())
        holder.vacunaname.setText(model.getVacunaname())
        holder.aplicationdate.setText(model.getAplicationdate())

        holder.btnEdit.setOnClickListener {
            v: View -> Unit

            val intent = Intent(context, RecordatorioFormActivity::class.java)
            intent.putExtra("id",  model.getId().toString())
            intent.putExtra("fullname",  model.getFullname().toString())
            intent.putExtra("vacunaname",  model.getVacunaname().toString())
            intent.putExtra("aplicationdate",  model.getAplicationdate().toString())
            context.startActivity(intent)

        }


        holder.btnDelete.setOnClickListener {
            v: View -> Unit
            Log.i("onItemClick", "onItemClick for ID: " + model.getId().toString())

            val recordatorioActivity = RecordatorioFormActivity()
            recordatorioActivity.deleteRemember( model.getId().toString() )

        }

    }

    inner class recordatoriosViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var fullname: TextView
        var vacunaname: TextView
        var aplicationdate: TextView
        var btnEdit: TextView
        var btnDelete: TextView

        init {
            fullname = itemView.findViewById(R.id.fullname)
            vacunaname = itemView.findViewById(R.id.vacunaname)
            aplicationdate = itemView.findViewById(R.id.aplicationdate)
            btnEdit = itemView.findViewById(R.id.btnEdit)
            btnDelete = itemView.findViewById(R.id.btnDelete)
        }

    }

}