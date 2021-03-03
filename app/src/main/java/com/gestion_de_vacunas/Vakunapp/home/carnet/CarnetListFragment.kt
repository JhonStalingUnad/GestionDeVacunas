package com.gestion_de_vacunas.Vakunapp.home.carnet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gestion_de_vacunas.Vakunapp.R

class CarnetListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //val root = inflater.inflate(R.layout.fragment_miembros_list, container, false)
        val intent = Intent(activity, CarnetFormActivity::class.java)
        startActivity(intent);
        return null
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        //adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        //adapter?.stopListening()
    }

}