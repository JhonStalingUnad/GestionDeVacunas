package com.gestion_de_vacunas.Vakunapp.home.plan

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


class PlanAsociarAdapter(options: FirebaseRecyclerOptions<PlanAsociar?>) : FirebaseRecyclerAdapter<PlanAsociar?, PlanAsociarAdapter.recordatoriosViewholder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                           viewType: Int): recordatoriosViewholder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recicler_view_plan_asociar, parent, false)

        return recordatoriosViewholder(view)
    }

    override fun onBindViewHolder(holder: recordatoriosViewholder, position: Int, model: PlanAsociar) {

        val context = holder.btnEdit.context

        holder.vacunaname.setText(model.getVacunaname())
        holder.aplicationdate.setText(model.getAplicationdate())

        holder.btnEdit.setOnClickListener {
            v: View -> Unit

            val intent = Intent(context, PlanAsociarFormActivity::class.java)
            intent.putExtra("id",  model.getId().toString())
            intent.putExtra("memberId",  model.getMember().toString())
            intent.putExtra("vacunaname",  model.getVacunaname().toString())
            intent.putExtra("aplicationdate",  model.getAplicationdate().toString())
            context.startActivity(intent)

        }


        holder.btnDelete.setOnClickListener {
            v: View -> Unit
            Log.i("onItemClick", "onItemClick for ID: " + model.getId().toString())

            val recordatorioActivity = PlanAsociarFormActivity()
            recordatorioActivity.deleteRemember( model.getMember().toString(), model.getId().toString() )

        }

    }

    inner class recordatoriosViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var vacunaname: TextView
        var aplicationdate: TextView
        var btnEdit: TextView
        var btnDelete: TextView

        init {
            vacunaname = itemView.findViewById(R.id.vacunaname)
            aplicationdate = itemView.findViewById(R.id.aplicationdate)
            btnEdit = itemView.findViewById(R.id.btnEdit)
            btnDelete = itemView.findViewById(R.id.btnDelete)
        }

    }

}