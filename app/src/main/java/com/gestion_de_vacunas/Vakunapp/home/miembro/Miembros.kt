package com.gestion_de_vacunas.Vakunapp.home.miembro

class Miembros  {

    fun Miembros(){}

    // definimos las variables de la Clase, que se van a utilizar para registrar los Miembros
    private var firstName: String? = null
    private var lastName: String? = null
    private var dateOfBirth: String? = null
    private var genderUser: String? = null
    private var documentType: String? = null
    private var documentNumber: Int? = null
    private var relationship: String? = null
    private var bloodType: String? = null

    // Defino los métodos Get y Set para cada una de las variables de la clase
    fun setFirstname(firstName: String?) {
        this.firstName = firstName
    }

    fun setLastname(lastName: String?) {
        this.lastName = lastName
    }

    fun setDateOfBirth(dateOfBirth: String?) {
        this.dateOfBirth = dateOfBirth
    }

    fun setGenderUser(genderUser: String?) {
        this.genderUser = genderUser
    }

    fun setDocumentType(documentType: String?) {
        this.documentType = documentType
    }

    fun setDocumentNumber(documentNumber: Int?) {
        this.documentNumber = documentNumber
    }

    fun setRelationship(relationship: String?) {
        this.relationship = relationship
    }

    fun setBloodType(bloodType: String?) {
        this.bloodType = bloodType
    }

    fun getFirstname(): String? {
        return firstName
    }

    fun getLastname(): String? {
        return lastName
    }

    fun getDateOfBirth(): String? {
        return dateOfBirth
    }

    fun getGenderUser(): String? {
        return genderUser
    }

    fun getDocumentType(): String? {
        return documentType
    }

    fun getDocumentNumber(): Int? {
        return documentNumber
    }

    fun getRelationship(): String? {
        return relationship
    }

    fun getBloodType(): String? {
        return bloodType
    }

}