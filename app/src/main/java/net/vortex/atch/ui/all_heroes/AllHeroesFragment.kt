package net.vortex.atch.ui.all_heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import net.vortex.atch.R
import net.vortex.atch.databinding.FragmentAllHeroesBinding
import net.vortex.atch.databinding.GridViewItemBinding

class AllHeroesFragment : Fragment() {

    private val allHeroesViewModel by lazy {
        ViewModelProviders.of(this).get(AllHeroesViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        val binding = FragmentAllHeroesBinding.inflate(inflater)

        val binding = GridViewItemBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = allHeroesViewModel

        setHasOptionsMenu(true)
        return binding.root
    }
}