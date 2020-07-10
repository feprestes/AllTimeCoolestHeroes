package net.vortex.atch.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.vortex.atch.data.Result
import net.vortex.atch.databinding.GridViewItemBinding
import net.vortex.atch.util.EspressoIdlingResource

class PhotoGridAdapter(private val onClickListener: OnClickListener ) : ListAdapter<Result,
        PhotoGridAdapter.CharacterViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.CharacterViewHolder {
        return CharacterViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.CharacterViewHolder, position: Int) {
        EspressoIdlingResource.countingIdlingResource.increment()
        val character = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(character)
        }
        holder.bind(character)
        EspressoIdlingResource.countingIdlingResource.decrement()
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CharacterViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            EspressoIdlingResource.countingIdlingResource.increment()
            binding.character = result
            binding.executePendingBindings()
            EspressoIdlingResource.countingIdlingResource.decrement()
        }
    }

    class OnClickListener(val clickListener: (character: Result) -> Unit) {
        fun onClick(character: Result) = clickListener(character)
    }
}