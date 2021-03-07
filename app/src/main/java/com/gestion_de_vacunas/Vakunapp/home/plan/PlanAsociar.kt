package com.gestion_de_vacunas.Vakunapp.home.plan

class PlanAsociar {

    private var id: String? = null
    private var member: String? = null
    private var vacunaName: String? = null
    private var aplicationDate: String? = null

    fun Recordatorios() {}

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getMember(): String? {
        return member
    }

    fun setMember(fullName: String?) {
        this.member = fullName
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