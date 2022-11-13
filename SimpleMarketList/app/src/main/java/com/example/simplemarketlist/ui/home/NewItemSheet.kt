package com.example.simplemarketlist.ui.home

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.simplemarketlist.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewItemSheet(var item: ItemList?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewItemSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if (item != null) {
            binding.taskTitle.text = "Editar item"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(item!!.name)
            binding.value.text = editable.newEditable(item!!.value)
        } else
        {
            binding.taskTitle.text = "Novo item"
        }

        homeViewModel = ViewModelProvider(activity)[HomeViewModel::class.java]
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    private fun saveAction()
    {
        val name = binding.name.text.toString()
        val value = binding.value.text.toString()
        if(item == null)
        {
            val newTask = ItemList(name, value)
            homeViewModel.addTaskItem(newTask)
        }
        else
        {
            homeViewModel.updateTaskItem(item!!.id, name, value)
        }
        binding.name.setText("")
        binding.value.setText("")
        dismiss()
    }
}