package br.com.nicolas.preferences.data.repository.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type

class LocalStore(
    private val sharedPreferences: SharedPreferences,
) : LocalStoreDelegate {

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun <T> saveObject(key: String, value: T) {
        saveString(key, Gson().toJson(value))
    }

    override fun <T> getObject(key: String, clazz: Class<T>): T? {
        return Gson().fromJson(getString(key), clazz)
    }

    override fun <T> getObject(key: String, type: Type): T? {
        return Gson().fromJson(getString(key), type)
    }
}