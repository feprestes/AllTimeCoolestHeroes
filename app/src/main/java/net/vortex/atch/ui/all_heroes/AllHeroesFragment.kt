package net.vortex.atch.ui.all_heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import net.vortex.atch.R

class AllHeroesFragment : Fragment() {

    private lateinit var allHeroesViewModel: AllHeroesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allHeroesViewModel =
                ViewModelProviders.of(this).get(AllHeroesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_heroes, container, false)
        val textView: TextView = root.findViewById(R.id.text_all_heroes)
        allHeroesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}