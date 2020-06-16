package net.vortex.atch.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import net.vortex.atch.R
import net.vortex.atch.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val character = DetailFragmentArgs.fromBundle(requireArguments()).selectedCharacter
        val viewModelFactory = DetailViewModelFactory(character, application)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DetailViewModel::class.java)

        return binding.root
    }
}