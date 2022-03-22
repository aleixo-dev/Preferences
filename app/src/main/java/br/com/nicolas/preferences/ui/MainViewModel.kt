package br.com.nicolas.preferences.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.preferences.data.repository.PrefRepository
import br.com.nicolas.preferences.models.Comments
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * Quando tiver internet, salvar os dados no local
 * para garantir que, quando não tiver internet,
 * retornar esses dados da última request feita.
 * sendo assim, evitando exception, e mostrar os dados da última requisição.
 */

class MainViewModel(
    private val localStorage: PrefRepository
) : ViewModel() {

    private val _state = MutableLiveData<List<Comments>>()
    val state: LiveData<List<Comments>> get() = _state

    init {
        getCommentsFromApi()
    }

    private fun getCommentsFromApi() = viewModelScope.launch {
        localStorage.getRemote()
            .onStart {

            }
            .catch {
                getLocal()
            }
            .collect { comments ->
                localStorage.saveLocal(comments)
                _state.value = comments
            }
    }

    private fun getLocal() {
        if (localStorage.getLocal().isNullOrEmpty()) {
            _state.value = arrayListOf(
                Comments(
                    name = "Please, check your connections",
                    email = "Please, check your connections",
                    body = "Please, check your connections"
                )
            )
        } else {
            _state.value = localStorage.getLocal()
        }
    }
}