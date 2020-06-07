package net.vortex.atch.ui.all_heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.vortex.atch.data.ApiResponse
import net.vortex.atch.data.Result
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
            object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    _response.value = "Success: ${response.body()?.data?.results?.size} results"
                }

            })
    }
}