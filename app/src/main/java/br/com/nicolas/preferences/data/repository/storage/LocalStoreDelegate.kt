package br.com.nicolas.preferences.data.repository.storage

import java.lang.reflect.Type

interface LocalStoreDelegate {

    fun saveString(key: String, value : String)
    fun getString(key: String): String?
    fun <T> saveObject(key: String, value: T)
    fun <T> getObject(key: String, clazz: Class<T>): T?
    fun <T> getObject(key: String, type: Type): T?
}