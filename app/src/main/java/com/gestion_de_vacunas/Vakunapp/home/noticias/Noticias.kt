package com.gestion_de_vacunas.Vakunapp.home.noticias


class Noticias  {

    fun Noticias(){}

    // definimos las variables de la Clase, que se van a utilizar para registrar las noticias
    private var title: String? = null
    private var description: String? = null


    // Defino los métodos Get y Set para cada una de las variables de la clase
    fun settitle(title: String?) {
        this.title = title
    }

    fun setdescription(description: String?) {
        this.description = description
    }

    fun gettitle(): String? {
        return title
    }

    fun getdescription(): String? {
        return description
    }


}