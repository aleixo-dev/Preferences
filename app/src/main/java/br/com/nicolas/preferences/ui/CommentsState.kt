package br.com.nicolas.preferences.ui

import br.com.nicolas.preferences.models.CommentsResponse

sealed class CommentsState {

    data class CommentsData(
        val data : CommentsResponse?
    ) : CommentsState()
}