package com.gestion_de_vacunas.Vakunapp

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "AComputerEngineer"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val IS_LOGIN = Pair("is_login", false)
    private val UID = Pair("uid", "")
    private val USERNAME = Pair("username", "")
    private val FCMTOKEN = Pair("fcmtoken", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    //SharedPreferences variables getters/setters
    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var uid: String
        get() = preferences.getString(UID.first, UID.second) ?: ""
        set(value) = preferences.edit {
            it.putString(UID.first, value)
        }

    var username: String
        get() = preferences.getString(USERNAME.first, USERNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(USERNAME.first, value)
        }

    var fcmtoken: String
        get() = preferences.getString(FCMTOKEN.first, FCMTOKEN.second) ?: ""
        set(value) = preferences.edit {
            it.putString(FCMTOKEN.first, value)
        }
}