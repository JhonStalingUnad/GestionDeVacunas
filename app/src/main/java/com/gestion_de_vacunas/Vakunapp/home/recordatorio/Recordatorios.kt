package com.gestion_de_vacunas.Vakunapp.home.recordatorio

class Recordatorios {

    private var id: String? = null
    private var fullName: String? = null
    private var vacunaName: String? = null
    private var aplicationDate: String? = null

    fun Recordatorios() {}

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getFullname(): String? {
        return fullName
    }

    fun setFullname(fullName: String?) {
        this.fullName = fullName
    }

    fun getVacunaname(): String? {
        return vacunaName
    }

    fun setVacunaname(vacunaName: String?) {
        this.vacunaName = vacunaName
    }

    fun getAplicationdate(): String? {
        return aplicationDate
    }

    fun setAplicationdate(aplicationDate: String?) {
        this.aplicationDate = aplicationDate
    }

}