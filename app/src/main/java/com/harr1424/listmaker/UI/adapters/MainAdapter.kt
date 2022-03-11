package com.harr1424.listmaker.UI.adapters

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.harr1424.listmaker.ListViewModel
import com.harr1424.listmaker.R
import kotlinx.android.synthetic.main.list_item.view.*


// TODO implement a ListAdapter with DiffUtil ?
// Change back to Live Data?

class MainAdapter(private var list: LiveData<MutableList<String>>,
        private val listener: ViewHolderListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    // https://stackoverflow.com/questions/57586902/how-to-set-onclicklistener-on-recyclerview-item-in-mvvm-structure

    interface ViewHolderListener {
        fun onClick(view: View?) { // should this really be a biew?

        }
        fun onLongClick(view: View?) {

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.list_item_text.text = list.value?.elementAt(position)
        holder.itemView.setOnClickListener(
            listener.onClick()
        )
    }

    override fun getItemCount(): Int {
        return list.value?.size ?: 0
    }

}