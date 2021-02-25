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
                //.inflate(R.layout.recycler_view_members, parent, false)
                .inflate(R.layout.recycler_view_members, parent, false)
        return miembrosViewholder(view)
    }

    override fun onBindViewHolder(holder: MiembrosAdapter.miembrosViewholder, position: Int, model: Miembros) {

        holder.firstName.setText(model.getFirstname())
        holder.lastName.setText(model.getLastname())
        holder.dateOfBirth.setText(model.getDateOfBirth())
        holder.genderUser.setText(model.getGenderUser())
        holder.documentType.setText(model.getDocumentType())
        holder.relationship.setText(model.getRelationship())
        holder.documentNumber.setText(model.getDocumentNumber())
        holder.bloodType.setText(model.getBloodType())
    }

    inner class miembrosViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var firstName: TextView
        var lastName: TextView
        var dateOfBirth: TextView
        var genderUser: TextView
        var documentType: TextView
        var relationship: TextView
        var documentNumber: TextView
        var bloodType: TextView

        init {
            firstName = itemView.findViewById(R.id.rvFirstName)
            lastName = itemView.findViewById(R.id.rvLastName)
            dateOfBirth = itemView.findViewById(R.id.rvDateOfBirth)
            genderUser = itemView.findViewById(R.id.rvGenderUser)
            documentType = itemView.findViewById(R.id.rvDocumentType)
            relationship = itemView.findViewById(R.id.rvRelationship)
            documentNumber = itemView.findViewById(R.id.rvDocumentNumber)
            bloodType = itemView.findViewById(R.id.rvBloodType)
        }
    }
}