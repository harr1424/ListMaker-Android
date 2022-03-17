package com.harr1424.listmaker.UI

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.harr1424.listmaker.BaseApplication
import com.harr1424.listmaker.ListViewModel
import com.harr1424.listmaker.UI.adapters.DetailAdapter
import com.harr1424.listmaker.data.DetailItem
import com.harr1424.listmaker.databinding.FragmentDetailListBinding

class DetailListFragment : Fragment() {
    private val viewModel: ListViewModel by activityViewModels() {
        ListViewModel.ListViewModelFactory(
            (activity?.application as BaseApplication).database.mainItemDao(),
            (activity?.application as BaseApplication).database.detailItemDao(),
            )
    }
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
            addListItem()
        }
        val recyclerView = binding.detailListView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // lambda to define longClick behavior
        val longClick = { detailItem: DetailItem ->
            deleteListItem(detailItem)
        }
        adapter = DetailAdapter(longClick)
        recyclerView.adapter = adapter
        viewModel.getDetailItems(args.mainItemId).observe(this.viewLifecycleOwner) { items ->
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
                ) { dialog, id ->
                    viewModel.addDetailItem(args.mainItemId, input.text.toString())
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

    private fun deleteListItem(detailItem: DetailItem): Boolean {
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setTitle("Delete ${detailItem.detailItemName}?")
                setPositiveButton(
                    "Yes"
                ) { dialog, id ->
                    viewModel.deleteDetailItem(detailItem)
                    Log.d("deletion", "id was ${detailItem}")
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