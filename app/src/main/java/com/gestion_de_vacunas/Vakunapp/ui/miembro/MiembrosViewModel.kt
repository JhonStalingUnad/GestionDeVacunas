package com.gestion_de_vacunas.Vakunapp.ui.miembro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MiembrosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Recordatorio Fragment"
    }
    val text: LiveData<String> = _text
}