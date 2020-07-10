package net.vortex.atch.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.vortex.atch.database.AppDatabase
import net.vortex.atch.database.characters.Character
import net.vortex.atch.database.characters.CharacterRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CharacterRepository

    val allCharacters: LiveData<List<Character>>

    init {
        val characterDao = AppDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        allCharacters = repository.allCharacters
    }

    fun insert(character: Character) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(character)
    }
}