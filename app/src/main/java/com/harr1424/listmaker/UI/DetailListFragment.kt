package com.harr1424.listmaker.UI

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.harr1424.listmaker.ListViewModel
import com.harr1424.listmaker.UI.adapters.DetailAdapter
import com.harr1424.listmaker.data.Item
import com.harr1424.listmaker.databinding.FragmentDetailListBinding

class DetailListFragment : Fragment() {
    private val viewModel: ListViewModel by activityViewModels()
    private var _binding: FragmentDetailListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DetailAdapter
    val args: DetailListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add click listener to floating action button
        binding.fabMain.setOnClickListener {
            addListItem(args.item)
        }
        val recyclerView = binding.detailListView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // lambda to define longClick behavior
        val longClick = { item: String ->
            deleteListItem(item)
        }
        adapter = DetailAdapter(longClick)
        recyclerView.adapter = adapter

        val index = viewModel.list.value?.indexOf(args.item)
        adapter.submitList(viewModel.list.value?.elementAt(index!!)?.detailItems)
    }

    private fun addListItem(mainItem: Item) {
        val input = EditText(activity)
        input.hint = "Item name"
        input.inputType = InputType.TYPE_CLASS_TEXT
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setTitle("Add List Item")
                setView(input)
                setPositiveButton(
                    "Add"
                ) { dialog, id ->
                    val newDetailString = input.text.toString()
                    val targetItemIndex = viewModel.list.value?.indexOf(mainItem)!!
                    val targetItem = viewModel.list.value?.elementAt(targetItemIndex)!!
                    viewModel.addItemDetailList(targetItem, newDetailString)
                    adapter.notifyDataSetChanged()
                }
                setNegativeButton(
                    "Cancel"
                ) { dialog, id ->
                    dialog.cancel()
                }
            }
            builder.create()
            builder.show()
        }
    }

    private fun deleteListItem(item: String): Boolean {
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setTitle("Delete Item?")
                setPositiveButton(
                    "Yes"
                ) { dialog, id ->
                    val index = viewModel.list.value?.indexOf(args.item)
                    if (index != null) {
                        viewModel.list.value?.elementAt(index)
                            ?.let { it1 -> viewModel.deleteItemDetailList(it1, item) }
                    }
                    adapter.notifyDataSetChanged()
                }
                setNegativeButton(
                    "Cancel"
                ) { dialog, id ->
                    dialog.cancel()
                }
            }
            builder.create()
            builder.show()
        }
        return true
    }


}