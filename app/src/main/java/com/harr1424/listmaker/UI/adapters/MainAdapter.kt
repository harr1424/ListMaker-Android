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

class MainAdapter(private var list: LiveData<MutableList<String>>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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
        holder.itemView.list_item_text.text = list.value?.elementAt(position)
    }

    override fun getItemCount(): Int {
        return list.value?.size ?: 0
    }

}