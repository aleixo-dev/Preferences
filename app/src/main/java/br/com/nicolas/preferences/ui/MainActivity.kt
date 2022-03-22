package br.com.nicolas.preferences.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.nicolas.preferences.CommentsAdapter
import br.com.nicolas.preferences.databinding.ActivityMainBinding
import br.com.nicolas.preferences.models.Comments
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
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.state.observe(this) {
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