package net.vortex.atch.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
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

                _characters.value = listResult.data.results
                var apiCharacterResponseCounter = listResult.data.count

                while (apiCharacterResponseCounter < listResult.data.total) {
                    getCharactersData =
                        Api.retrofitService.getData(offset = _characters.value!!.size)
                    listResult = getCharactersData

                    _characters.value = _characters.value!! + listResult.data.results
                    apiCharacterResponseCounter = _characters.value!!.size
                }

                _status.value = ApiStatus.DONE
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
    fun displayCharacterDetailsComplete() {
        _navigateToSelectedCharacter.value = null
    }
}