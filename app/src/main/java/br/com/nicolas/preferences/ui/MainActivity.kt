package br.com.nicolas.preferences.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import br.com.nicolas.preferences.CommentsAdapter
import br.com.nicolas.preferences.R
import br.com.nicolas.preferences.databinding.ActivityMainBinding
import br.com.nicolas.preferences.models.Comments
import br.com.nicolas.preferences.models.CommentsResponse
import kotlinx.coroutines.launch
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state.observe(activityScope().lifecycleOwner) {
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(comments: List<Comments>) {
        with(binding.recyclerViewCommentsResponse) {
            adapter = CommentsAdapter(comments)
            setHasFixedSize(true)
        }
    }
}