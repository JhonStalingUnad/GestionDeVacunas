package com.gestion_de_vacunas.Vakunapp.home.perfil

class Usuarios {

    // definimos las variables de la Clase, que se van a utilizar para registrar los Usuarios
    private var id: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var password: String? = null
    private var documentType: String? = null
    private var documentNumber: String? = null
    private var phoneNumber: String? = null

    fun Usuarios(){

    }

    // Defino los métodos Get y Set para cada una de las variables de la clase
    fun setId(id: String?) {
        this.id = id
    }

    fun setFirstname(firstName: String?) {
        this.firstName = firstName
    }

    fun setLastname(lastName: String?) {
        this.lastName = lastName
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setDocumentType(documentType: String?) {
        this.documentType = documentType
    }

    fun setDocumentNumber(documentNumber: String?) {
        this.documentNumber = documentNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }

    fun getId(): String? {
        return id
    }

    fun getFirstname(): String? {
        return firstName
    }

    fun getLastname(): String? {
        return lastName
    }

    fun getEmail(): String? {
        return email
    }

    fun getPassword(): String? {
        return password
    }

    fun getDocumentType(): String? {
        return documentType
    }

    fun getDocumentNumber(): String? {
        return documentNumber
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

}