package com.harr1424.listmaker.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harr1424.listmaker.data.Item
import com.harr1424.listmaker.databinding.ListItemBinding


class DetailAdapter(
    private val onItemLongClick: (String) -> Boolean
) : ListAdapter<String, DetailAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    // TODO make binding type RecyclerView
    class ViewHolder(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.listItemText.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnLongClickListener {
            val position = viewHolder.adapterPosition
            onItemLongClick(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}