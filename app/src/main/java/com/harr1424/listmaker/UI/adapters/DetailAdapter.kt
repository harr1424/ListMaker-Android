package com.harr1424.listmaker.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harr1424.listmaker.data.Item
import com.harr1424.listmaker.databinding.ListItemBinding


class DetailAdapter(
    private val onItemLongClick: (Item) -> Boolean
) : ListAdapter<Item, DetailAdapter.ViewHolder>(MainAdapter) {

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.itemName == newItem.itemName
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            if (item.detailItems.isNotEmpty()) {
                binding.listItemText.text = item.detailItems[adapterPosition]
            }
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