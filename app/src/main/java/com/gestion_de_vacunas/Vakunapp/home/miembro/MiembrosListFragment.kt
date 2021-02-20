package com.gestion_de_vacunas.Vakunapp.home.miembro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gestion_de_vacunas.Vakunapp.R

class MiembrosListFragment : Fragment() {

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
            val intent = Intent(activity, MiembroFormActivity::class.java)
            startActivity(intent);
        }

        return root
    }


    fun newInstance(someInt: Int): Fragment? {
        val myFragment = Fragment()
        val args = Bundle()
        args.putInt("someInt", someInt)
        myFragment.setArguments(args)
        return myFragment
    }
}