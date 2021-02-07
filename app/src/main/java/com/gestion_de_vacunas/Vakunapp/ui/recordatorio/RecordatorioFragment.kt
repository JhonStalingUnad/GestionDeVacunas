package com.gestion_de_vacunas.Vakunapp.ui.recordatorio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gestion_de_vacunas.Vakunapp.R
import android.widget.Button
import com.gestion_de_vacunas.Vakunapp.LoginActivity

class RecordatorioFragment : Fragment() {

    private lateinit var recordatorioViewModel: RecordatorioViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        recordatorioViewModel =
                ViewModelProvider(this).get(RecordatorioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recordatorio_list, container, false)

        /*
        val textView: TextView = root.findViewById(R.id.tituloRecordatorio)
        recordatorioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
         */


        // use arrayadapter and define an array
        /*
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
                "Virat Kohli", "Rohit Sharma", "Steve Smith",
                "Kane Williamson", "Ross Taylor"
        )

        // access the listView from xml file
        var mListView = root.findViewById<ListView>(R.id.list_view_recordatorios)
        arrayAdapter = ArrayAdapter(this,
                R.layout.simple_list_item_1, users)
        mListView.adapter = arrayAdapter
        */

        val buttonCrearRecordatorio: Button = root.findViewById(R.id.buttonCrearRecordatorio);
        buttonCrearRecordatorio.setOnClickListener {
            val intent = Intent(activity, RecordatorioActivity::class.java)
            startActivity(intent);
        }

        return root
    }

}