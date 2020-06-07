package net.vortex.atch.ui.all_heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.vortex.atch.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllHeroesViewModel : ViewModel() {

    // Internal MuttableLiveData that stores the most recent response
    private val _response = MutableLiveData<String>()

    // External Immutable LiveData for the response string
    val response: LiveData<String>
        get() = _response

    init {
        getCharacters()
    }

    private fun getCharacters() {
        Api.retrofitService.getData().enqueue(
            object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

            })
    }
}