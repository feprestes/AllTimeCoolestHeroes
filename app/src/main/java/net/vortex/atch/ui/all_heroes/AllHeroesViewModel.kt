package net.vortex.atch.ui.all_heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.vortex.atch.data.Result
import net.vortex.atch.network.Api

class AllHeroesViewModel : ViewModel() {

    // Internal MuttableLiveData that stores the most recent response
    private val _characters = MutableLiveData<List<Result>>()
    private val _response = MutableLiveData<String>()

    // External Immutable LiveData for the response string
    val characters: LiveData<List<Result>>
        get() = _characters

    val response: LiveData<String>
        get() = _response


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        coroutineScope.launch {
            try {
                var getCharactersData = Api.retrofitService.getData()
                var listResult = getCharactersData
                _response.value = "Success: ${listResult.data.results.size} results"
                _characters.value = listResult.data.results
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}