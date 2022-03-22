package br.com.nicolas.preferences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nicolas.preferences.databinding.ItemCommentsBinding
import br.com.nicolas.preferences.models.Comments

class CommentsAdapter(
    private val commentsList: List<Comments>
) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comments: Comments) {
            binding.apply {
                textName.text = comments.name
                textEmail.text = comments.email
                textBody.text = comments.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCommentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentsList[position])
    }

    override fun getItemCount() = commentsList.size
}