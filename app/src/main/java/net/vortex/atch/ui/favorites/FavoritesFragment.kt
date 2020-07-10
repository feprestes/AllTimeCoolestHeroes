package net.vortex.atch.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.vortex.atch.R
import net.vortex.atch.databinding.FragmentCharactersBinding
import net.vortex.atch.ui.characters.CharactersFragmentDirections
import net.vortex.atch.ui.characters.CharactersViewModel
import net.vortex.atch.ui.characters.PhotoGridAdapter

class FavoritesFragment : Fragment() {

    private val charactersViewModel by lazy {
        ViewModelProviders.of(this).get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharactersBinding.inflate(inflater)

        binding.viewModel = charactersViewModel

        binding.setLifecycleOwner(this)

        binding.imageGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            charactersViewModel.displayCharacterDetails(it)
        })

        charactersViewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(CharactersFragmentDirections.actionShowDetail(it))
                charactersViewModel.displayCharacterDetailsComplete()
            }
        })

        return binding.root
    }
}