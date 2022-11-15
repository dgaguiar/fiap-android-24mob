package com.example.simplemarketlist.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : BaseAuthFragment(), ItemClickListener {

    override val layout = R.layout.fragment_home

    private lateinit var constraintLayout: ConstraintLayout

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var button: FloatingActionButton

    private lateinit var list: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        showBanner()
        setRecyclerView()
    }

    private fun setUpView(view: View) {
        list = view.findViewById(R.id.rv_itens)
        constraintLayout = view.findViewById(R.id.containerAds)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        button = view.findViewById(R.id.fab_add)
        button.setOnClickListener {
            NewItemSheet(null, homeViewModel).show(parentFragmentManager, "newTaskTag")
        }
    }

    private fun setRecyclerView() {
        val homeFragment = this
        homeViewModel.items.observe(viewLifecycleOwner) {
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ItemAdapter(it, homeFragment)
            }
        }
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

    override fun editTaskItem(taskItem: ItemList) {
        NewItemSheet(taskItem, homeViewModel).show(parentFragmentManager, "newTaskTag")
    }
}