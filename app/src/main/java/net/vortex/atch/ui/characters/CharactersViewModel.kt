package net.vortex.atch.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.vortex.atch.data.Result
import net.vortex.atch.network.Api

enum class ApiStatus { LOADING, ERROR, DONE }

class CharactersViewModel : ViewModel() {

    // Internal MuttableLiveData that stores the most recent response
    private val _characters = MutableLiveData<List<Result>>()
    private val _status = MutableLiveData<ApiStatus>()

    private val _navigateToSelectedCharacter = MutableLiveData<Result>()

    // External Immutable LiveData for the response string
    val characters: LiveData<List<Result>>
        get() = _characters

    val status: LiveData<ApiStatus>
        get() = _status


    val navigateToSelectedCharacter: LiveData<Result>
        get() = _navigateToSelectedCharacter

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                var getCharactersData = Api.retrofitService.getData()
                var listResult = getCharactersData
                _status.value = ApiStatus.DONE
                _characters.value = listResult.data.results
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _characters.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayCharacterDetails(character: Result) {
        _navigateToSelectedCharacter.value = character
    }

    // Marking navigation state as complete
    fun displayCharacterDetailsComplete(){
        _navigateToSelectedCharacter.value = null
    }
}