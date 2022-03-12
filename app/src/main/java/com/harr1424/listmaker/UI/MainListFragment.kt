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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harr1424.listmaker.ListViewModel
import com.harr1424.listmaker.UI.adapters.MainAdapter
import com.harr1424.listmaker.data.Item
import com.harr1424.listmaker.databinding.FragmentMainListBinding
import kotlinx.android.synthetic.main.fragment_main_list.*
import java.lang.reflect.Constructor


class MainListFragment : Fragment() {
    private val viewModel: ListViewModel by activityViewModels()
    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add click listener to floating action button
        binding.fabMain.setOnClickListener {
            addListItem()
        }
        val recyclerView = binding.mainListView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val click =  { item: Item ->
            val action = MainListFragmentDirections.actionMainListFragmentToDetailListFragment(item.itemName)
            findNavController().navigate(action)
        }

        val longClick = {
            item: Item ->
            deleteListItem(item)
        }

        val adapter = MainAdapter(click, longClick)
        recyclerView.adapter = adapter
        viewModel.mainList.observe(this.viewLifecycleOwner) {
            items -> items.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun addListItem() {
        val input = EditText(activity)
        input.setHint("Item name")
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
                    viewModel.addItemMainList(newItem)
                }
                setNegativeButton("Cancel"
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
                    viewModel.deleteItemMainList(item)
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
