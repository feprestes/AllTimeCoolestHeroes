package net.vortex.atch.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.vortex.atch.database.characters.Character
import net.vortex.atch.databinding.GridViewItemBinding

class PhotoGridAdapter(private val onClickListener: OnClickListener ) : ListAdapter<Character,
        PhotoGridAdapter.FavoriteViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.FavoriteViewHolder {
        return FavoriteViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.FavoriteViewHolder, position: Int) {
        val character = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(character)
        }
        holder.bind(character)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class FavoriteViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
//            binding.character = character
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (character: Character) -> Unit) {
        fun onClick(character: Character) = clickListener(character)
    }
}