package br.com.nicolas.preferences.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.preferences.data.repository.PrefRepository
import br.com.nicolas.preferences.models.Comments
import br.com.nicolas.preferences.models.CommentsResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val localStorage: PrefRepository
) : ViewModel() {

    private val _state = MutableLiveData<List<Comments>>()
    val state: LiveData<List<Comments>> get() = _state

    init {
        getCommentsFromApi()
        getLocal()
    }

    private fun getCommentsFromApi() = viewModelScope.launch {
        localStorage.getRemote().collect {
            val data = it
            localStorage.saveLocal(data)
        }
    }

    private fun getLocal() {
        _state.value = localStorage.getLocal()
    }
}