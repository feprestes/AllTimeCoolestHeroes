package net.vortex.atch.ui.all_heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.vortex.atch.R
import net.vortex.atch.databinding.FragmentAllHeroesBinding

class AllHeroesFragment : Fragment() {

    private val allHeroesViewModel by lazy {
        ViewModelProviders.of(this).get(AllHeroesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAllHeroesBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = allHeroesViewModel

        binding.imageGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            allHeroesViewModel.displayCharacterDetails(it)
        })

        allHeroesViewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner, Observer {
            if (null != it ){
                this.findNavController().navigate(AllHeroesFragmentDirections.actionShowDetail(it))
                allHeroesViewModel.displayCharacterDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}