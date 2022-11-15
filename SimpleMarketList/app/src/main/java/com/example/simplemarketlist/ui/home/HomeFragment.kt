package com.example.simplemarketlist.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment

class HomeFragment : BaseAuthFragment(), ItemClickListener {

    override val layout: Int
    get() = R.layout.fragment_home

    private lateinit var constraintLayout: ConstraintLayout

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraintLayout = view.findViewById(R.id.containerAds)
        showBanner()
        binding = FragmentHomeBinding.bind(view)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.fabAdd.setOnClickListener {
            NewItemSheet(null).show(parentFragmentManager, "newTaskTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val homeFragment = this
        homeViewModel.items.observe(viewLifecycleOwner) {
            binding.rvItens.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ItemAdapter(it, homeFragment)
            }
        }
    }

    override fun editTaskItem(taskItem: ItemList) {
        // TODO
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

    private fun isFreeVersion(): Boolean {
        return requireActivity().getPackageName() == "com.example.simplemarketlist.free"
    }

    override fun completeTaskItem(taskItem: ItemList) {
        homeViewModel.setCompleted(taskItem)
    }
}