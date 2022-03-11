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

class DetailAdapter(private var list: LiveData<MutableMap<String, MutableList<String>>>, mainItem: String) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private val detailItemsList = list.value?.get(mainItem)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnLongClickListener{

        override fun onLongClick(p0: View?): Boolean {
            // Delete DetailList item only
            return true
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.list_item_text.text = detailItemsList?.elementAt(position)
    }

    override fun getItemCount(): Int {
        return detailItemsList?.size ?: 0
    }

}