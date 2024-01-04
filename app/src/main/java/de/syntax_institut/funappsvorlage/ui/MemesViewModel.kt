package de.syntax_institut.funappsvorlage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.funappsvorlage.data.AppRepository
import de.syntax_institut.funappsvorlage.data.remote.MemeApi
import kotlinx.coroutines.launch

class MemesViewModel : ViewModel() {
    private val repository = AppRepository(MemeApi)

    val memes = repository.memes

    init {
        loadMemes()
    }

    fun loadMemes() {
        viewModelScope.launch {
            repository.getMemes()
        }
    }

    fun saveTitle(id: Int, title: String) {
        repository.saveTitle(id, title)
    }
}
