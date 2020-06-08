package net.vortex.atch.ui.all_heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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

        binding.imageGrid.adapter = PhotoGridAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }
}