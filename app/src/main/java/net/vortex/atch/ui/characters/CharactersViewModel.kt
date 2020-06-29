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

    // Filtered list
    private val _charactersFilter = MutableLiveData<List<Result>>()

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
        getFilteredList("Alpha")
    }

    private fun getCharacters() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING

                var getCharactersData = Api.retrofitService.getData()
                var listResult = getCharactersData

                _charactersFilter.value = listResult.data.results
                var apiCharacterResponseCounter = listResult.data.count

                while (apiCharacterResponseCounter < listResult.data.total) {
                    getCharactersData =
                        Api.retrofitService.getData(offset = _charactersFilter.value!!.size)
                    listResult = getCharactersData

                    _charactersFilter.value = _charactersFilter.value!! + listResult.data.results

                    apiCharacterResponseCounter = _charactersFilter.value!!.size
                }

                _characters.value = _charactersFilter.value
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _characters.value = ArrayList()
                _charactersFilter.value = ArrayList()
            }
        }
    }

    fun getFilteredList(s: String) : Boolean {
        if(s.isNullOrEmpty()){
            cleanSearch()
        } else {
            cleanSearch()
            _characters.value = _charactersFilter.value?.filter { it.name.toLowerCase().contains(s) }
        }
        return true
    }

    fun cleanSearch() {
        _characters.value = _charactersFilter.value
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