package net.vortex.atch.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.vortex.atch.data.Result

class DetailViewModel(character: Result, app: Application) : AndroidViewModel(app) {
    private val _selectedCharacter = MutableLiveData<Result>()

    val selectedCharacter: LiveData<Result>
        get() = _selectedCharacter

    init {
        _selectedCharacter.value = character
    }
}