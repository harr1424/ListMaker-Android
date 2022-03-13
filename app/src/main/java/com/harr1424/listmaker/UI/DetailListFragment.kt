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
            addListItem()
        }
        val recyclerView = binding.detailListView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = DetailAdapter { item ->
            deleteListItem(item)
        }
        viewModel.detailList.observe(this.viewLifecycleOwner) { items ->
            items.let {
                val list: MutableList<Item>? = viewModel.mainList.value
                val arrayList = list?.toList()
                val target = (Item(args.itemName))
                val index = arrayList?.indexOf(target) // indexOf() still throws error
                adapter.submitList(it.elementAt(index!!))
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun addListItem() {
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
                    val newItem = Item(input.text.toString())
                    viewModel.addItemDetailList(Item(args.itemName), newItem)
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

    private fun deleteListItem(item: Item): Boolean {
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setTitle("Delete Item?")
                setPositiveButton(
                    "Yes"
                ) { dialog, id ->
                    viewModel.deleteItemDetailList(Item(args.itemName), item)
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