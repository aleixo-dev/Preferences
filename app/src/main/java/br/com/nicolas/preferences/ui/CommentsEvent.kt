package br.com.nicolas.preferences.ui

sealed class CommentsEvent {

    object OnOpened : CommentsEvent()
    object OnGoHome : CommentsEvent()
    object OnNewComment : CommentsEvent()

}