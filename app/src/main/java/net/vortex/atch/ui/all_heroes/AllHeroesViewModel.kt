package net.vortex.atch.ui.all_heroes

import android.util.Log
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
    private val _response = MutableLiveData<String>()
    private val _character = MutableLiveData<Result>()
    private val _characterImg = MutableLiveData<String>()

    // External Immutable LiveData for the response string
    val response: LiveData<String>
        get() = _response

    val character: LiveData<Result>
        get() = _character

    val characterImg: LiveData<String>
        get() = _characterImg

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _response.value = "Loading..."
        coroutineScope.launch {
            try {
                var getCharactersData = Api.retrofitService.getData()
                var listResult = getCharactersData
                _response.value = "Success: ${listResult.data.results.size} results"
                if (listResult.data.results.size > 0) {
//                    _character.value = listResult.data.results[0]
                    _characterImg.value = listResult.data.results[0].thumbnail.path
                        .plus(".")
                        .plus(listResult.data.results[0].thumbnail.extension)
                    _response.value = listResult.data.results[0].thumbnail.path
                        .plus(".")
                        .plus(listResult.data.results[0].thumbnail.extension)
                }
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