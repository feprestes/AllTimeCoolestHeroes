package net.vortex.atch.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.characters_fragment_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    charactersViewModel.getFilteredList(query)
                } else {
                    charactersViewModel.cleanSearch()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    charactersViewModel.getFilteredList(newText)
                }
                return false
            }
        })
    }
}