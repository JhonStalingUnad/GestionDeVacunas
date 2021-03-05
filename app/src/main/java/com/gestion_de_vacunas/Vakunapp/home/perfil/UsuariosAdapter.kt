package com.gestion_de_vacunas.Vakunapp.home.perfil

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

class UsuariosAdapter (options: FirebaseRecyclerOptions<Usuarios?>) : FirebaseRecyclerAdapter<Usuarios?, UsuariosAdapter.usuariosViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosAdapter.usuariosViewholder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_perfil, parent, false)
        return usuariosViewholder(view)
    }

    override fun onBindViewHolder(holder: UsuariosAdapter.usuariosViewholder, position: Int, model: Usuarios) {

        holder.firstName.setText(model.getFirstname())
        holder.lastName.setText(model.getLastname())
        holder.email.setText(model.getEmail())
        //holder.password.setText(model.getPassword())
        holder.documentType.setText(model.getDocumentType())
        holder.documentNumber.setText(model.getDocumentNumber())
        holder.numberPhone.setText(model.getDocumentNumber())

        //Implemento la modificación de los datos del perfil del Usuario

        val contexto = holder.btnUpdateUsers.context

        holder.firstName.setText(model.getFirstname())
        holder.lastName.setText(model.getLastname())
        holder.email.setText(model.getEmail())
        //holder.password.setText(model.getPassword())
        holder.documentType.setText(model.getDocumentType())
        holder.documentNumber.setText(model.getDocumentNumber())
        holder.numberPhone.setText(model.getDocumentNumber())

        holder.btnUpdateUsers.setOnClickListener {
            v: View -> Unit

            val intent = Intent(contexto, EditarFormActivity::class.java)
            intent.putExtra("id", model.getId().toString())
            intent.putExtra("firstName", model.getFirstname().toString())
            intent.putExtra("lastName", model.getLastname().toString())
            intent.putExtra("typeDocument", model.getDocumentType().toString())
            intent.putExtra("numberDocument", model.getDocumentNumber().toString())
            intent.putExtra("numberPhone", model.getPhoneNumber().toString())
            contexto.startActivity(intent)
        }
    }

    inner class usuariosViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var firstName: TextView
        var lastName: TextView
        var email: TextView
        //var password: TextView
        var documentType: TextView
        var documentNumber: TextView
        var numberPhone: TextView

        var btnUpdateUsers: Button

        init {
            firstName = itemView.findViewById(R.id.rvFirstNamePerfil)
            lastName = itemView.findViewById(R.id.rvLastNamePerfil)
            email = itemView.findViewById(R.id.rvEmailPerfil)
            //password = itemView.findViewById(R.id.rvPassword)
            documentType = itemView.findViewById(R.id.rvDocumentTypePerfil)
            documentNumber = itemView.findViewById(R.id.rvDocumentNumberPerfil)
            numberPhone = itemView.findViewById(R.id.rvNumberPhonePerfil)

            btnUpdateUsers = itemView.findViewById(R.id.rvBtnEditUserPerfil)
        }
    }
}