package com.harr1424.listmaker.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.harr1424.listmaker.R
import kotlinx.android.synthetic.main.list_item.view.*


// TODO implement a ListAdapter with DiffUtil ?
// Change back to Live Data?

class DetailAdapter(private var list: LiveData<MutableMap<String, MutableList<String>>>, val mainItem: String) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener,
        View.OnLongClickListener {
        override fun onClick(p0: View?) {
            // Navigate to corresponding DetailList
        }

        override fun onLongClick(p0: View?): Boolean {
            // Delete MainList item and any contained DetailList items
            return true
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailItemsList = list.value?.get(mainItem)
        holder.itemView.list_item_text.text = detailItemsList?.elementAt(position)
    }

    override fun getItemCount(): Int {
        return list.value?.size ?: 0
    }

}