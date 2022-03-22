package br.com.nicolas.preferences.data.service

import br.com.nicolas.preferences.models.CommentsResponse
import retrofit2.http.GET

interface PreferencesService {

    @GET("comments")
    suspend fun getComments() : CommentsResponse

}