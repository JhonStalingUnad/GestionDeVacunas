package com.gestion_de_vacunas.Vakunapp.home.miembro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.R

class MiembrosAdapter(options: FirebaseRecyclerOptions<Miembros?>) : FirebaseRecyclerAdapter<Miembros?, MiembrosAdapter.miembrosViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiembrosAdapter.miembrosViewholder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_members, parent, false)
        return miembrosViewholder(view)
    }

    override fun onBindViewHolder(holder: MiembrosAdapter.miembrosViewholder, position: Int, model: Miembros) {

        holder.firstName.setText(model.getFirstname())
        holder.lastName.setText(model.getLastname())
        //holder.dateOfBirth.setText(model.getDateOfBirth())
        //holder.genderUser.setText(model.getGenderUser())
        //holder.documentType.setText(model.getDocumentType())
        //holder.documentNumber.setText(model.getDocumentNumber().toString())
        holder.relationship.setText(model.getRelationship())
        //holder.bloodType.setText(model.getBloodType())
    }

    inner class miembrosViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var firstName: TextView
        lateinit var lastName: TextView
        //lateinit var dateOfBirth: TextView
        //lateinit var genderUser: TextView
        //lateinit var documentType: TextView
        //lateinit var documentNumber: TextView
        lateinit var relationship: TextView
        //lateinit var bloodType: TextView

        init {
            firstName = itemView.findViewById(R.id.tvFirstName)
            lastName = itemView.findViewById(R.id.tvLastName)
            relationship = itemView.findViewById(R.id.tvRelationship)
        }
    }
}