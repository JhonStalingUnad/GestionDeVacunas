package com.gestion_de_vacunas.Vakunapp.home.plan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.gestion_de_vacunas.Vakunapp.R


class PlanAdapter(options: FirebaseRecyclerOptions<Plan?>) : FirebaseRecyclerAdapter<Plan?, PlanAdapter.miembrosViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.miembrosViewholder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_plan, parent, false)
        return miembrosViewholder(view)
    }

    override fun onBindViewHolder(holder: PlanAdapter.miembrosViewholder, position: Int, model: Plan) {

        holder.firstName.setText(model.getFirstname())
        holder.lastName.setText(model.getLastname())
        holder.dateOfBirth.setText(model.getDateOfBirth())
        holder.genderUser.setText(model.getGenderUser())
        holder.documentType.setText(model.getDocumentType())
        holder.relationship.setText(model.getRelationship())
        holder.documentNumber.setText(model.getDocumentNumber())
        holder.bloodType.setText(model.getBloodType())


        val contexto = holder.btnUpdateMembers.context

        holder.firstName.setText(model.getFirstname())
        holder.lastName.setText(model.getLastname())
        holder.dateOfBirth.setText(model.getDateOfBirth())
        holder.genderUser.setText(model.getGenderUser())
        holder.documentType.setText(model.getDocumentType())
        holder.relationship.setText(model.getRelationship())
        holder.documentNumber.setText(model.getDocumentNumber())
        holder.bloodType.setText(model.getBloodType())

        holder.btnUpdateMembers.setOnClickListener {
            v: View -> Unit

            val intent = Intent(contexto, PlanFormActivity::class.java)
            intent.putExtra("id", model.getId().toString())
            intent.putExtra("firstName", model.getFirstname().toString())
            intent.putExtra("lastName", model.getLastname().toString())
            intent.putExtra("dateOfBirth", model.getDateOfBirth().toString())
            intent.putExtra("genderUser", model.getGenderUser().toString())
            intent.putExtra("documentType", model.getDocumentType().toString())
            intent.putExtra("documentNumber", model.getDocumentNumber().toString())
            intent.putExtra("relationship", model.getRelationship().toString())
            intent.putExtra("bloodType", model.getBloodType().toString())
            contexto.startActivity(intent)
        }
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

        var btnUpdateMembers: Button

        init {
            firstName = itemView.findViewById(R.id.rvFirstName)
            lastName = itemView.findViewById(R.id.rvLastName)
            dateOfBirth = itemView.findViewById(R.id.rvDateOfBirth)
            genderUser = itemView.findViewById(R.id.rvGenderUser)
            documentType = itemView.findViewById(R.id.rvDocumentType)
            relationship = itemView.findViewById(R.id.rvRelationship)
            documentNumber = itemView.findViewById(R.id.rvDocumentNumber)
            bloodType = itemView.findViewById(R.id.rvBloodType)

            btnUpdateMembers = itemView.findViewById(R.id.rvBtnEditMembers)
        }
    }
}