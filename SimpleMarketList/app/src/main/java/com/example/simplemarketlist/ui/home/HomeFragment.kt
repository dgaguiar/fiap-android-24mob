package com.example.simplemarketlist.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment

class HomeFragment : BaseAuthFragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>? = null

    override val layout: Int
        get() = R.layout.fragment_home

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var recycleViewHome: RecyclerView

    val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        binding = FragmentHomeBinding.bind(view)
        requireContext()
        layoutManager = LinearLayoutManager(view.context)
        recycleViewHome.layoutManager = layoutManager
        adapter = ItemAdapter()
        recycleViewHome.adapter = adapter

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.fabAdd.setOnClickListener {
            NewItemSheet(null).show(parentFragmentManager, "")
        }
    }

    private fun setUpView(view: View) {
        recycleViewHome = view.findViewById(R.id.rv_itens)
    }
}