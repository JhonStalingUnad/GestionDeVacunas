package com.gestion_de_vacunas.Vakunapp.ui.miembro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gestion_de_vacunas.Vakunapp.R
import android.widget.Button

class MiembrosFragment : Fragment() {

    private lateinit var miembrosViewModel: MiembrosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        miembrosViewModel =
                ViewModelProvider(this).get(MiembrosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_miembros_list, container, false)

        val buttonRegistrarMiembro: Button = root.findViewById(R.id.buttonRegistrarMiembro);
        buttonRegistrarMiembro.setOnClickListener {
            val intent = Intent(activity, MiembroActivity::class.java)
            startActivity(intent);
        }

        return root
    }

}