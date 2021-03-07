package com.gestion_de_vacunas.Vakunapp.home.InfoVacunas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.R

class InfoVacunaAdapter(options: FirebaseRecyclerOptions<InfoVacuna?>) : FirebaseRecyclerAdapter<InfoVacuna?, InfoVacunaAdapter.infovacunaViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoVacunaAdapter.infovacunaViewholder {
        val view: View = LayoutInflater.from(parent.context)

                .inflate(R.layout.recycler_view_infovacuna, parent, false)
        return infovacunaViewholder(view)
    }

    override fun onBindViewHolder(holder: InfoVacunaAdapter.infovacunaViewholder, position: Int, model: InfoVacuna) {

        holder.title.setText(model.gettitle())
        holder.description.setText(model.getdescription())
        holder.imagen.setText(model.getimage())

    }

    inner class infovacunaViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView
        var description: TextView
        var imagen: TextView

        init {
            title = itemView.findViewById(R.id.rvtitle)
            description = itemView.findViewById(R.id.rvdescription)
            imagen = itemView.findViewById(R.id.rvimage)

        }
    }
}