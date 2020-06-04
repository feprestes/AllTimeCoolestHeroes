package net.vortex.atch.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Buy me a coffee using Iti :)"
    }
    val text: LiveData<String> = _text
}