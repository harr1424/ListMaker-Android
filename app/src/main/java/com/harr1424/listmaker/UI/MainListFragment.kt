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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harr1424.listmaker.ListViewModel
import com.harr1424.listmaker.UI.adapters.MainAdapter
import com.harr1424.listmaker.databinding.FragmentMainListBinding
import kotlinx.android.synthetic.main.fragment_main_list.*


class MainListFragment : Fragment() {
    private val viewModel: ListViewModel by activityViewModels()
    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: MainAdapter
    lateinit var recyclerView: RecyclerView

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
        viewModel.list.observe(this.viewLifecycleOwner) {}
        // Add click listener to floating action button
        binding.fabMain.setOnClickListener {
            addListItem()
        }
        adapter = MainAdapter(viewModel.list)
        main_list_view.adapter = adapter
        main_list_view.layoutManager = LinearLayoutManager(requireContext())
        recyclerView = binding.mainListView
    }

    private fun addListItem() {
        val input = EditText(activity)
        input.setHint("Enter the name of your new list item")
        input.inputType = InputType.TYPE_CLASS_TEXT
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setTitle("Add List Item")
                setView(input)
                setPositiveButton(
                    "Add"
                ) { dialog, id ->
                    val newItem = input.text.toString()
                    viewModel.addItem(newItem)
                    adapter.notifyDataSetChanged()
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
}
