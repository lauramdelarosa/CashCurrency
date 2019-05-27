package com.delarosa.cashcurrency.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesAdapter(internal var mContext: Context) {
    private var preferences: SharedPreferences = mContext.getSharedPreferences("configuracion", Context.MODE_PRIVATE)
    private lateinit var editor: SharedPreferences.Editor

    fun getPreferenceString(key: String): String? {
        return preferences.getString(key, "")

    }

    fun getPreferenceInt(key: String): Int {
        return preferences.getInt(key, 0)

    }

    fun getPreferenceBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)

    }


    fun setPreferenceString(key: String, valor: String) {
        editor = preferences.edit()
        editor.putString(key, valor)
        editor.commit()

    }

    fun setPreferenceInt(key: String, valor: Int) {
        editor = preferences.edit()
        editor.putInt(key, valor)
        editor.commit()

    }

    fun setPreferenceBoolean(key: String, valor: Boolean) {
        editor = preferences.edit()
        editor.putBoolean(key, valor)
        editor.commit()

    }


    companion object {
        var AVAILABLE: String = "AVAILABLE"
        var LIST: String = "LIST"
        var CONFIG_FIRST_TIME: String = "CONFIG_FIRST_TIME"
    }

}
