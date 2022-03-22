package br.com.nicolas.preferences.data.repository

import br.com.nicolas.preferences.data.Constants.COMMENTS_KEY
import br.com.nicolas.preferences.data.repository.storage.LocalStoreDelegate
import br.com.nicolas.preferences.data.service.PreferencesService
import br.com.nicolas.preferences.models.Comments
import br.com.nicolas.preferences.models.toComments
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PrefRepositoryImpl(
    private val service: PreferencesService,
    private val local: LocalStoreDelegate
) : PrefRepository {

    override suspend fun getRemote(): Flow<List<Comments>> = flow {
        val response = service.getComments()
        val result = response.toComments(response)
        emit(result)
    }

    override fun saveLocal(comments: List<Comments>) {
        local.saveObject(COMMENTS_KEY, comments)
    }

    override fun getLocal(): List<Comments>? {
        val type = object : TypeToken<List<Comments>>() {}.type
        return local.getObject<List<Comments>>(COMMENTS_KEY, type)
    }
}