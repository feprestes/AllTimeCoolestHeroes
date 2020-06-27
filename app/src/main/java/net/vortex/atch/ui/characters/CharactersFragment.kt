package net.vortex.atch.ui.characters

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.vortex.atch.R
import net.vortex.atch.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment() {

    private val charactersViewModel by lazy {
        ViewModelProviders.of(this).get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharactersBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = charactersViewModel

        binding.imageGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            charactersViewModel.displayCharacterDetails(it)
        })

        charactersViewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(CharactersFragmentDirections.actionShowDetail(it))
                charactersViewModel.displayCharacterDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.characters_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}