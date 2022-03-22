package br.com.nicolas.preferences.models

class CommentsResponse : ArrayList<CommentsResponseItem>()

fun CommentsResponse.toComments(comments: ArrayList<CommentsResponseItem>): ArrayList<Comments> {

    val commentsWrapperList = ArrayList<Comments>()

    for (comment in comments) {
        val data = Comments(
            name = comment.name,
            email = comment.email,
            body = comment.body
        )
        commentsWrapperList.add(data)
    }

    return commentsWrapperList
}