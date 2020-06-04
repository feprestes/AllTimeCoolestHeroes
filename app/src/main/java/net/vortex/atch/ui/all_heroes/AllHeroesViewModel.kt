package net.vortex.atch.ui.all_heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllHeroesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is all heroes Fragment"
    }
    val text: LiveData<String> = _text
}