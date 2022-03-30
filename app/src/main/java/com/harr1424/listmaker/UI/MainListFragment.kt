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
import com.harr1424.listmaker.BaseApplication
import com.harr1424.listmaker.UI.viewmodels.ListViewModel
import com.harr1424.listmaker.UI.adapters.MainAdapter
import com.harr1424.listmaker.model.MainItem
import com.harr1424.listmaker.databinding.FragmentMainListBinding


class MainListFragment : Fragment() {
    private val viewModel: ListViewModel by activityViewModels {
        ListViewModel.ListViewModelFactory(
            (activity?.application as BaseApplication).database.mainItemDao(),
            (activity?.application as BaseApplication).database.detailItemDao(),
        )
    }
    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add click listener to floating action button
        binding.fabMain.setOnClickListener {
            addListItem()
        }
        val recyclerView = binding.mainListView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // lambda for click behavior
        // navigate to appropriate detail list
        val click = { mainItem: MainItem ->
            val action =
                MainListFragmentDirections.actionMainListFragmentToDetailListFragment(mainItem.id, mainItem.itemName!!)
            findNavController().navigate(action)
        }

        // lambda for long click behavior
        // delete longclicked item
        val longClick = { item: MainItem ->
            deleteListItem(item)
        }

        // MainAdapter takes params onClickListener and onLongClickListener
        val adapter = MainAdapter(click, longClick)
        recyclerView.adapter = adapter
        viewModel.allMainItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
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
                ) { _, _ ->
                    val newItem = MainItem(itemName = input.text.toString())
                    viewModel.addMainItem(newItem)
                }
                setNegativeButton(
                    "Cancel"
                ) { dialog, _ ->
                    dialog.cancel()
                }
            }
            builder.create()
            builder.show()
        }
    }

    private fun deleteListItem(item: MainItem): Boolean {
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setTitle("Delete ${item.itemName}?")
                setPositiveButton(
                    "Yes"
                ) { _, _ ->
                    viewModel.deleteMainItem(item)
                    viewModel.deleteDetailsFromMain(item.id)
                }
                setNegativeButton(
                    "Cancel"
                ) { dialog, _ ->
                    dialog.cancel()
                }
            }
            builder.create()
            builder.show()
        }
        return true
    }

}
