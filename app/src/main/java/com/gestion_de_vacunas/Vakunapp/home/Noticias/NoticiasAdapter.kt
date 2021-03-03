package com.gestion_de_vacunas.Vakunapp.home.Noticias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.R

class NoticiasAdapter(options: FirebaseRecyclerOptions<Noticias?>) : FirebaseRecyclerAdapter<Noticias?, NoticiasAdapter.noticiasViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasAdapter.noticiasViewholder {
        val view: View = LayoutInflater.from(parent.context)
                //.inflate(R.layout.recycler_view_members, parent, false)
                .inflate(R.layout.recycler_view_noticias, parent, false)
        return noticiasViewholder(view)
    }

    override fun onBindViewHolder(holder: NoticiasAdapter.noticiasViewholder, position: Int, model: Noticias) {

        holder.title.setText(model.gettitle())
        holder.description.setText(model.getdescription())
        holder.imagen.setText(model.getimage())

    }

    inner class noticiasViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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