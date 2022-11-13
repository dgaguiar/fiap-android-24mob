package com.example.simplemarketlist.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment

class HomeFragment : BaseAuthFragment(), ItemClickListener {

    override val layout: Int
    get() = R.layout.fragment_home

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        requireContext()

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.fabAdd.setOnClickListener {
            NewItemSheet(null).show(parentFragmentManager, "newTaskTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val homeFragment = this
        homeViewModel.items.observe(this) {
            binding.rvItens.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ItemAdapter(it, homeFragment)
            }
        }
    }

    override fun editTaskItem(taskItem: ItemList) {

    }

    override fun completeTaskItem(taskItem: ItemList) {

    }
}