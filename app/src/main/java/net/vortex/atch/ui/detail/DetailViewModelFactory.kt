package net.vortex.atch.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.vortex.atch.data.Result

class DetailViewModelFactory(
    private val character: Result,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(character, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}