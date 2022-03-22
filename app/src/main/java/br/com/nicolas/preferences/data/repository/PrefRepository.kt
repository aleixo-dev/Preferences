package br.com.nicolas.preferences.data.repository

import br.com.nicolas.preferences.models.Comments
import br.com.nicolas.preferences.models.CommentsResponse
import kotlinx.coroutines.flow.Flow

interface PrefRepository {

    suspend fun getRemote(): Flow<List<Comments>>
    fun saveLocal(comments: List<Comments>)
    fun getLocal(): List<Comments>?
}