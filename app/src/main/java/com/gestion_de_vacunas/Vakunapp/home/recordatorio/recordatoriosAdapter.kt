package com.gestion_de_vacunas.Vakunapp.home.recordatorio

import com.gestion_de_vacunas.Vakunapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class recordatoriosAdapter(options: FirebaseRecyclerOptions<Recordatorios?>) : FirebaseRecyclerAdapter<Recordatorios?, recordatoriosAdapter.recordatoriosViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                           viewType: Int): recordatoriosViewholder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recicler_view_recordatorio, parent, false)

        return recordatoriosViewholder(view)
    }

    override fun onBindViewHolder(holder: recordatoriosViewholder, position: Int, model: Recordatorios) {

        holder.firstname.setText(model.getFirstname())
        holder.lastname.setText(model.getLastname())

    }

    inner class recordatoriosViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstname: TextView
        var lastname: TextView

        init {
            firstname = itemView.findViewById(R.id.firstname)
            lastname = itemView.findViewById(R.id.lastname)
        }
    }

}